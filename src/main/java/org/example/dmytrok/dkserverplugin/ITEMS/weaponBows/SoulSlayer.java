package org.example.dmytrok.dkserverplugin.ITEMS.weaponBows;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;
import org.example.dmytrok.dkserverplugin.LEVELSYSTEM.LevelCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoulSlayer implements Listener {
    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 1000;

    private final long abilityCooldown = 30000;
    private final Map<Player, Long> abilityCooldowns = new HashMap<>();

    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Soul Slayer"))) {
            return;
        }
        ItemStack item = event.getItem();
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR) && !(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && isOnCooldown(player)) {
            return;
        }

        if(LevelCheck.isLevelTooLow(player, item)) {
            return;
        }

        if(player.isSneaking()) {
            if(!isOnAbilityCooldown(player)) {
                performCombo(player);
            } else {
                player.sendMessage( "§4§lRecharge: " + getAbilityCooldown(player) + " sec");
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
                return;
            }
        }

        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setShooter(player);
        arrow.setVelocity(player.getLocation().getDirection().multiply(9.5));
        arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
        player.spawnParticle(Particle.CRIT, player.getLocation().add(0, 1.5, 0), 10);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!arrow.isDead() && !arrow.isInBlock()) {
                    arrow.remove();
                }
            }
        }.runTaskLater(DK_Server_Plugin.getInstance(), 40);

        setCooldown(player);
    }

    private void performCombo(Player player) {
        player.sendTitle("§6§lCombo!", "§fSoul Collector", 15, 15, 15);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDEREYE_DEATH, 3, 1);

        List<LivingEntity> targets = getNearbyMobs(player, 15);

        for (LivingEntity target : targets) {
            target.setVelocity(player.getLocation().subtract(target.getLocation()).toVector().normalize().multiply(1.5));
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                for (LivingEntity target : targets) {

                    target.damage(10, player);


                    Arrow arrow = player.launchProjectile(Arrow.class);
                    arrow.setShooter(player);
                    arrow.setVelocity(target.getLocation().subtract(player.getLocation()).toVector().normalize().multiply(1.5));
                    arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);


                    target.setVelocity(target.getLocation().subtract(player.getLocation()).toVector().normalize().multiply(2));

                    player.spawnParticle(Particle.EXPLOSION_LARGE, target.getLocation(), 1);
                    player.playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                }
            }
        }.runTaskLater(DK_Server_Plugin.getInstance(), 20);

        setAbilityCooldown(player);
    }

    private List<LivingEntity> getNearbyMobs(Player player, double radius) {
        List<LivingEntity> nearbyEntities = new ArrayList<>();
        player.getWorld().getEntitiesByClass(LivingEntity.class).forEach(entity -> {
            if (entity != player && entity.getLocation().distance(player.getLocation()) <= radius) {
                nearbyEntities.add(entity);
            }
        });
        return nearbyEntities;
    }

    private boolean isWoodSword(ItemStack itemStack) {
        return itemStack.getType().equals(Material.WOOD_SWORD);
    }

    private boolean isOnCooldown(Player player) {
        if (!cooldowns.containsKey(player)) {
            return false;
        }
        long lastUsed = cooldowns.get(player);
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastUsed) < cooldownTime;
    }

    private void setCooldown(Player player) {
        cooldowns.put(player, System.currentTimeMillis());
    }

    private boolean isOnAbilityCooldown(Player player) {
        if (!abilityCooldowns.containsKey(player)) {
            return false;
        }
        long lastUsed = abilityCooldowns.get(player);
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastUsed) < abilityCooldown;
    }

    private void setAbilityCooldown(Player player) {
        abilityCooldowns.put(player, System.currentTimeMillis());
    }

    private long getAbilityCooldown(Player player) {
        if (!abilityCooldowns.containsKey(player)) {
            return 0;
        }
        long lastUsed = abilityCooldowns.get(player);
        long currentTime = System.currentTimeMillis();
        long timeLeft = abilityCooldown - (currentTime - lastUsed);
        return Math.max(0, timeLeft / 1000);
    }
}

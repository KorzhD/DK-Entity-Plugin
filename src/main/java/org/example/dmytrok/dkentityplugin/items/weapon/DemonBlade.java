package org.example.dmytrok.dkentityplugin.items.weapon;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkentityplugin.DK_Entity_Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemonBlade implements Listener {

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 10000;
    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Demon Blade"))) {
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR)) {
            return;
        }

        if (isOnCooldown(player)) {
            player.sendMessage( "§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
            return;
        }

        World world = player.getWorld();
        Location location = player.getLocation();

        List<Entity> entities = player.getNearbyEntities(5, 5, 5);
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                if (!(entity instanceof Player)) {
                    player.playSound(location, Sound.ENTITY_WITHER_HURT, 0.5f, 3);
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 50, false, false));
                    world.spawnParticle(Particle.SWEEP_ATTACK, entity.getLocation().add(0, 1, 0), 5);

                    createDarkSmokeVortex((LivingEntity) entity);
                }
            }
        }
        setCooldown(player);
    }

    private void createDarkSmokeVortex(LivingEntity entity) {
        if (entity.getType().equals(EntityType.ARMOR_STAND)) {
            return;
        }
        new BukkitRunnable() {
            int ticks = 200;

            @Override
            public void run() {
                if (ticks <= 0 || entity.isDead() || !entity.hasPotionEffect(PotionEffectType.WITHER)) {
                    this.cancel();
                    return;
                }

                Location loc = entity.getLocation().add(0, 1, 0);
                double radius = 1.5;
                int points = 20;

                for (int i = 0; i < points; i++) {
                    double angle = Math.toRadians(i * (360.0 / points));
                    double x = Math.cos(angle) * radius;
                    double z = Math.sin(angle) * radius;
                    entity.getWorld().spawnParticle(Particle.SMOKE_NORMAL, loc.clone().add(x, 0, z), 1, 0, 0, 0, 0);
                }

                ticks -= 5;
            }
        }.runTaskTimer(DK_Entity_Plugin.getInstance(), 0, 5);
    }

    private boolean isWoodSword(ItemStack itemStack) {
        if (itemStack.getType().equals(Material.WOOD_SWORD)) {
            return true;
        }
        return false;
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
    private long getCooldownTimeLeft(Player player) {
        if (!cooldowns.containsKey(player)) {
            return 0;
        }
        long lastUsed = cooldowns.get(player);
        long currentTime = System.currentTimeMillis();
        long timeLeft = cooldownTime - (currentTime - lastUsed);
        return Math.max(0, timeLeft / 1000);
    }
}

package org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LegendaryKatana implements Listener {
    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final Map<UUID, ArmorStand> activeStands = new HashMap<>();
    private final Map<UUID, UUID> standToMob = new HashMap<>();
    private final long cooldownTime = 40000;
    private final long abilityDuration = 10000;
    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Legendary Katana"))) {
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR) && !(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        if (isOnCooldown(player)) {
            player.sendMessage( "§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
            return;
        }


        player.sendTitle("§6§lCombo!", "§fSoul Butcher", 15, 15, 15);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDEREYE_DEATH, 3, 1);
        player.sendMessage("§f§lLegendary Katana is ready! Hit a mob to activate.");
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 3, 1);
        activeStands.put(player.getUniqueId(), null);
    }

    @EventHandler
    public void onMobHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        if(event.getEntity() instanceof ArmorStand) {
            return;
        }
        Player player = (Player) event.getDamager();
        LivingEntity mob = (LivingEntity) event.getEntity();

        if (!activeStands.containsKey(player.getUniqueId()) || activeStands.get(player.getUniqueId()) != null) {
            return;
        }

        Location mobLoc = mob.getLocation();
        Location standLoc = mobLoc.clone().add(0, 2, 0);
        World world = mobLoc.getWorld();

        ArmorStand stand = (ArmorStand) world.spawnEntity(standLoc, EntityType.ARMOR_STAND);
        stand.setVisible(false);
        stand.setInvulnerable(true);
        stand.setGravity(false);
        stand.setHelmet(new ItemStack(Material.QUARTZ_BLOCK));

        activeStands.put(player.getUniqueId(), stand);
        standToMob.put(stand.getUniqueId(), mob.getUniqueId());

        new BukkitRunnable() {
            double angle = 0;
            boolean stationary = true;

            @Override
            public void run() {
                if (!stand.isValid() || !mob.isValid()) {
                    stand.remove();
                    cancel();
                    return;
                }

                if (stationary) {
                    stationary = false;
                } else {
                    angle += Math.PI / 8;
                    double x = Math.cos(angle) * 2;
                    double z = Math.sin(angle) * 2;
                    stand.teleport(mob.getLocation().clone().add(x, 1, z));
                }

                world.spawnParticle(Particle.CLOUD, stand.getLocation().add(0, 1.5, 0), 10, 0.2, 0.2, 0.2, 0);
            }
        }.runTaskTimer(DK_Server_Plugin.getInstance(), 0, 2);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (stand.isValid()) {
                    stand.remove();
                }
                activeStands.remove(player.getUniqueId());
                standToMob.remove(stand.getUniqueId());
                setCooldown(player);
            }
        }.runTaskLater(DK_Server_Plugin.getInstance(), abilityDuration / 50);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!stand.isValid()) {
                    cancel();
                    return;
                }
                world.playSound(stand.getLocation(), Sound.ENTITY_VEX_AMBIENT, 1, 1);
            }
        }.runTaskTimer(DK_Server_Plugin.getInstance(), 0, 10);
    }

    @EventHandler
    public void onStandHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof ArmorStand)) {
            return;
        }

        ArmorStand stand = (ArmorStand) event.getEntity();
        Player player = (Player) event.getDamager();

        if (!standToMob.containsKey(stand.getUniqueId())) {
            return;
        }

        UUID mobId = standToMob.get(stand.getUniqueId());
        LivingEntity mob = (LivingEntity) Bukkit.getEntity(mobId);

        if (mob != null) {
            long damage = Math.round(event.getDamage() * 4);
            mob.damage(damage);
            player.sendMessage("§c§lCritical Strike! " + damage + " damage dealt!");
            player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_IMPACT, 2, 0.5f);
        }

        stand.remove();
        activeStands.remove(player.getUniqueId());
        standToMob.remove(stand.getUniqueId());
        setCooldown(player);
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

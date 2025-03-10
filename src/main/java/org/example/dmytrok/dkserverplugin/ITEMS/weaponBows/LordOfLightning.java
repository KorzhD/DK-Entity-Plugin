package org.example.dmytrok.dkserverplugin.ITEMS.weaponBows;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.DemonBlade;
import org.example.dmytrok.dkserverplugin.LEVELSYSTEM.LevelCheck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LordOfLightning  implements Listener {

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 1000;

    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Lord of Lightning"))) {
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

            Arrow arrow = player.launchProjectile(Arrow.class);
            arrow.setShooter(player);
            arrow.setCustomName("Zeus Arrow");
            arrow.setCustomNameVisible(false);
            arrow.setVelocity(player.getLocation().getDirection().multiply(15));
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

    @EventHandler
    public void onZeusArrowHit(ProjectileHitEvent event) {
        if(event.getEntity() == null) {
            return;
        }
        if(!(event.getEntity() instanceof Arrow)) {
            return;
        }
        Arrow arrow = (Arrow) event.getEntity();

        if(arrow.getCustomName() == null || !arrow.getCustomName().equals("Zeus Arrow")) {
            return;
        }

        Location arrowLocation = arrow.getLocation();

        if(event.getHitEntity() != null) {
         arrowLocation.getWorld().strikeLightningEffect(arrow.getLocation());
         List<Entity> entities = arrow.getNearbyEntities(5, 5, 5);
            entities.removeIf(entity -> entity instanceof Player);
            entities.removeIf(entity -> entity instanceof ArmorStand);
            for(Entity entity : entities) {
                if(entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(10);
                    entity.getLocation().getWorld().strikeLightningEffect(entity.getLocation());
                }
            }

        } else if(event.getHitBlock() != null) {
            arrowLocation.getWorld().strikeLightningEffect(arrow.getLocation());
            List<Entity> entities = arrow.getNearbyEntities(5, 5, 5);
            entities.removeIf(entity -> entity instanceof Player);
            entities.removeIf(entity -> entity instanceof ArmorStand);
            for(Entity entity : entities) {
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(10);
                    entity.getLocation().getWorld().strikeLightningEffect(entity.getLocation());
                }
            }
        } else {
            return;
        }
        arrow.remove();
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

}

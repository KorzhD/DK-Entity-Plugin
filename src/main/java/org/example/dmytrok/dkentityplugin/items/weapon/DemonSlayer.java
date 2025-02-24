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
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkentityplugin.DK_Entity_Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemonSlayer implements Listener {
    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 60000;

    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Demon Slayer"))) {
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR)) {
            return;
        }

        if (isOnCooldown(player)) {
            player.sendMessage("§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
            return;
        }

        World world = player.getWorld();
        Location location = player.getLocation();

        List<Entity> entities = player.getNearbyEntities(5, 5, 5);
        List<Entity> attackedEntities = new ArrayList<>();
        attackedEntities.add(entities.get(1));
        attackedEntities.add(entities.get(2));

        for (Entity entity : attackedEntities) {
            if (entity instanceof LivingEntity) {
                if (!(entity instanceof Player)) {
                    player.playSound(location, Sound.ENTITY_WITHER_HURT, 0.5f, 3);
                    world.spawnParticle(Particle.FLAME, entity.getLocation().add(0, 1, 0), 5);
                    createRotatingPentagram((LivingEntity) entity);
                }
            }
        }
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

    private void createRotatingPentagram(LivingEntity entity) {
        if (entity.getType().equals(EntityType.ARMOR_STAND)) {
            return;
        }

        new BukkitRunnable() {
            int ticks = 200;
            double rotation = 0;
            double circleRadius = 3;

            @Override
            public void run() {
                if (ticks <= 0 || entity.isDead()) {
                    this.cancel();
                    return;
                }

                Location loc = entity.getLocation().clone().subtract(0, -0.3, 0);
                double radius = 2.5;
                int points = 5;

                for (int i = 0; i < points; i++) {
                    double angle1 = Math.toRadians((360.0 / points) * i) + rotation;
                    double angle2 = Math.toRadians((360.0 / points) * ((i + 2) % points)) + rotation;

                    double x1 = Math.cos(angle1) * radius;
                    double z1 = Math.sin(angle1) * radius;
                    double x2 = Math.cos(angle2) * radius;
                    double z2 = Math.sin(angle2) * radius;

                    drawParticleLine(loc.clone().add(x1, 0, z1), loc.clone().add(x2, 0, z2));
                }


                int circlePoints = 50;
                for (int i = 0; i < circlePoints; i++) {
                    double angle = Math.toRadians((360.0 / circlePoints) * i) + rotation;
                    double x = Math.cos(angle) * circleRadius;
                    double z = Math.sin(angle) * circleRadius;

                    loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(x, 0, z), 2);
                }

                if (ticks % 40 == 0) {
                    entity.damage(10);
                    entity.getLocation().getWorld().spawnParticle(Particle.LAVA, entity.getLocation(), 5);
                }

                rotation += Math.toRadians(4);
                ticks -= 5;
            }
        }.runTaskTimer(DK_Entity_Plugin.getInstance(), 0, 5);
    }

    private void drawParticleLine(Location start, Location end) {
        int points = 30;
        World world = start.getWorld();

        for (int i = 0; i <= points; i++) {
            double t = i / (double) points;
            double x = start.getX() + (end.getX() - start.getX()) * t;
            double y = start.getY() + (end.getY() - start.getY()) * t;
            double z = start.getZ() + (end.getZ() - start.getZ()) * t;
            world.spawnParticle(Particle.REDSTONE, new Location(world, x, y, z), 2);
        }
    }
}

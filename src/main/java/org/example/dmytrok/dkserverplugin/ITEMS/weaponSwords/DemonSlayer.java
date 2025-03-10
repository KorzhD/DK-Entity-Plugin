package org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;
import org.example.dmytrok.dkserverplugin.LEVELSYSTEM.LevelCheck;

import java.util.*;

public class DemonSlayer implements Listener {

    private final HashMap<UUID, ClickTracker> clickData = new HashMap<>();
    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 30000;

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
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && isOnCooldown(player)) {
            player.sendMessage("§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if(LevelCheck.isLevelTooLow(player, item)) {
            return;
        }

        UUID playerId = player.getUniqueId();
        Action action = event.getAction();

        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            if(isOnCooldown(player)) {
                return;
            }

            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            if (clickData.containsKey(playerId)) {
                ClickTracker tracker = clickData.get(playerId);
                if (tracker.hasLeftClick) {
                    performComboAction(player);
                    clickData.remove(playerId);
                    return;
                }
            }

           ClickTracker tracker = new ClickTracker();
            clickData.put(playerId, tracker);

            new BukkitRunnable() {
                @Override
                public void run() {
                    clickData.remove(playerId);
                }
            }.runTaskLater(DK_Server_Plugin.getInstance(), 40);
        }

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (clickData.containsKey(playerId)) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                ClickTracker tracker = clickData.get(playerId);
                tracker.hasLeftClick = true;
            }
        }
    }


    private void performComboAction(Player player) {
        player.sendTitle("§6§lCombo!", "§4Pentagram of Fire", 15, 15, 15);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDEREYE_DEATH, 3, 1);
        List<Entity> entities = player.getNearbyEntities(5, 5, 5);
        entities.removeIf(entity -> entity instanceof Player);
        entities.removeIf(entity -> entity instanceof ArmorStand);
        List<Entity> attackedEntities = new ArrayList<>();
        for (int i = 0; i < Math.min(entities.size(), 2); i++) {
            attackedEntities.add(entities.get(i));
        }
        for (Entity entity : attackedEntities) {
            if (entity instanceof LivingEntity) {
                if (!(entity instanceof Player)) {
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, 0.5f, 3);
                    player.getWorld().spawnParticle(Particle.FLAME, entity.getLocation().add(0, 1, 0), 5);
                    createRotatingPentagram((LivingEntity) entity);
                }
            }
        }
        setCooldown(player);
    }

    private static class ClickTracker {
        boolean hasLeftClick = false;
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
        }.runTaskTimer(DK_Server_Plugin.getInstance(), 0, 5);
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

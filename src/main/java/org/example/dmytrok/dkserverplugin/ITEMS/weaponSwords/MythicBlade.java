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

import java.util.HashMap;
import java.util.Map;

public class MythicBlade implements Listener {

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 40000;
    private final long tornadoDuration = 100L;
    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Mythic Blade"))) {
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR) && !(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if(LevelCheck.isLevelTooLow(player, item)) {
            return;
        }

        if (isOnCooldown(player)) {
            player.sendMessage("§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
            return;
        }

        setCooldown(player);
        spawnTornado(player);

    }
    private void spawnTornado(Player player) {
        player.sendTitle("§6§lCombo!", "§fTornado", 15, 15, 15);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDEREYE_DEATH, 3, 1);
        Location location = player.getLocation().add(player.getLocation().getDirection().multiply(2));
        ArmorStand tornadoCore = location.getWorld().spawn(location, ArmorStand.class);
        tornadoCore.setVisible(false);
        tornadoCore.setGravity(false);

        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= tornadoDuration) {
                    tornadoCore.remove();
                    cancel();
                    return;
                }

                location.add(player.getLocation().getDirection().multiply(0.5));
                tornadoCore.teleport(location);

               spawnTornadoParticles(location.getWorld(), location);
                playTornadoSound(location.getWorld(), location);

                for (Entity entity : location.getWorld().getNearbyEntities(location, 4, 4, 4)) {
                    if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                        if(!(entity instanceof ArmorStand)) {
                            ((LivingEntity) entity).damage(15);
                        }
                    }
                }

                ticks++;
            }
        }.runTaskTimer(DK_Server_Plugin.getInstance(), 0, 1);
    }

    private void spawnTornadoParticles(World world, Location loc) {
        for (double y = 0; y < 3; y += 0.5) {
            for (double angle = 0; angle < 360; angle += 45) {
                double radians = Math.toRadians(angle);
                double x = Math.cos(radians) * 1.5;
                double z = Math.sin(radians) * 1.5;
                world.spawnParticle(Particle.CLOUD, loc.clone().add(x, y, z), 0);
            }
        }
    }

    private void playTornadoSound(World world, Location loc) {
        world.playSound(loc, Sound.ENTITY_ENDERDRAGON_FLAP, 1, 1);
    }

    private void damageNearbyMobs(World world, Location loc) {
        for (Entity entity : world.getNearbyEntities(loc, 4, 4, 4)) {
            if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                ((LivingEntity) entity).damage(20);
            }
        }
    }

    private boolean isWoodSword(ItemStack itemStack) {
        return itemStack.getType() == Material.WOOD_SWORD;
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

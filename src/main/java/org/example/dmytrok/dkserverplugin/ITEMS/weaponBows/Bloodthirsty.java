package org.example.dmytrok.dkserverplugin.ITEMS.weaponBows;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;
import org.example.dmytrok.dkserverplugin.LEVELSYSTEM.LevelCheck;

import java.util.HashMap;
import java.util.Map;

public class Bloodthirsty implements Listener {
    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 1000;

    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Bloodthirsty"))) {
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
        arrow.setCustomName("Bloodthirsty arrow");
        arrow.setCustomNameVisible(false);
        arrow.setShooter(player);
        arrow.setVelocity(player.getLocation().getDirection().multiply(15));
        arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
        player.spawnParticle(Particle.CRIT, player.getLocation().add(0, 1.5, 0), 10);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!arrow.isDead() && !arrow.isInBlock()) {
                    Location arrowLocation = arrow.getLocation();
                    arrowLocation.getWorld().spawnParticle(Particle.REDSTONE, arrowLocation,  20, 0, 0, 0, 0);
                }
            }
        }.runTaskTimer(DK_Server_Plugin.getInstance(), 0, 1);

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
    public void onBloodthirstyArrowHit(ProjectileHitEvent event) {
        if(event.getEntity() == null) {
            return;
        }
        if(!(event.getEntity() instanceof Arrow)) {
            return;
        }
        Arrow arrow = (Arrow) event.getEntity();

        if (arrow.getCustomName() == null || !arrow.getCustomName().equals("Bloodthirsty arrow"))  {
            return;
        }
        Player player = (Player) arrow.getShooter();

        if(event.getHitEntity() != null) {
            double currentHealth = player.getHealth();
            double newHealth = currentHealth + 1;
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BELL , 1, 1);
            player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation().add(0, 1, 0),  20, 2, 2, 2, 2);
            player.setHealth(newHealth);

        } else if(event.getHitBlock() != null) {
            double currentHealth = player.getHealth();
            double newHealth = currentHealth - 2;
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, 1, 1);
            player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation().add(0, 1, 0),  20, 2, 2, 2, 2);
            player.setHealth(newHealth);
        } else {
            return;
        }

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

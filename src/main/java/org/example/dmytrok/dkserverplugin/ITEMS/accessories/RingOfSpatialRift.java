package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;

import java.util.HashMap;
import java.util.UUID;

public class RingOfSpatialRift implements Listener {
    private final HashMap<UUID, Integer> jumpCounts = new HashMap<>();
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private final HashMap<UUID, Boolean> fallImmunity = new HashMap<>();
    private static final long COOLDOWN_TIME = 5000;
    private static final long FALL_IMMUNITY_TIME = 3000;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (!isRingOfSpatialRiftEquipped(player)) return;

        if (player.isOnGround()) {
            jumpCounts.put(playerId, 0);
            return;
        }

        if (!player.isSprinting()) return;

        if (cooldowns.containsKey(playerId) && (System.currentTimeMillis() - cooldowns.get(playerId) < COOLDOWN_TIME)) {
            return;
        }

        int jumps = jumpCounts.getOrDefault(playerId, 0);

        if (jumps >= 2) {
            cooldowns.put(playerId, System.currentTimeMillis());
            return;
        }

        player.setVelocity(player.getVelocity().add(new Vector(0, 0.7, 0)));
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
        player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 15);
        jumpCounts.put(playerId, jumps + 1);

        if (jumps >= 1) {
            fallImmunity.put(playerId, true);

            new BukkitRunnable() {
                @Override
                public void run() {
                    fallImmunity.remove(playerId);
                }
            }.runTaskLater(DK_Server_Plugin.getInstance(), FALL_IMMUNITY_TIME / 50);
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        UUID playerId = player.getUniqueId();

        if (event.getCause() == EntityDamageEvent.DamageCause.FALL && fallImmunity.getOrDefault(playerId, false)) {
            event.setCancelled(true);
            fallImmunity.remove(playerId);
        }
    }

    private boolean isRingOfSpatialRiftEquipped(Player player) {
        ItemStack itemInSlot9 = player.getInventory().getItem(9);
        ItemStack itemInSlot10 = player.getInventory().getItem(10);
        ItemStack itemInSlot11 = player.getInventory().getItem(11);

        return isRingOfSpatialRift(itemInSlot9) || isRingOfSpatialRift(itemInSlot10) || isRingOfSpatialRift(itemInSlot11);
    }

    private boolean isRingOfSpatialRift(ItemStack item) {
        return item != null && item.getType() == Material.CHORUS_FRUIT_POPPED &&
                item.getItemMeta() != null &&
                item.getItemMeta().getDisplayName().equals("Ring of Spatial Rift");
    }
}

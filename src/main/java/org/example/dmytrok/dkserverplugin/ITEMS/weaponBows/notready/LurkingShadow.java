package org.example.dmytrok.dkserverplugin.ITEMS.weaponBows.notready;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
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

public class LurkingShadow  implements Listener {
    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 1000;

    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Lurking Shadow"))) {
            return;
        }
        ItemStack item = event.getItem();
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR)) {
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
        arrow.setVelocity(player.getLocation().getDirection().multiply(2));
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

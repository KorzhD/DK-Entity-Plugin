package org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.notready;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Thunderer implements Listener {
        private final HashMap<UUID, ClickTracker> clickData = new HashMap<>();
        private final Map<Player, Long> cooldowns = new HashMap<>();
        private final long cooldownTime = 10000;

        @EventHandler
        public void onWeaponUse(PlayerInteractEvent event) {
            Player player = event.getPlayer();
            if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
                return;
            }
            if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                    player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Thunderer"))) {
                return;
            }

            if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && isOnCooldown(player)) {
                player.sendMessage("§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
                return;
            }

            UUID playerId = player.getUniqueId();
            Action action = event.getAction();

            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
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

            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                if (clickData.containsKey(playerId)) {
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    ClickTracker tracker = clickData.get(playerId);
                    tracker.hasLeftClick = true;
                }
            }
        }


        private void performComboAction(Player player) {
            player.sendTitle("§6§lCombo!", "////", 15, 15, 15);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDEREYE_DEATH, 3, 1);

            //////////////////////

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
}

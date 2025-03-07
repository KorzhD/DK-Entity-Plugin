package org.example.dmytrok.dkserverplugin.UTILS;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;

import java.util.HashMap;
import java.util.Map;

public class Stamina implements Listener {

    private final Map<Player, BukkitRunnable> runningTasks = new HashMap<>();
    private final Map<Player, BukkitRunnable> regenerationTasks = new HashMap<>();

    private void startStaminaRegeneration(final Player player) {
        if (regenerationTasks.containsKey(player)) return;

        BukkitRunnable regenerationTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isSprinting() && player.getFoodLevel() < 20) {
                    player.setFoodLevel(player.getFoodLevel() + 1);
                }
            }
        };
        regenerationTasks.put(player, regenerationTask);
        regenerationTask.runTaskTimer(DK_Server_Plugin.getInstance(), 0L, 40L);
    }

    private void startStaminaDrain(final Player player) {
        if (runningTasks.containsKey(player)) return;

        BukkitRunnable drainTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (player.isSprinting() && player.getFoodLevel() > 1) {
                    if(player.getFoodLevel() < 8) {
                        player.setFoodLevel(player.getFoodLevel() - 2);
                    }
                    player.setFoodLevel(player.getFoodLevel() - 1);
                } else {
                    cancel();
                }
            }
        };
        runningTasks.put(player, drainTask);
        drainTask.runTaskTimer(DK_Server_Plugin.getInstance(), 0L, 20L);
    }

    private void stopStaminaTask(Player player) {
        if (runningTasks.containsKey(player)) {
            runningTasks.get(player).cancel();
            runningTasks.remove(player);
        }
        if (regenerationTasks.containsKey(player)) {
            regenerationTasks.get(player).cancel();
            regenerationTasks.remove(player);
        }
    }

    @EventHandler
    public void onPlayerSprinting(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
            if (event.isSprinting()) {
                startStaminaDrain(player);
            } else {
                stopStaminaTask(player);
                startStaminaRegeneration(player);
            }
        }

    }
}

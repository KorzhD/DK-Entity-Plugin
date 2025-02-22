package org.example.dmytrok.dkentityplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkentityplugin.DK_Entity_Plugin;

import java.util.*;

public class CongratulationTitle {

    public static ArmorStand createCongratulationTitle(Location bossSpawnLocation) {
        ArmorStand armorStand = (ArmorStand) bossSpawnLocation.getWorld().spawnEntity(bossSpawnLocation, EntityType.ARMOR_STAND);
        armorStand.setCustomName("§7§lCONGRATULATION!!");
        armorStand.setCustomNameVisible(true);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);

        return armorStand;
    }

    public static void removeAfter(ArmorStand armorStand, int seconds) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isCongratulationTitle(armorStand)) {
                    if (!armorStand.isDead()) {
                        armorStand.remove();
                    }
                }
            }
        }.runTaskLater(DK_Entity_Plugin.getInstance(), seconds * 20L);
    }

    private static boolean isCongratulationTitle(ArmorStand armorStand) {
        if (armorStand == null) {
            return false;
        }
        if (!(armorStand.getCustomName().equals("§7§lCONGRATULATION!!"))) {
            return false;
        }
        return true;
    }

    public static void displayCongratulation(HashMap<Player, Double> players) {
        if (players != null) {
            Set<Player> playerSet = players.keySet();
            for (Player player : playerSet) {
                player.sendTitle("§7§l CONGRATULATION!!", "§8You hit: " + Math.round(players.get(player)), 30, 30, 30);
            }
        }
    }
}


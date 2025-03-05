package org.example.dmytrok.dkserverplugin.LEVELSYSTEM;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class LevelSystem {
    private static final HashMap<UUID, Integer> playerLevels = new HashMap<>();
    private static final HashMap<UUID, Integer> playerExp = new HashMap<>();
    private static final int baseExpRequirement = 100;

    public static void addExp(Player player, int amount) {
        UUID uuid = player.getUniqueId();
        int currentExp = playerExp.getOrDefault(uuid, 0) + amount;
        int currentLevel = playerLevels.getOrDefault(uuid, 1);
        int requiredExp = getRequiredExp(currentLevel);

        if (currentExp >= requiredExp) {
            currentExp -= requiredExp;
            currentLevel++;
            playerLevels.put(uuid, currentLevel);

            player.sendTitle("§6New Level!", currentLevel - 1 + " > " + currentLevel, 30, 30, 30);

            ScoreboardManager.updateScore(player, "level", currentLevel);
            ScoreboardManager.updateScore(player, "newExp", getRequiredExp(getLevel(player)));
        }
        playerExp.put(uuid, currentExp);
        ScoreboardManager.updateScore(player, "exp", currentExp);
    }


    public static int getRequiredExp(int level) {
        return baseExpRequirement + (level - 1) * 50;
    }

    public static int getLevel(Player player) {
        return playerLevels.getOrDefault(player.getUniqueId(), 1);
    }

    public static int getExp(Player player) {
        return playerExp.getOrDefault(player.getUniqueId(), 0);
    }
}


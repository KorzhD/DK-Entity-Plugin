package org.example.dmytrok.dkserverplugin.LEVELSYSTEM;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ScoreboardManager implements Listener {

    private static final Map<Player, Scoreboard> playerScoreboards = new HashMap<>();

    public static void createScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Stats", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("  §b§l " + player.getDisplayName() + "'s Statistic");

        objective.getScore("  ").setScore(10);
        objective.getScore("§d§lLevel: " + LevelSystem.getLevel(player)).setScore(9);
        objective.getScore("§6§lExp: " + LevelSystem.getExp(player)).setScore(8);
        objective.getScore("§6To new Level: " + LevelSystem.getRequiredExp(LevelSystem.getLevel(player))).setScore(7);
        objective.getScore("   ").setScore(6);
        objective.getScore("§2§lClass: ").setScore(5);
        objective.getScore("     ").setScore(4);
        objective.getScore("      ").setScore(3);
        objective.getScore("       ").setScore(2);
        objective.getScore("        ").setScore(1);
        objective.getScore("    §f§l ⇒ www.projectunderworld.com ⇐").setScore(0);

        player.setScoreboard(scoreboard);
        playerScoreboards.put(player, scoreboard);
    }

    public static void updateScore(Player player, String type, int newStats) {
        Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard == null) return;

        Objective objective = scoreboard.getObjective("Stats");
        if (objective == null) return;

        // Получаем текущие строки Scoreboard
        Set<String> entries = new HashSet<>(scoreboard.getEntries());

        // Карта для соответствия типа обновления и строки в Scoreboard
        Map<String, String> scoreEntries = new HashMap<>();
        scoreEntries.put("level", "§l§dLevel: ");
        scoreEntries.put("exp", "§l§6Exp: ");
        scoreEntries.put("newExp", "§6To new Level: ");

        if (!scoreEntries.containsKey(type)) return;

        // Удаляем старую строку
        for (String entry : entries) {
            if (entry.startsWith(scoreEntries.get(type))) {
                scoreboard.resetScores(entry);
            }
        }

        // Добавляем новую строку
        String newEntry = scoreEntries.get(type) + newStats;
        int scoreValue = -1;

        if (type.equals("level")) {
            scoreValue = 9;
        } else if (type.equals("exp")) {
            scoreValue = 8;
        } else if (type.equals("newExp")) {
            scoreValue = 7;
        }

        if (scoreValue != -1) {
            objective.getScore(newEntry).setScore(scoreValue);
        }
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!playerScoreboards.containsKey(player)) {
            createScoreboard(player);
        }
    }
}

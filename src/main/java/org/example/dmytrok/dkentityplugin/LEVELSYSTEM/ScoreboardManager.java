package org.example.dmytrok.dkentityplugin.LEVELSYSTEM;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.*;

public class ScoreboardManager implements Listener {

    private static Score levels;
    private static Score exp;
    private static Score expToNextLevel;


    public static void createScoreboard(Player player) {

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("Stats", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§l§b " + player.getDisplayName() + "'s Statistic");

        Score score10 = objective.getScore("  ");
        score10.setScore(10);

        levels = objective.getScore("§l§dLevel: " + LevelSystem.getLevel(player));
        levels.setScore(9);

        exp = objective.getScore("§l§6Exp: " + LevelSystem.getExp(player));
        exp.setScore(8);

        expToNextLevel = objective.getScore("§6To new Level: " + LevelSystem.getRequiredExp(LevelSystem.getLevel(player)));
        expToNextLevel.setScore(7);

        Score score6 = objective.getScore("   ");
        score6.setScore(6);

        Score score5 = objective.getScore("    ");
        score5.setScore(5);

        Score score4 = objective.getScore("     ");
        score4.setScore(4);

        Score score3 = objective.getScore("      ");
        score3.setScore(3);

        Score score2 = objective.getScore("       ");
        score2.setScore(2);

        Score score1 = objective.getScore("        ");
        score1.setScore(1);

        Score score0 = objective.getScore("         ");
        score0.setScore(0);

        player.setScoreboard(scoreboard);
    }

    public static void updateScore(Player player, String type, String newStats) {
        Scoreboard scoreboard = player.getScoreboard();
        Objective objective = scoreboard.getObjective("Stats");

        if (objective != null) {
            Score score = null;
            switch (type) {
                case "level":
                    score = objective.getScore(levels.getEntry());
                    scoreboard.resetScores(levels.getEntry());
                    Score newLevelScore = objective.getScore(newStats);
                    newLevelScore.setScore(9);
                    levels = newLevelScore;
                    break;

                case "exp":
                    score = objective.getScore(exp.getEntry());
                    scoreboard.resetScores(exp.getEntry());
                    Score newExpScore = objective.getScore(newStats);
                    newExpScore.setScore(8);
                    exp = newExpScore;
                    break;

                case "newExp":
                    score = objective.getScore(expToNextLevel.getEntry());
                    scoreboard.resetScores(expToNextLevel.getEntry());
                    Score newScore = objective.getScore(newStats);
                    newScore.setScore(7);
                    expToNextLevel = newScore;
                    break;
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getScoreboard() == Bukkit.getScoreboardManager().getMainScoreboard()) {
            createScoreboard(player);
        }
    }
}


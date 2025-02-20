package org.example.dmytrok.dkentityplugin.bosses.golembossGuardianOfColdLand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example.dmytrok.dkentityplugin.bosses.blazebossFireElementKing.BlazeBossEntity;

public class GolemBossCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }
        Player player = (Player) commandSender;

        if (!(player.hasPermission("golemBoss.admin"))) {
            commandSender.sendMessage("You must be an admin");
            return false;
        }
        Location location = player.getLocation();
        if (location == null || location.getWorld() == null) {
            commandSender.sendMessage("Error: Golem Boss can't be spawned in this place!");
            return false;
        }

        GolemBossEntity.trackArmorStandWithGolem( GolemBossEntity.summonGolemBoss(location));
        Bukkit.broadcastMessage("§bA furious frost has pierced your body...");
        return true;
    }
}


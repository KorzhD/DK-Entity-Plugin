package org.example.dmytrok.dkentityplugin.zombiebossMonarch;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ZombieBossCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }
        Player player = (Player) commandSender;

        if(!(player.hasPermission("zombieBoss.admin"))) {
            commandSender.sendMessage("You must be an admin");
            return false;
        }

         ZombieBossEntity.summonZombieBoss(player.getLocation());
        player.sendMessage("§5Monarch of Death has arrived..!");
        return true;
    }
}

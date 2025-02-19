package org.example.dmytrok.dkentityplugin.bosses.blazebossFireElementKing;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BlazeBossCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }
        Player player = (Player) commandSender;

        if (!(player.hasPermission("blazeBoss.admin"))) {
            commandSender.sendMessage("You must be an admin");
            return false;
        }
        Location location = player.getLocation();
        if(location == null || location.getWorld() == null) {
            commandSender.sendMessage("Error: Blaze Boss can't be spawned in this place!");
            return false;
        }

         BlazeBossEntity.summonBlazeBoss(location);
        player.sendMessage("§cKing of the Fire Element - burns everything to the ground!..");
        return true;
    }
}

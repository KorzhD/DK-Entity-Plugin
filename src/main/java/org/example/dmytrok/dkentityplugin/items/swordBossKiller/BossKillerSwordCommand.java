package org.example.dmytrok.dkentityplugin.items.swordBossKiller;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BossKillerSwordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }
        Player player = (Player) commandSender;

        if (!(player.hasPermission("bossKiller.admin"))) {
            commandSender.sendMessage("You must be an admin");
            return false;
        }

        player.sendMessage("§cAre you winning, son?");
        player.getInventory().addItem(BossKillerSword.getBossKiller());
        return true;
    }
}

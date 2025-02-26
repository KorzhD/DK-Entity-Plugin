package org.example.dmytrok.dkentityplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LastDropCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }

        Player player = (Player) commandSender;

        Inventory lastDropMenu = Bukkit.createInventory(player, 27, "§aLast Drop | Take it all now");

        Inventory playerLastDrop = null;

        if(!(BossDefeatMenu.getLastDrop(player) == null)) {
            playerLastDrop = BossDefeatMenu.getLastDrop(player);
            if (playerLastDrop != null) {
               for(ItemStack item : playerLastDrop.getContents()) {
                   if (item != null && item.getType() != Material.AIR) {
                       lastDropMenu.addItem(item);
                   }
               }
            } else {
                player.sendMessage("§cLast Drop inventory - is empty!");
                return true;
            }
        } else {
            player.sendMessage("§cLast Drop inventory - is empty!");
            return true;
        }
        player.openInventory(lastDropMenu);
        BossDefeatMenu.lastDrop.remove(player.getUniqueId());
        return true;
    }
}

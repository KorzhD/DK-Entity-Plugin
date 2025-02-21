package org.example.dmytrok.dkentityplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BossDefeatMenu {

    public static void bossInventory(HashMap<Player, Double> players, List<ItemStack> loot) {
        Set<Player> playerList = players.keySet();
        for(Player player : playerList) {
            Inventory bossInventory = Bukkit.createInventory(player, 18, "§aCongratulation!!");
            for (ItemStack item : loot) {
                bossInventory.addItem(item);
            }
            player.sendMessage("You hit: " + players.get(player));
            player.openInventory(bossInventory);
        }
    }
}

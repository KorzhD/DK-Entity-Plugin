package org.example.dmytrok.dkentityplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

public class PlayerDeath implements Listener {

    private static HashMap<Player, Inventory> playerRemovedItems = new HashMap<>();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.sendTitle("§4§lYou Died!", "§6Try better next time  ", 30, 30, 30);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Inventory inventory = player.getInventory();
        Random random = new Random();
        Inventory removedItems = Bukkit.createInventory(null, 9);

        int removedCount = 0;
        int attempts = 0;

        while (removedCount < 3 && attempts < 100) {
            int randomNumber = random.nextInt(36);
            ItemStack item = inventory.getItem(randomNumber);

            if (item != null && item.getType() != Material.AIR) {
                removedItems.addItem(item);
                inventory.setItem(randomNumber, new ItemStack(Material.AIR));
                removedCount++;
            }
            attempts++;
        }

        player.updateInventory();
        playerRemovedItems.put(player, removedItems);
    }


    public static HashMap<Player, Inventory> getPlayerItems() {
        return playerRemovedItems;
    }

}

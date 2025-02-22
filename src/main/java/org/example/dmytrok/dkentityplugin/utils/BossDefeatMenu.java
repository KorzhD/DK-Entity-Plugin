package org.example.dmytrok.dkentityplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkentityplugin.DK_Entity_Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BossDefeatMenu implements Listener {

    public static HashMap<UUID, Inventory> lastDrop = new HashMap<>();

    public static void setBossInventory(HashMap<Player, Double> players, List<ItemStack> loot) {
        Set<Player> playerList = players.keySet();
        for (Player player : playerList) {
            Inventory bossInventory = Bukkit.createInventory(player, 27, "§aCongratulation!!");
            for (ItemStack item : loot) {
                bossInventory.addItem(item);
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    Inventory bossInventoryWB = addButtons(bossInventory);
                    player.openInventory(bossInventoryWB);

                }
            }.runTaskLater(DK_Entity_Plugin.getInstance(),  120L);

        }
    }

    @EventHandler
    public void onBossMenuInventoryClick(InventoryClickEvent event) {
        if (!(isBossMenu(event.getInventory()))) {
            return;
        }
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        if (event.getClick().isKeyboardClick()) {
            event.setCancelled(true);
            return;
        }
        if (event.getClick().isRightClick()) {
            event.setCancelled(true);
            return;
        }
        if (event.getClick().isShiftClick()) {
            event.setCancelled(true);
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }


        if (clickedItem.getType().equals(Material.REDSTONE_BLOCK) && clickedItem.getItemMeta().getDisplayName().equals("§cDecline")) {
            player.closeInventory();
            player.sendMessage("§cYou declined reward");
        }
        if (clickedItem.getType().equals(Material.EMERALD_BLOCK) && clickedItem.getItemMeta().getDisplayName().equals("§aAccept")) {
            player.closeInventory();
            player.sendMessage("§aYou accepted reward");
            inventory.setItem(21, new ItemStack(Material.AIR));
            inventory.setItem(23, new ItemStack(Material.AIR));

            if (!(getOccupiedSlots(player) >= 36)) {
                for (ItemStack item : inventory.getContents()) {
                    if (item != null && item.getType() != Material.AIR) {
                        player.getInventory().addItem(item);
                    }
                }
            } else {
                player.sendMessage("§cYour inventory is full! \n" +
                        "§eWe put the drop in a special place - /lastdrop :)");
                    lastDrop.put(player.getUniqueId(), inventory);
            }
        }

        event.setCancelled(true);
    }


    private boolean isBossMenu(Inventory inventory) {
        if (inventory == null) {
            return false;
        }
        if (!(inventory.getTitle().equals("§aCongratulation!!"))) {
            return false;
        }

        return true;
    }

    private static Inventory addButtons(Inventory inventory) {
        ItemStack cancel = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName("§cDecline");
        cancel.setItemMeta(cancelMeta);

        ItemStack accept = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta acceptMeta = accept.getItemMeta();
        acceptMeta.setDisplayName("§aAccept");
        accept.setItemMeta(acceptMeta);

        inventory.setItem(21, cancel);
        inventory.setItem(23, accept);

        return inventory;
    }

    public int getOccupiedSlots(Player player) {
        int occupied = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() != Material.AIR) {
                occupied++;
            }
        }

        return occupied;
    }

    public static Inventory getLastDrop(Player player) {
        return lastDrop.get(player.getUniqueId());
    }
}

package org.example.dmytrok.dkserverplugin.MENU.playermenu.personalAccount;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.List;


public class PersonalAccountCommand implements CommandExecutor {

    private static HashMap<Player, List<String>> personalStatisticLore = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }

        Player player = (Player) commandSender;

        Inventory personalAccount = Bukkit.createInventory(player, getRows(5),"§9Personal Account");

        //

        // PickUp

        ItemStack pickUpOff = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta pickUpOffMeta = pickUpOff.getItemMeta();
        pickUpOffMeta.setDisplayName("§aEnable Pick Up Messages");
        pickUpOff.setItemMeta(pickUpOffMeta);

        ItemStack pickUpOn = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta pickUpOnMeta = pickUpOn.getItemMeta();
        pickUpOnMeta.setDisplayName("§cDisable Pick Up Messages");
        pickUpOn.setItemMeta(pickUpOnMeta);

        // PickUp

        //

        // Entity

        ItemStack lastDrop = new ItemStack(Material.CHEST);
        ItemMeta ldMeta = lastDrop.getItemMeta();
        ldMeta.setDisplayName("§a§lLast Drop");
        lastDrop.setItemMeta(ldMeta);

        // Entity

        //

        // Player Invisible

        ItemStack playerInvOn = new ItemStack(Material.GLASS_BOTTLE);
        ItemMeta playerInvOnMeta = playerInvOn.getItemMeta();
        playerInvOnMeta.setDisplayName("§aDisable Players invisible");
        playerInvOn.setItemMeta(playerInvOnMeta);

        ItemStack playerInvOff = new ItemStack(Material.DRAGONS_BREATH);
        ItemMeta playerInvOffMeta = playerInvOff.getItemMeta();
        playerInvOffMeta.setDisplayName("§cEnable Players invisible");
        playerInvOff.setItemMeta(playerInvOffMeta);

        // Player Invisible

        //

        // Personal Statistic

        ItemStack playerStatistic = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) playerStatistic.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.setOwner(player.getName());
        skullMeta.setDisplayName("§6" + player.getDisplayName() + "'s Statistic");
        playerStatistic.setItemMeta(skullMeta);

        // Personal Statistic

        //

        // Back Button

        ItemStack backButton = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta backMeta = backButton.getItemMeta();
        backMeta.setDisplayName("§c<- Back");
        backButton.setItemMeta(backMeta);

        // Back Button

        //

        personalAccount.setItem(44, lastDrop);

        String setting = null;

        if (!(PersonalAccountEvents.getPickUpSetting(player) == null)) {
            setting = PersonalAccountEvents.getPickUpSetting(player);
            if (setting.equals("disable")) {
                personalAccount.setItem(36, pickUpOff);
            } else if (setting.equals("enable")) {
                personalAccount.setItem(36, pickUpOn);
            }
        } else {
            setting = "disable";
        }
        if (setting.equals("disable")) {
            personalAccount.setItem(36, pickUpOff);
        } else if (setting.equals("enable")) {
            personalAccount.setItem(36, pickUpOn);
        }

        String playerInvSetting = null;

        if (!(PersonalAccountEvents.getPlayerInvSetting(player) == null)) {
            playerInvSetting = PersonalAccountEvents.getPlayerInvSetting(player);
            if (playerInvSetting.equals("disable")) {;
                personalAccount.setItem(37, playerInvOff);
            } else if (playerInvSetting.equals("enable")) {
                personalAccount.setItem(37, playerInvOn);
            }
        } else {
            playerInvSetting = "disable";
        }
        if (playerInvSetting.equals("disable")) {
            personalAccount.setItem(37, playerInvOff);
        } else if (playerInvSetting.equals("enable")) {
            personalAccount.setItem(37, playerInvOn);
        }


        personalAccount.setItem(22, playerStatistic);
        personalAccount.setItem(0, backButton);


        player.openInventory(personalAccount);
        return true;
    }

    private int getRows(int numberOfRows) {
        return numberOfRows * 9;
    }


}

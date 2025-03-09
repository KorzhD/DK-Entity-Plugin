package org.example.dmytrok.dkserverplugin.LEVELSYSTEM;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SwordLevelCheck implements Listener {

    private boolean isCustomSword(ItemStack item) {
        return item != null && item.getType() == Material.WOOD_SWORD;
    }

    private int getRequiredLevel(ItemStack item) {
        if (!isCustomSword(item)) {
            return 0;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasLore()) {
            return 0;
        }

        List<String> lore = meta.getLore();
        if (lore == null)  {
            return 0;
        }

        for (String line : lore) {
            if (line.contains("§a§lLvl: §e")) {
                try {
                    return Integer.parseInt(line.replace("§a§lLvl: §e", "").trim());
                } catch (NumberFormatException ignored) {}
            }
        }
        return 0;
    }

    private boolean isLevelTooLow(Player player, ItemStack item) {
        if (!isCustomSword(item))  {
            return false;
        }
        int requiredLevel = getRequiredLevel(item);
        return requiredLevel > LevelSystem.getLevel(player);
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            Player player = (Player) damager;
            ItemStack item = player.getInventory().getItemInMainHand();

            if (isLevelTooLow(player, item)) {
                player.sendTitle("§4§lToo low lvl!", "",15, 15, 15);
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (isLevelTooLow(player, item)) {
            player.sendTitle("§4§lToo low lvl!", "",15, 15, 15);
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerSwitchItem(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItem(event.getNewSlot());
        if (isLevelTooLow(player, item)) {
            player.sendTitle("§4§lToo low lvl!", "",15, 15, 15);
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
            event.setCancelled(true);
        }
    }
}

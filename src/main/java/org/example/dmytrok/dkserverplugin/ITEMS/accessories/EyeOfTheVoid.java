package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import java.util.Random;

public class EyeOfTheVoid implements Listener {
    private final Random random = new Random();

    @EventHandler
    public void onPlayerDeath(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))  {
            return;
        }
        Player player = (Player) event.getEntity();
        double finalHealth = player.getHealth() - event.getFinalDamage();
        if(finalHealth <= 0) {
        if (isEyeOfTheVoidEquipped(player) && random.nextInt(100) < 10) {
            event.setCancelled(true);
            player.setHealth(2.0);
            player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
            player.sendMessage("§5The void has spared you...");
        }
        }
    }

    private boolean isEyeOfTheVoidEquipped(Player player) {
        ItemStack itemInSlot9 = player.getInventory().getItem(9);
        ItemStack itemInSlot10 = player.getInventory().getItem(10);
        ItemStack itemInSlot11 = player.getInventory().getItem(11);

        return isEyeOfTheVoid(itemInSlot9) || isEyeOfTheVoid(itemInSlot10) || isEyeOfTheVoid(itemInSlot11);
    }

    private boolean isEyeOfTheVoid(ItemStack item) {
        return item != null && item.getType() == Material.CHORUS_FRUIT_POPPED && item.getItemMeta().getDisplayName().equals("Eye of The Void");
    }
}
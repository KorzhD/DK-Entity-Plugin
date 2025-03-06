package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class RingOfTheFaceless implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (isRingOfTheFacelessEquipped(player)) {
                double currentHealth = player.getHealth();
                double maxHealth = player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                double healthRatio = 1 - (currentHealth / maxHealth);
                double damageReduction = Math.min(healthRatio * 0.6, 0.6);

                double reducedDamage = event.getDamage() * (1 - damageReduction);
                event.setDamage(reducedDamage);
            }
        }
    }

    private boolean isRingOfTheFacelessEquipped(Player player) {
        ItemStack itemInSlot9 = player.getInventory().getItem(9);
        ItemStack itemInSlot10 = player.getInventory().getItem(10);
        ItemStack itemInSlot11 = player.getInventory().getItem(11);

        return isRingOfTheFaceless(itemInSlot9) ||  isRingOfTheFaceless(itemInSlot10) || isRingOfTheFaceless(itemInSlot11);
    }

    private boolean isRingOfTheFaceless(ItemStack item) {
        return item != null && item.getType() == Material.CHORUS_FRUIT_POPPED && item.getItemMeta().getDisplayName().equals("Ring of The Faceless");
    }
}
package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;


public class FrozenEyeRing implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (isFrozenEyeRingEquipped(player) && (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE)) {
                event.setDamage(event.getDamage() / 2);
            }
        }
    }

    private boolean isFrozenEyeRingEquipped(Player player) {
        ItemStack itemInSlot9 = player.getInventory().getItem(9);
        ItemStack itemInSlot10 = player.getInventory().getItem(10);
        ItemStack itemInSlot11 = player.getInventory().getItem(11);

        return isFrozenEyeRing(itemInSlot9) || isFrozenEyeRing(itemInSlot10) || isFrozenEyeRing(itemInSlot11);
    }

    private boolean isFrozenEyeRing(ItemStack item) {
        return item != null && item.getType() == Material.CHORUS_FRUIT_POPPED && item.getItemMeta().getDisplayName().equals("Frozen Eye Ring");
    }

}

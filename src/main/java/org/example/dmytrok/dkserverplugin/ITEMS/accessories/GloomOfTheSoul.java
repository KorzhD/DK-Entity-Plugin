package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class GloomOfTheSoul implements Listener {

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        if (isGloomOfTheSoulEquipped(player)) {
            double currentHealth = player.getHealth();
            double maxHealth = player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            double healthRatio = 1 - (currentHealth / maxHealth);

            double attackMultiplier = 1 + Math.min(healthRatio * 0.5, 0.5);
            double increasedAttack = event.getDamage() * attackMultiplier;

            event.setDamage(increasedAttack);
        }
    }


    private boolean isGloomOfTheSoulEquipped(Player player) {
        ItemStack itemInSlot9 = player.getInventory().getItem(9);
        ItemStack itemInSlot10 = player.getInventory().getItem(10);
        ItemStack itemInSlot11 = player.getInventory().getItem(11);

        return isGloomOfTheSoul(itemInSlot9) || isGloomOfTheSoul(itemInSlot10) ||  isGloomOfTheSoul(itemInSlot11);
    }

    private boolean isGloomOfTheSoul(ItemStack item) {
        return item != null && item.getType() == Material.CHORUS_FRUIT_POPPED && item.getItemMeta().getDisplayName().equals("Gloom of The Soul");
    }
}

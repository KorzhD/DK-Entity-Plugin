package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DragonsSparkRing implements Listener {

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (isDragonsSparkRingEquipped(player) && event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof Player) && (!(event.getEntity() instanceof ArmorStand))) {
                LivingEntity target = (LivingEntity) event.getEntity();
                target.setFireTicks(60);
            }
        }
    }
    private boolean isDragonsSparkRingEquipped(Player player) {
        ItemStack itemInSlot9 = player.getInventory().getItem(9);
        ItemStack itemInSlot10 = player.getInventory().getItem(10);
        ItemStack itemInSlot11 = player.getInventory().getItem(11);

        return isDragonsSparkRing(itemInSlot9) || isDragonsSparkRing(itemInSlot10) || isDragonsSparkRing(itemInSlot11);
    }

    private boolean isDragonsSparkRing(ItemStack item) {
        return item != null && item.getType() == Material.CHORUS_FRUIT_POPPED && item.getItemMeta().getDisplayName().equals("Dragon's Spark Ring");
    }
}


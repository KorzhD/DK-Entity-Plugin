package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class FlameJudgeRing implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (isFlameJudgeRingEquipped(player)) {
            for (Entity entity : player.getNearbyEntities(4, 2, 4)) {
                if (entity instanceof LivingEntity) {
                    if(!(entity instanceof Player)) {
                        if(!(entity instanceof ArmorStand)) {
                        LivingEntity target = (LivingEntity) entity;
                        target.damage(0.5);
                        target.setFireTicks(40);
                        }
                    }
                }
            }
        }
    }

    private boolean isFlameJudgeRingEquipped(Player player) {
        ItemStack itemInSlot9 = player.getInventory().getItem(9);
        ItemStack itemInSlot10 = player.getInventory().getItem(10);
        ItemStack itemInSlot11 = player.getInventory().getItem(11);

        return isFlameJudgeRing(itemInSlot9) || isFlameJudgeRing(itemInSlot10) || isFlameJudgeRing(itemInSlot11);
    }

    private boolean isFlameJudgeRing(ItemStack item) {
        return item != null && item.getType() == Material.CHORUS_FRUIT_POPPED && item.getItemMeta().getDisplayName().equals("Flame Judge Ring");
    }
}

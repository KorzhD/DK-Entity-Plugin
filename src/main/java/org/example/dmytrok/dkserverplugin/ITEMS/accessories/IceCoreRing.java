package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.ItemStack;

public class IceCoreRing implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (isIceCoreRingEquipped(player)) {
            for (Entity entity : player.getNearbyEntities(2, 2, 2)) {
                if (entity instanceof LivingEntity) {
                    if(!(entity instanceof Player)) {
                        if(!(entity instanceof ArmorStand)) {
                            LivingEntity livingEntity = (LivingEntity) entity;
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (isIceCoreRingEquipped(player) && (event.getCause() == EntityDamageEvent.DamageCause.FIRE
                    || event.getCause() == EntityDamageEvent.DamageCause.LAVA)
                    || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                event.setDamage(event.getDamage() / 1.25);
            }
        }
    }

    private boolean isIceCoreRingEquipped(Player player) {
        ItemStack itemInSlot9 = player.getInventory().getItem(9);
        ItemStack itemInSlot10 = player.getInventory().getItem(10);
        ItemStack itemInSlot11 = player.getInventory().getItem(11);

        return isIceCoreRing(itemInSlot9) || isIceCoreRing(itemInSlot10) || isIceCoreRing(itemInSlot11);
    }

    private boolean isIceCoreRing(ItemStack item) {
        return item != null && item.getType() == Material.CHORUS_FRUIT_POPPED && item.getItemMeta().getDisplayName().equals("Ice Core Ring");
    }
}

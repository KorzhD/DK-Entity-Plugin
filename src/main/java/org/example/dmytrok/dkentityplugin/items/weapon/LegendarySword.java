package org.example.dmytrok.dkentityplugin.items.weapon;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class LegendarySword implements Listener {
    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Legendary Sword"))) {
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR)) {
            return;
        }
        World world = player.getWorld();
        Location location = player.getLocation();

        List<Entity> entities = player.getNearbyEntities(5, 5, 5);
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                if (entity instanceof Player) {
                    player.playSound(location, Sound.ENTITY_WITHER_HURT, 0.5f, 3);
                    ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2, false, false));
                    ((Player) entity).playSound(entity.getLocation(), Sound.ENTITY_CAT_PURREOW, 0.5f, 3);
                    for (int i = 0; i < 5; i++) {
                        world.spawnParticle(Particle.VILLAGER_HAPPY, entity.getLocation().add(0, 1, 0), 5);
                    }
                }
            }
        }
    }
    private boolean isWoodSword(ItemStack itemStack) {
        if (itemStack.getType().equals(Material.WOOD_SWORD)) {
            return true;
        }
        return false;
    }
}

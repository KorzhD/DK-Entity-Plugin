package org.example.dmytrok.dkentityplugin.items.weapon;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkentityplugin.DK_Entity_Plugin;

import java.util.HashMap;
import java.util.Map;

public class Moonlight implements Listener {

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 20000;

    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Moonlight"))) {
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR)) {
            return;
        }

        if (isOnCooldown(player)) {
            player.sendMessage("§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
            return;
        }

        giveShieldEffect(player);

        setCooldown(player);
    }

    private boolean isWoodSword(ItemStack itemStack) {
        if (itemStack.getType().equals(Material.WOOD_SWORD)) {
            return true;
        }
        return false;
    }

    private boolean isOnCooldown(Player player) {
        if (!cooldowns.containsKey(player)) {
            return false;
        }
        long lastUsed = cooldowns.get(player);
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastUsed) < cooldownTime;
    }

    private void setCooldown(Player player) {
        cooldowns.put(player, System.currentTimeMillis());
    }

    private long getCooldownTimeLeft(Player player) {
        if (!cooldowns.containsKey(player)) {
            return 0;
        }
        long lastUsed = cooldowns.get(player);
        long currentTime = System.currentTimeMillis();
        long timeLeft = cooldownTime - (currentTime - lastUsed);
        return Math.max(0, timeLeft / 1000);
    }

    private void giveShieldEffect(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 140, 0, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 140, 4, false, false));
        player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1);

        player.setInvulnerable(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setInvulnerable(false);
                player.removePotionEffect(PotionEffectType.GLOWING);
                player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            }
        }.runTaskLater(DK_Entity_Plugin.getInstance(), 140);
    }

}


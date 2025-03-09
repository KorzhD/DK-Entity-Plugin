package org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;
import org.example.dmytrok.dkserverplugin.LEVELSYSTEM.LevelCheck;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BloodyDeath implements Listener {
    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 40000;
    private final long berserkDuration = 4000;

    private final Map<Player, Boolean> berserkActive = new HashMap<>();
    private final Map<Player, Integer> berserkDamage = new HashMap<>();

    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Bloody Death"))) {
            return;
        }
        if (isOnCooldown(player)) {
            if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_AIR)) {
                return;
            }
            player.sendMessage("§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 1);
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if(LevelCheck.isLevelTooLow(player, item)) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.isSneaking()) {
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_POLAR_BEAR_HURT, 2, 1);
                activateBerserk(player);
            } else {
                setCooldown(player);
            }
        }
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        if(event.getEntity() instanceof ArmorStand) {
            return;
        }

        Player player = (Player) event.getDamager();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!isWoodSword(item) || !item.getItemMeta().getDisplayName().equals("Bloody Death")) {
            return;
        }

        if (berserkActive.getOrDefault(player, false)) {
            int bonusDamage = berserkDamage.getOrDefault(player, 2);
            event.setDamage(event.getDamage() + bonusDamage);
            berserkDamage.put(player, bonusDamage + 2);
        }
    }

    private void activateBerserk(Player player) {
        player.sendTitle("§6§lCombo!", "§n§4Berserk Mode", 15, 15, 15);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDEREYE_DEATH, 3, 1);
        berserkActive.put(player, true);
        berserkDamage.put(player, 2);
        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
        player.getWorld().strikeLightningEffect(player.getLocation());
        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
        Collection<Entity> nearby = player.getNearbyEntities(5, 3, 5);
        for (Entity entity : nearby) {
            if (entity instanceof Player) {
                ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                berserkActive.remove(player);
                berserkDamage.remove(player);
                player.sendMessage("§7§lBerserk mode ended.");
                setCooldown(player);
            }
        }.runTaskLater(DK_Server_Plugin.getInstance(), berserkDuration / 50);
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
}

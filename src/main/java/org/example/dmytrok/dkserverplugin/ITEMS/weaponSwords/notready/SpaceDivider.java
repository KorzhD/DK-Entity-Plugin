package org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.notready;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.dmytrok.dkserverplugin.DK_Server_Plugin;

import java.util.*;

public class SpaceDivider implements Listener {

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 40000;
    private final Map<UUID, List<ArmorStand>> activeSwords = new HashMap<>();
    private final Map<UUID, List<ArmorStand>> flyingSwords = new HashMap<>();
    private final long swordDuration = 300L;
    private final long flightDuration = 100L;

    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!isMagicSword(item)) return;

        if (isOnCooldown(player)) {
            if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_AIR)) {
                return;
            }
            player.sendMessage("§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 1);
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(activeSwords.get(player.getUniqueId()) != null) {
                return;
            }
            spawnSwordsAroundPlayer(player);
        } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            launchSwords(player);
            setCooldown(player);
        }
    }

    private void spawnSwordsAroundPlayer(Player player) {
        player.sendTitle("§6§lCombo!", "§dGate to the Armory", 15, 15, 15);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDEREYE_DEATH, 3, 1);
        List<ArmorStand> swords = new ArrayList<>();
        World world = player.getWorld();

        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians((360 / 6) * i);
            double x = Math.cos(angle) * 2;
            double z = Math.sin(angle) * 2;

            Location loc = player.getLocation().clone().add(x, 1.5, z);
            ArmorStand sword = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
            sword.setVisible(false);
            sword.setGravity(false);
            sword.setArms(true);
            ItemStack swordStack = new ItemStack(Material.WOOD_SWORD);
            ItemMeta swordMeta = swordStack.getItemMeta();
            swordMeta.setDisplayName("Space Divider");
            swordStack.setItemMeta(swordMeta);
            sword.setItemInHand(swordStack);

            swords.add(sword);
        }

        activeSwords.put(player.getUniqueId(), swords);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!activeSwords.containsKey(player.getUniqueId())) {
                    cancel();
                    return;
                }
                Location playerLoc = player.getLocation();
                for (int i = 0; i < swords.size(); i++) {
                    double angle = Math.toRadians((360 / 6) * i);
                    double x = Math.cos(angle) * 2;
                    double z = Math.sin(angle) * 2;
                    swords.get(i).teleport(playerLoc.clone().add(x, 1.5, z));
                }
            }
        }.runTaskTimer(DK_Server_Plugin.getInstance(), 0, 5);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (activeSwords.containsKey(player.getUniqueId())) {
                    for (ArmorStand sword : activeSwords.get(player.getUniqueId())) {
                        sword.remove();
                    }
                    activeSwords.remove(player.getUniqueId());
                }
            }
        }.runTaskLater(DK_Server_Plugin.getInstance(), swordDuration);
    }

    private void launchSwords(Player player) {
        if (!activeSwords.containsKey(player.getUniqueId())) return;

        List<ArmorStand> swords = activeSwords.remove(player.getUniqueId());
        flyingSwords.put(player.getUniqueId(), swords);
        Vector direction = player.getLocation().getDirection().normalize().multiply(0.8);

        for (ArmorStand sword : swords) {
            new BukkitRunnable() {
                int ticks = 0;

                @Override
                public void run() {
                    if (ticks++ >= flightDuration || !sword.isValid()) {
                        sword.remove();
                        flyingSwords.get(player.getUniqueId()).remove(sword);
                        cancel();
                        return;
                    }

                    sword.teleport(sword.getLocation().add(direction));
                    sword.getWorld().playEffect(sword.getLocation(), Effect.SMOKE, 1);
                    damageNearbyEntities(sword);
                }
            }.runTaskTimer(DK_Server_Plugin.getInstance(), 0, 2);
        }
    }

    private void damageNearbyEntities(ArmorStand sword) {
        Location loc = sword.getLocation();
        for (Entity entity : loc.getWorld().getNearbyEntities(loc, 2, 2, 2)) {
            if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                if(!(entity instanceof ArmorStand)) {
                    LivingEntity target = (LivingEntity) entity;
                    target.damage(10);
                    target.getWorld().playSound(target.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.0f);
                    sword.remove();
                    break;
                }
            }
        }
    }

    private boolean isMagicSword(ItemStack item) {
        if (item == null || item.getType() != Material.WOOD_SWORD || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        return meta.hasDisplayName() && meta.getDisplayName().equals("Space Divider");
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
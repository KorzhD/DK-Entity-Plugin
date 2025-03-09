package org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords;

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

public class ShadowScythe implements Listener {
        private final Map<Player, Long> cooldowns = new HashMap<>();
        private final long cooldownTime = 45000;
        private final long voidDuration = 500L;

        @EventHandler
        public void onWeaponUse(PlayerInteractEvent event) {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();

            if (!isShadowScythe(item)) return;

            if (isOnCooldown(player)) {
                if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                    return;
                }
                player.sendMessage("§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 1);
                return;
            }

            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                createVoidZone(player);
                setCooldown(player);
            }
        }

        private void createVoidZone(Player player) {
            player.sendTitle("§6§lCombo!", "§8Black Hole", 15, 15, 15);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDEREYE_DEATH, 3, 1);
            Location loc = player.getLocation().add(player.getLocation().getDirection().multiply(5));
            World world = loc.getWorld();

            ArmorStand blackHole = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
            blackHole.setVisible(false);
            blackHole.setGravity(false);
            blackHole.setHelmet(new ItemStack(Material.COAL_BLOCK));

            world.playSound(loc, Sound.ENTITY_ENDERDRAGON_GROWL, 2, 1);

            new BukkitRunnable() {
                int ticks = 0;

                @Override
                public void run() {
                    if (ticks++ >= voidDuration) {
                        if (blackHole != null && !blackHole.isDead()) {
                            blackHole.remove();
                        }
                        cancel();
                        return;
                    }
                    if (blackHole == null || blackHole.isDead()) {
                        cancel();
                        return;
                    }
                    world.spawnParticle(Particle.PORTAL, blackHole.getLocation(), 50, 1, 1, 1, 0.1);
                    Collection<Entity> list = world.getNearbyEntities(blackHole.getLocation(), 7, 3, 7);
                    for(Entity entity : list) {
                        if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                            if(!(entity instanceof ArmorStand)) {
                                Vector pull = blackHole.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize().multiply(0.3);
                                entity.setVelocity(pull);
                                ((LivingEntity) entity).damage(2);
                                world.playSound(entity.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
                            }
                        }
                        ticks += 5;
                    }
                }
            }.runTaskTimer(DK_Server_Plugin.getInstance(), 0, 10);
        }

        private boolean isShadowScythe(ItemStack item) {
            if (item == null || item.getType() != Material.WOOD_SWORD || !item.hasItemMeta()) return false;
            ItemMeta meta = item.getItemMeta();
            return meta.hasDisplayName() && meta.getDisplayName().equals("Shadow Scythe");
        }

        private boolean isOnCooldown(Player player) {
            return cooldowns.containsKey(player) && (System.currentTimeMillis() - cooldowns.get(player)) < cooldownTime;
        }

        private void setCooldown(Player player) {
            cooldowns.put(player, System.currentTimeMillis());
        }

        private long getCooldownTimeLeft(Player player) {
            return Math.max(0, (cooldownTime - (System.currentTimeMillis() - cooldowns.getOrDefault(player, 0L))) / 1000);
        }
    }


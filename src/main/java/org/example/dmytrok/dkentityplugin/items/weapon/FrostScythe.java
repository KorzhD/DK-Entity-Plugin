package org.example.dmytrok.dkentityplugin.items.weapon;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkentityplugin.DK_Entity_Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrostScythe implements Listener {
    private final Map<LivingEntity, ArmorStand[]> snowflakesMap = new HashMap<>();
    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 20000;

    @EventHandler
    public void onWeaponUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(isWoodSword(player.getInventory().getItemInMainHand()))) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta() != null &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Frost Scythe"))) {
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR)) {
            return;
        }

        if (isOnCooldown(player)) {
            player.sendMessage( "§4§lRecharge: " + getCooldownTimeLeft(player) + " sec");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 2, 200);
            return;
        }
        Location location = player.getLocation();
        List<Entity> entities = player.getNearbyEntities(5, 5, 5);

        if (player.isSneaking()) {
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity) {
                    if (!(entity instanceof Player)) {
                        player.playSound(location, Sound.ENTITY_WITHER_HURT, 0.5f, 200);
                        ((LivingEntity) entity).addPotionEffect(PotionEffectType.SLOW.createEffect(200, 100));
                        LivingEntity livingEntity = (LivingEntity) entity;
                        createSnowflakeEffect(livingEntity);
                    }
                }
            }
            setCooldown(player);
        }
    }

    private boolean isWoodSword(ItemStack itemStack) {
        if (itemStack.getType().equals(Material.WOOD_SWORD)) {
            return true;
        }
        return false;
    }

    private void createSnowflakeEffect(LivingEntity entity) {
        if (entity.getType().equals(EntityType.ARMOR_STAND)) {
            return;
        }
        Location location = entity.getLocation().clone().add(0, -1, 0);
        double radius = 2;
        int points = 8;

        ArmorStand[] snowflakes = new ArmorStand[points * 2];

        for (int i = 0; i < points; i++) {
            double angle = Math.toRadians((360.0 / points) * i);
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;

            Location standLocation = location.clone().add(x, 0, z);

            ArmorStand armorStand = location.getWorld().spawn(standLocation, ArmorStand.class);
            armorStand.setCustomName("snowflake");
            armorStand.setCustomNameVisible(false);
            armorStand.setVisible(false);
            armorStand.setInvulnerable(true);
            armorStand.setGravity(false);
            armorStand.setCustomNameVisible(false);
            armorStand.setBasePlate(false);
            armorStand.setSmall(true);
            armorStand.getEquipment().setHelmet(new ItemStack(Material.ICE));
            armorStand.setCanPickupItems(false);

            snowflakes[i] = armorStand;
        }

        for (int i = 0; i < points; i++) {
            double angle = Math.toRadians((360.0 / points) * i);
            double x = Math.cos(angle) * radius * 0.66;
            double z = Math.sin(angle) * radius * 0.66;

            Location standLocation = location.clone().add(x, 0, z);

            ArmorStand armorStand = location.getWorld().spawn(standLocation, ArmorStand.class);
            armorStand.setCustomName("snowflake");
            armorStand.setCustomNameVisible(false);
            armorStand.setVisible(false);
            armorStand.setInvulnerable(true);
            armorStand.setGravity(false);
            armorStand.setCustomNameVisible(false);
            armorStand.setBasePlate(false);
            armorStand.setSmall(true);
            armorStand.getEquipment().setHelmet(new ItemStack(Material.ICE));
            armorStand.setCanPickupItems(false);


            snowflakes[points + i] = armorStand;
        }

        snowflakesMap.put(entity, snowflakes);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!entity.hasPotionEffect(PotionEffectType.SLOW)) {
                    this.cancel();
                    removeSnowflakes(entity);
                }
            }
        }.runTaskTimer(DK_Entity_Plugin.getInstance(), 0, 20);
    }

    private void removeSnowflakes(LivingEntity entity) {
        ArmorStand[] snowflakes = snowflakesMap.remove(entity);
        if (snowflakes != null) {
            for (ArmorStand snowflake : snowflakes) {
                if (snowflake != null) {
                    snowflake.remove();
                }
            }
        }
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

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        removeSnowflakes(entity);
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




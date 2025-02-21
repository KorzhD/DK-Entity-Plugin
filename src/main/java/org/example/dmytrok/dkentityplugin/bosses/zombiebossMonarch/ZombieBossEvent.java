package org.example.dmytrok.dkentityplugin.bosses.zombiebossMonarch;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkentityplugin.DK_Entity_Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZombieBossEvent implements Listener {

    @EventHandler
    public void onMonarchDeath(EntityDeathEvent event) {
        if (!isMonarch(event.getEntity())) {
            return;
        }
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();
        world.playSound(location, Sound.ENTITY_ENDERDRAGON_DEATH, 0.5f, 50);
        event.getDrops().clear();

        //Boss
        Random random = new Random();
        int dropChance = random.nextInt(500);
        if(dropChance == 1) {
        }
        //Drop


        ItemStack diamond = new ItemStack(Material.DIAMOND, 10);
        ItemStack emeralds = new ItemStack(Material.EMERALD, 10);
        ItemStack gold = new ItemStack(Material.GOLD_INGOT, 10);

            Bukkit.broadcastMessage("§5Monarch of Death - did not please Death..!");

        world.dropItem(location, diamond);
        world.dropItem(location, emeralds);
        world.dropItem(location, gold);

        if (ZombieBossEntity.getZombieBossBar() != null) {
            ZombieBossEntity.getZombieBossBar().removeAll();
            ZombieBossEntity.getZombieBossBar().setVisible(false);
        }
    }

    @EventHandler
    public void onPlayerAttackMonarch(EntityDamageEvent event) {
        if (!isMonarch(event.getEntity())) {
            return;
        }
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();

        world.spawnParticle(Particle.DRAGON_BREATH, location, 20);
        Zombie zombieBoss = (Zombie) event.getEntity();

        Random random = new Random();
        int x = location.getBlockX() + random.nextInt(4);
        int y = location.getBlockY();
        int z = location.getBlockZ() + random.nextInt(4);
        Location newLocation = new Location(world, x, y ,z);
        if(newLocation.getBlock().getType().equals(Material.AIR)) {
            zombieBoss.teleport(newLocation);
        }

        BossBar bossBar = ZombieBossEntity.getZombieBossBar();
        if (bossBar != null) {
            double maxHealth = zombieBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            double healthAfterDamage = zombieBoss.getHealth() - event.getFinalDamage();
            healthAfterDamage = Math.max(0, healthAfterDamage);
            bossBar.setProgress(healthAfterDamage / maxHealth);
        }
    }

    @EventHandler
    public void onMonarchAttackPlayer(EntityDamageByEntityEvent event) {
        if (!isMonarch(event.getDamager())) {
            return;
        }
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Location tpPlayerLoc = player.getLocation().add(0, 5, 0);
            player.teleport(tpPlayerLoc);
        } else {
            Entity entity = event.getEntity();
            Location tpEntityLoc = entity.getLocation().add(0, 5, 0);
            entity.teleport(tpEntityLoc);
        }

    }



    private boolean isMonarch(Entity entity) {
        if (!(entity.getType().equals(EntityType.ZOMBIE))) {
             return false;
        }
        Zombie zombie = (Zombie) entity;
        if (!(zombie.getCustomName().equals("§l§5Monarch"))){
            return false;
        }
        return true;
    }
}

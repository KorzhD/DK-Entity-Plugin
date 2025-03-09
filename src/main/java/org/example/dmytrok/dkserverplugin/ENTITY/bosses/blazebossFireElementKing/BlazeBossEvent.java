package org.example.dmytrok.dkserverplugin.ENTITY.bosses.blazebossFireElementKing;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.example.dmytrok.dkserverplugin.LEVELSYSTEM.LevelSystem;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.SwordCreator;
import org.example.dmytrok.dkserverplugin.UTILS.BossDefeatMenu;
import org.example.dmytrok.dkserverplugin.UTILS.CongratulationTitle;


import java.util.*;

public class BlazeBossEvent implements Listener {

    private HashMap<Player, Double> damagerPlayers = new HashMap<>();

    @EventHandler
    public void onFEKDeath(EntityDeathEvent event) {
        if (!(isFireElementKing(event.getEntity()))) {
            return;
        }
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();
        world.playSound(location, Sound.ENTITY_ENDERDRAGON_DEATH, 0.5f, 50);
        event.getDrops().clear();


        //Boss
        Random random = new Random();
        ItemStack rareDrop = new ItemStack(Material.AIR);
        int dropChance = random.nextInt(500);
        if (dropChance <= 5) {
            rareDrop = SwordCreator.getDemonSlayer();
        }

        ItemStack rareDrop2 = new ItemStack(Material.AIR);
        int dropChance2 = random.nextInt(100);
        if (dropChance <= 5) {
            rareDrop2 = SwordCreator.getDemonBlade();
        }
        //Drop

        ItemStack[] itemStacks = new ItemStack[]{new ItemStack(Material.DIAMOND, 30),
                new ItemStack(Material.EMERALD, 30),
                new ItemStack(Material.GOLD_INGOT, 30),
                rareDrop, rareDrop2
        };

        List<ItemStack> loot = getLootList(itemStacks);
        BossDefeatMenu.setBossInventory(damagerPlayers, loot);


        Bukkit.broadcastMessage("§cFire Element King - extinguished!");

        if (BlazeBossEntity.getBlazeBossBar() != null) {
            BlazeBossEntity.getBlazeBossBar().removeAll();
            BlazeBossEntity.getBlazeBossBar().setVisible(false);
        }

        CongratulationTitle.displayCongratulation(damagerPlayers);
        for(Player player : damagerPlayers.keySet()) {
            LevelSystem.addExp(player, 100);
        }
        damagerPlayers.clear();
    }

    @EventHandler
    public void onPlayerAttackFEK(EntityDamageByEntityEvent event) {
        if (!(isFireElementKing(event.getEntity()))) {
            return;
        }
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player attacker = (Player) event.getDamager();
        double damage = event.getDamage();

        if(!damagerPlayers.containsKey(attacker)) {
            damagerPlayers.put(attacker, damage);
        } else {
            double playersDamage = damagerPlayers.get(attacker);
            playersDamage = playersDamage + damage;
            damagerPlayers.put(attacker, playersDamage);
        }
    }

    @EventHandler
    public void onPlayerAttackFEK(EntityDamageEvent event) {
        if (!isFireElementKing(event.getEntity())) {
            return;
        }

        Blaze blazeBoss = (Blaze) event.getEntity();
        BossBar bossBar = BlazeBossEntity.getBlazeBossBar();

        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION ||
                event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
            event.setCancelled(true);
            if (bossBar != null) {
                bossBar.setProgress(blazeBoss.getHealth());
            }
        }
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();

        world.spawnParticle(Particle.FLAME, location, 20);
        if (bossBar != null) {
            double health = blazeBoss.getHealth() - event.getFinalDamage();
            double maxHealth = blazeBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            bossBar.setProgress(Math.min(1.0, Math.max(0, health / maxHealth)));
        }
    }

    @EventHandler
    public void onFEKAttackPlayer1(EntityDamageByEntityEvent event) {
        if (!isFireElementKing(event.getDamager())) {
            return;
        }
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            World world = player.getWorld();
            Location playerLocation = player.getLocation();
            world.createExplosion(playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ(), 5, false, false);
        } else {
            Entity entity = event.getEntity();
            World world = entity.getWorld();
            Location entityLocation = entity.getLocation();
            world.createExplosion(entityLocation.getBlockX(), entityLocation.getBlockY(), entityLocation.getBlockZ(), 5, false, false);
        }

    }

    @EventHandler
    public void onFEKAttackPlayer2(ProjectileHitEvent event) {
        Projectile FEKProjectile = event.getEntity();

        if (!(FEKProjectile.getShooter() instanceof Blaze)) {
            return;
        }

        Blaze blazeShooter = (Blaze) FEKProjectile.getShooter();

        if (!isFireElementKing(blazeShooter)) {
            return;
        }

        if (event.getHitEntity() instanceof Player) {
            Player player = (Player) event.getHitEntity();
            World world = player.getWorld();
            Location playerLocation = player.getLocation();
            world.createExplosion(playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ(), 5, false, false);
        } else {
            Entity entity = event.getEntity();
            World world = entity.getWorld();
            Location entityLocation = entity.getLocation();
            world.createExplosion(entityLocation.getBlockX(), entityLocation.getBlockY(), entityLocation.getBlockZ(), 5, false, false);
        }

    }

    @EventHandler
    public void onFEKHitBlock(ProjectileHitEvent event) {
        if (event.getHitBlock() == null) {
            return;
        }

        Projectile FEKProjectile = event.getEntity();

        if (!(FEKProjectile.getShooter() instanceof Blaze)) {
            return;
        }

        Blaze blazeShooter = (Blaze) FEKProjectile.getShooter();

        if (!isFireElementKing(blazeShooter)) {
            return;
        }

        Location blockLocation = event.getHitBlock().getLocation();
        World world = blockLocation.getWorld();
        world.createExplosion(blockLocation.getBlockX(), blockLocation.getBlockY(), blockLocation.getBlockZ(), 10, false, false);
    }


    private boolean isFireElementKing(Entity entity) {
        if (!(entity.getType().equals(EntityType.BLAZE))) {
            return false;
        }
        Blaze blaze = (Blaze) entity;
        if (blaze.getCustomName() == null) {
            return false;
        }
        if (!(blaze.getCustomName().equals("§l§cFire Element King"))) {
            return false;
        }
        return true;
    }
    private List<ItemStack> getLootList(ItemStack[] itemStacks) {
        List<ItemStack> loot = new ArrayList<>();
        loot.addAll(Arrays.asList(itemStacks));
        return loot;
    }
}

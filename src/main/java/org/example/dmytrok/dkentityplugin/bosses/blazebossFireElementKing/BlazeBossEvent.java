package org.example.dmytrok.dkentityplugin.bosses.blazebossFireElementKing;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlazeBossEvent implements Listener {

    @EventHandler
    public void onFEKDeath(EntityDeathEvent event) {
        if (!(isFireElementKing(event.getEntity()))) {
            return;
        }
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();
        world.playSound(location, Sound.ENTITY_ENDERDRAGON_DEATH, 0.5f, 50);
        event.getDrops().clear();


        //Magic

        Random random = new Random();
        int magicWandChance = random.nextInt(1000);
        if (magicWandChance == 1) {
            ItemStack FEKDrop = new ItemStack(Material.WOOD_AXE, 1);
            ItemMeta FEKDropMagicWand = FEKDrop.getItemMeta();

            List<String> list = new ArrayList<>();
            list.add("§6The most powerful thing in the World!");

                FEKDropMagicWand.setDisplayName("Magic Wand");
                FEKDropMagicWand.setLore(list);
                FEKDropMagicWand.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
                FEKDropMagicWand.setUnbreakable(true);
                FEKDrop.setItemMeta(FEKDropMagicWand);
        }

        //Wand

        ItemStack diamond = new ItemStack(Material.DIAMOND, 30);
        ItemStack emeralds = new ItemStack(Material.EMERALD, 30);
        ItemStack gold = new ItemStack(Material.GOLD_INGOT, 30);

        for (Player player : world.getPlayers()) {
            player.sendMessage("§cFire Element King - extinguished!");
        }
        world.dropItem(location, diamond);
        world.dropItem(location, emeralds);
        world.dropItem(location, gold);
    }
    @EventHandler
    public void onPlayerAttackFEK(EntityDamageEvent event) {
        if (!isFireElementKing(event.getEntity())) {
            return;
        }
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();

        world.spawnParticle(Particle.FLAME, location, 20);
    }

    @EventHandler
    public void onFEKAttackPlayer1(EntityDamageByEntityEvent event) {
        if (!isFireElementKing(event.getDamager())) {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        World world = player.getWorld();
        Location playerLocation = player.getLocation();
        world.createExplosion(playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ(), 5, false, false);
    }
    @EventHandler
    public void onFEKAttackPlayer2(ProjectileHitEvent event) {
        Projectile FEKProjectile = event.getEntity();
        if (!isFireElementKing((Blaze) FEKProjectile.getShooter())) {
            return;
        }

        SmallFireball fireball = (SmallFireball) FEKProjectile;

        if (!(event.getHitEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getHitEntity();
        World world = player.getWorld();
        Location playerLocation = player.getLocation();
        world.createExplosion(playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ(), 5, false, false);
    }
    @EventHandler
    public void onFEKHitBlock(ProjectileHitEvent event) {
        Projectile FEKProjectile = event.getEntity();

        if (!isFireElementKing((Blaze) FEKProjectile.getShooter())) {
            return;
        }

        SmallFireball fireball = (SmallFireball) FEKProjectile;

        Location blockLocation = event.getHitBlock().getLocation();
        World world = blockLocation.getWorld();
        world.createExplosion(blockLocation.getBlockX(), blockLocation.getBlockY(), blockLocation.getBlockZ(), 10, false, false);
    }

    
    private boolean isFireElementKing(Entity entity) {
        if (!(entity.getType().equals(EntityType.BLAZE))) {
            return false;
        }
        Blaze blaze = (Blaze) entity;
        if (!(blaze.getCustomName().equals("§l§cFire Element King"))){
            return false;
        }
        return true;
    }
}

package org.example.dmytrok.dkentityplugin.zombiebossMonarch;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

        Random random = new Random();
        int graveBreakerChance = random.nextInt(500);

        if(graveBreakerChance == 1) {
            ItemStack monarchDrop = new ItemStack(Material.GOLD_AXE, 1);
            ItemMeta monarchDropGraveBreaker = monarchDrop.getItemMeta();

            List<String> list = new ArrayList<>();
            list.add("§4Nightmare of Graveyards....");

            monarchDropGraveBreaker.setDisplayName("Grave Breaker");
            monarchDropGraveBreaker.setLore(list);
            monarchDropGraveBreaker.setUnbreakable(true);
            monarchDrop.setItemMeta(monarchDropGraveBreaker);

            world.dropItem(location, monarchDrop);
        }
        ItemStack diamond = new ItemStack(Material.DIAMOND, 10);
        ItemStack emeralds = new ItemStack(Material.EMERALD, 10);
        ItemStack gold = new ItemStack(Material.GOLD_INGOT, 10);

        for (Player player : world.getPlayers()) {
            player.sendMessage("§5Monarch of Death has fallen!");
        }
        world.dropItem(location, diamond);
        world.dropItem(location, emeralds);
        world.dropItem(location, gold);
    }

    @EventHandler
    public void onPlayerAttackMonarch(EntityDamageEvent event) {
        if (!isMonarch(event.getEntity())) {
            return;
        }
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();

        world.spawnParticle(Particle.DRAGON_BREATH, location, 20);
    }

    @EventHandler
    public void onMonarchAttackPlayer(EntityDamageByEntityEvent event) {
        if (!isMonarch(event.getDamager())) {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        Location tpPlayerLoc = player.getLocation().add(0, 5, 0);
        player.teleport(tpPlayerLoc);
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

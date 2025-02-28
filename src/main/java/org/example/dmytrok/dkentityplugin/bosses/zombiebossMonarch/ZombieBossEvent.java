package org.example.dmytrok.dkentityplugin.bosses.zombiebossMonarch;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.example.dmytrok.dkentityplugin.LEVELSYSTEM.LevelSystem;
import org.example.dmytrok.dkentityplugin.items.weapon.WeaponCreator;
import org.example.dmytrok.dkentityplugin.utils.BossDefeatMenu;
import org.example.dmytrok.dkentityplugin.utils.CongratulationTitle;

import java.util.*;

public class ZombieBossEvent implements Listener {

    private HashMap<Player, Double> damagerPlayers = new HashMap<>();

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
        ItemStack rareDrop = new ItemStack(Material.AIR);
        Random random = new Random();
        int dropChance = random.nextInt(2);
        if(dropChance <= 1) {

        }
        //Drop

        ItemStack[] itemStacks = new ItemStack[]{new ItemStack(Material.DIAMOND, 10),
                new ItemStack(Material.EMERALD, 10),
                new ItemStack(Material.GOLD_INGOT, 10),
                rareDrop
        };

        List<ItemStack> loot = getLootList(itemStacks);

        BossDefeatMenu.setBossInventory(damagerPlayers, loot);


        Bukkit.broadcastMessage("§5Monarch of Death - did not please Death..!");

        if (ZombieBossEntity.getZombieBossBar() != null) {
            ZombieBossEntity.getZombieBossBar().removeAll();
            ZombieBossEntity.getZombieBossBar().setVisible(false);
        }
        CongratulationTitle.displayCongratulation(damagerPlayers);

        for(Player player : damagerPlayers.keySet()) {
            LevelSystem.addExp(player, 50);
        }

        damagerPlayers.clear();
    }

    @EventHandler
    public void onPlayerAttackMonarch(EntityDamageByEntityEvent event) {
        if (!(isMonarch(event.getEntity()))) {
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

    private List<ItemStack> getLootList(ItemStack[] itemStacks) {
        List<ItemStack> loot = new ArrayList<>();
        loot.addAll(Arrays.asList(itemStacks));
        return loot;
    }
}

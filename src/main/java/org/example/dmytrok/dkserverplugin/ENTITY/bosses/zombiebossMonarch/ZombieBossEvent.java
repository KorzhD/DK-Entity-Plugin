package org.example.dmytrok.dkserverplugin.ENTITY.bosses.zombiebossMonarch;

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
import org.example.dmytrok.dkserverplugin.ITEMS.accessories.AccessoriesCreator;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponBows.BowCreator;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.SwordCreator;
import org.example.dmytrok.dkserverplugin.LEVELSYSTEM.LevelSystem;
import org.example.dmytrok.dkserverplugin.UTILS.BossDefeatMenu;
import org.example.dmytrok.dkserverplugin.UTILS.CongratulationTitle;

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
        ItemStack rareDrop12 = new ItemStack(Material.AIR);
        ItemStack rareDrop13 = new ItemStack(Material.AIR);
        ItemStack rareDrop14 = new ItemStack(Material.AIR);
        ItemStack rareDrop15 = new ItemStack(Material.AIR);
        ItemStack rareDrop16 = new ItemStack(Material.AIR);
        ItemStack rareDrop17 = new ItemStack(Material.AIR);
        ItemStack rareDrop18 = new ItemStack(Material.AIR);
        ItemStack rareDrop19 = new ItemStack(Material.AIR);
        ItemStack rareDrop20 = new ItemStack(Material.AIR);
        ItemStack rareDrop21 = new ItemStack(Material.AIR);
        ItemStack rareDrop22 = new ItemStack(Material.AIR);
        ItemStack rareDrop23 = new ItemStack(Material.AIR);
        ItemStack rareDrop24 = new ItemStack(Material.AIR);
        ItemStack rareDrop25 = new ItemStack(Material.AIR);
        ItemStack rareDrop26 = new ItemStack(Material.AIR);
        ItemStack rareDrop27 = new ItemStack(Material.AIR);
        ItemStack rareDrop28 = new ItemStack(Material.AIR);
        ItemStack rareDrop29 = new ItemStack(Material.AIR);
        Random random = new Random();
        int dropChance = random.nextInt(2);
        if(dropChance <= 1) {
            rareDrop12 = BowCreator.getWoodenBow();
            rareDrop13 = BowCreator.getEpicBow();
            rareDrop14 = BowCreator.getHeroBow();
            rareDrop15 = BowCreator.getLegendaryBow();
            rareDrop16 = BowCreator.getMythicBow();
            rareDrop17 = BowCreator.getSoulSlayer();
            rareDrop18 = BowCreator.getFireBow();
            rareDrop19 = BowCreator.getBerserk();
            rareDrop20 = BowCreator.getTheVoidEnd();
            rareDrop21 = BowCreator.getDemonBow();
            rareDrop22 = BowCreator.getDemonSoul();
            rareDrop23 = BowCreator.getIcedBow();
            rareDrop24 = BowCreator.getFrozenFang();
            rareDrop25 = BowCreator.getHollowBow();
            rareDrop26 = BowCreator.getLurkingShadow();
            rareDrop27 = BowCreator.getThePiercerOfSins();
            rareDrop28 = BowCreator.getLordOfLightning();
            rareDrop29 = BowCreator.getBloodthirsty();
        }
        //Drop

        ItemStack[] itemStacks = new ItemStack[]{
                rareDrop12,
                rareDrop13,
                rareDrop14,
                rareDrop15,
                rareDrop16,
                rareDrop17,
                rareDrop18,
                rareDrop19,
                rareDrop20,
                rareDrop21,
                rareDrop22,
                rareDrop23,
                rareDrop24,
                rareDrop25,
                rareDrop26,
                rareDrop27,
                rareDrop28,
                rareDrop29
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
            LevelSystem.setLevel(player, 100);
        }

        damagerPlayers.clear();
    }

    @EventHandler
    public void onPlayerAttackMonarch(EntityDamageByEntityEvent event) {
        if (!(isMonarch(event.getEntity()))) {
            return;
        }
        if (event.getDamager() instanceof Arrow) {
            Bukkit.broadcastMessage(String.valueOf(event.getDamage()));
        }
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player attacker = (Player) event.getDamager();
        double damage = event.getDamage();
        attacker.sendMessage(String.valueOf(damage));

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
        if (entity == null) {
            return false;
        }
        if (!(entity.getType().equals(EntityType.ZOMBIE))) {
             return false;
        }
        Zombie zombie = (Zombie) entity;
        if (zombie.getCustomName() == null) {
            return false;
        }
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

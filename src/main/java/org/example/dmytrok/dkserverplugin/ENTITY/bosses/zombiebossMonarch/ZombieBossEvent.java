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
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.WeaponCreator;
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
        ItemStack rareDrop = new ItemStack(Material.AIR);
        ItemStack rareDrop1 = new ItemStack(Material.AIR);
        ItemStack rareDrop2 = new ItemStack(Material.AIR);
        ItemStack rareDrop3 = new ItemStack(Material.AIR);
        ItemStack rareDrop4 = new ItemStack(Material.AIR);
        ItemStack rareDrop5 = new ItemStack(Material.AIR);
        ItemStack rareDrop6 = new ItemStack(Material.AIR);
        ItemStack rareDrop7 = new ItemStack(Material.AIR);
        ItemStack rareDrop8 = new ItemStack(Material.AIR);
        ItemStack rareDrop9 = new ItemStack(Material.AIR);
        ItemStack rareDrop10 = new ItemStack(Material.AIR);
        ItemStack rareDrop11 = new ItemStack(Material.AIR);
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
        Random random = new Random();
        int dropChance = random.nextInt(2);
        if(dropChance <= 1) {
            rareDrop = WeaponCreator.getEpicSword();
            rareDrop1 = WeaponCreator.getMoltenSword();
            rareDrop2 = WeaponCreator.getHeroSword();
            rareDrop3 = WeaponCreator.getLegendarySword();
            rareDrop4 = WeaponCreator.getMythicBlade();
            rareDrop5 = WeaponCreator.getLegendaryKatana();
            rareDrop6 = WeaponCreator.getBloodyDeath();
            rareDrop7 = WeaponCreator.getEnderKatana();
            rareDrop8 = WeaponCreator.getDemonBlade();
            rareDrop9 = WeaponCreator.getDemonSlayer();
            rareDrop10 = WeaponCreator.getMoonlight();
            rareDrop11 = WeaponCreator.getFrostScythe();
            rareDrop12 = AccessoriesCreator.getGloomOfTheSoul();
            rareDrop13 = AccessoriesCreator.getRingOfTheFaceless();
            rareDrop14 = AccessoriesCreator.getRingOfSpatialRift();
            rareDrop15 = AccessoriesCreator.getEyeOfTheVoid();
            rareDrop16 = AccessoriesCreator.getDragonsSparkRing();
            rareDrop17 = AccessoriesCreator.getFlameJudgeRing();
            rareDrop18 = AccessoriesCreator.getFrozenEyeRing();
            rareDrop19 = AccessoriesCreator.getIceCoreRing();
            rareDrop20 = WeaponCreator.getSteelSword();
            rareDrop21 = WeaponCreator.getSpaceDivider();
            rareDrop22 = WeaponCreator.getShadowScythe();
        }
        //Drop

        ItemStack[] itemStacks = new ItemStack[]{
                rareDrop,
                rareDrop1,
                rareDrop2,
                rareDrop3,
                rareDrop4,
                rareDrop5,
                rareDrop6,
                rareDrop7,
                rareDrop8,
                rareDrop9,
                rareDrop10,
                rareDrop11,
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
                rareDrop22
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

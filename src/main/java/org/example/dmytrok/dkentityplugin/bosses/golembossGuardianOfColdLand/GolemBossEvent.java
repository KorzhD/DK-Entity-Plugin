package org.example.dmytrok.dkentityplugin.bosses.golembossGuardianOfColdLand;

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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkentityplugin.DK_Entity_Plugin;

import java.util.Random;

public class GolemBossEvent implements Listener {

    private ItemStack pHelmet;

    @EventHandler
    public void onGOCLDeath(EntityDeathEvent event) {
        if (!(isGOCL(event.getEntity()))) {
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


        ItemStack diamond = new ItemStack(Material.DIAMOND, 60);
        ItemStack emeralds = new ItemStack(Material.EMERALD, 60);
        ItemStack gold = new ItemStack(Material.GOLD_INGOT, 60);

        Bukkit.broadcastMessage("§bYou missed the feeling of warmth so much..");

        world.dropItem(location, diamond);
        world.dropItem(location, emeralds);
        world.dropItem(location, gold);

        if (GolemBossEntity.getGolemBossBar() != null) {
            GolemBossEntity.getGolemBossBar().removeAll();
            GolemBossEntity.getGolemBossBar().setVisible(false);
        }

    }

    @EventHandler
    public void onPlayerAttackGOCL(EntityDamageEvent event) {
        if (!(isGOCL(event.getEntity()))) {
            return;
        }

        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, x, y, z, 10);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, x + 1, y, z, 10);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, x + 2, y, z, 10);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, x - 1, y, z, 10);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, x - 2, y, z, 10);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, x, y, z + 1, 10);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, x, y, z + 2, 10);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, x, y, z - 1, 10);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, x, y, z - 2, 10);


        IronGolem golemBoss = (IronGolem) event.getEntity();
        BossBar bossBar = GolemBossEntity.getGolemBossBar();
        if (bossBar != null) {
            double health = golemBoss.getHealth() - event.getFinalDamage();
            double maxHealth = golemBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            bossBar.setProgress(Math.max(0, health / maxHealth));

        }
    }

    @EventHandler
    public void onGOCLAttackPlayer(EntityDamageByEntityEvent event) {
        if (!(isGOCL(event.getDamager()))) {
            return;
        }
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack playerHelmet = null;
            if (player.getInventory().getHelmet() != null && !(player.getInventory().getHelmet().getType().equals(Material.PACKED_ICE))) {
                playerHelmet = player.getInventory().getHelmet();
                pHelmet = playerHelmet;
            }
            if (pHelmet != null) {
                playerHelmet = pHelmet;
            }
            ItemStack helmet = playerHelmet;
            ItemStack iceBlockHelmet = new ItemStack(Material.PACKED_ICE);
            player.getInventory().setHelmet(iceBlockHelmet);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.getEquipment().setHelmet(helmet);
                }
            }.runTaskLater(DK_Entity_Plugin.getInstance(), 40L);

            player.addPotionEffect(PotionEffectType.SLOW.createEffect(100, 100));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 128, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1, false, false));


            event.setDamage(80);
        } else {
            event.setDamage(80);
        }

    }

    @EventHandler
    public void onWeakPlaceWasHit(EntityDamageByEntityEvent event) {
        if(!isGOCLWeakPlace(event.getEntity())) {
            return;
        }
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            Bukkit.broadcastMessage("§b" + player.getName() + " hit Guardian of Cold Land -§c weak place!");
            IronGolem boss = GolemBossEntity.getGolemBoss();
            ArmorStand armorStand = (ArmorStand) event.getEntity();
            Location location = armorStand.getLocation();
            location.getWorld().spawnParticle(Particle.TOTEM, location, 10);
            location.getWorld().playSound(location, Sound.ITEM_TOTEM_USE, 1f, 80);
            if (boss != null && !boss.isDead()) {
                boss.damage(200.0);
            }
            armorStand.remove();
        }
    }

    @EventHandler
    public void onArrowDamageGOCL(EntityDamageByEntityEvent event) {
        if (!(isGOCL(event.getEntity()))) {
            return;
        }
        if (!(event.getDamager() instanceof Arrow)) {
            return;
        }
        event.setCancelled(true);
        Arrow arrow = (Arrow) event.getDamager();
        if (arrow.getShooter() instanceof Player) {
            Player player = (Player) arrow.getShooter();
            player.sendMessage("§cYou can't damage Guardian of Cold Land by an arrow!");
        }

    }

    @EventHandler
    public void onFireDamageGOCL(EntityDamageEvent event) {
        if (!(isGOCL(event.getEntity()))) {
            return;
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.FIRE ||
                event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK ||
                event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
            event.setCancelled(true);
        }
    }

    private boolean isGOCL(Entity entity) {
        if (!(entity.getType().equals(EntityType.IRON_GOLEM))) {
            return false;
        }
        IronGolem ironGolem = (IronGolem) entity;
        if (!(ironGolem.getCustomName().equals("§l§bGuardian of Cold Lands"))) {
            return false;
        }
        return true;
    }

    private boolean isGOCLWeakPlace(Entity entity) {
        if(!(entity.getType().equals(EntityType.ARMOR_STAND))) {
            return false;
        }
        ArmorStand armorStand = (ArmorStand) entity;
        if(!(armorStand.getCustomName().equals("Weak Place"))) {
            return false;
        }
        return true;
    }
}

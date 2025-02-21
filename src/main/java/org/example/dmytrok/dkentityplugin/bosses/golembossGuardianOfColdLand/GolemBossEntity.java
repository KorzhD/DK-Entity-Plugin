package org.example.dmytrok.dkentityplugin.bosses.golembossGuardianOfColdLand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.dmytrok.dkentityplugin.DK_Entity_Plugin;

public class GolemBossEntity {

    private static BossBar golemBossBar;
    private static IronGolem bossGolem;

    public static IronGolem summonGolemBoss(Location location) {
        IronGolem golemBoss = (IronGolem) location.getWorld().spawnEntity(location, EntityType.IRON_GOLEM);

        golemBoss.setCustomName("§l§bGuardian of Cold Lands");
        golemBoss.setCustomNameVisible(false);

        golemBoss.setFireTicks(0);
        golemBoss.setRemoveWhenFarAway(false);
        golemBoss.setSilent(false);  //todo add custom sound
        golemBoss.setCanPickupItems(false);
        golemBoss.setPlayerCreated(false);

        golemBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(800);
        golemBoss.setHealth(800);

        golemBoss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);

        golemBoss.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(100);

        ItemStack nothing = new ItemStack(Material.AIR);
        golemBoss.getEquipment().setItemInMainHand(nothing);

        golemBossBar = Bukkit.createBossBar("§l§b❄ Guardian of Cold Lands ❄", BarColor.BLUE, BarStyle.SOLID);
        golemBossBar.setProgress(1.0);
        golemBossBar.setVisible(true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            golemBossBar.addPlayer(player);
        }

        bossGolem = golemBoss;
        return golemBoss;

    }
    public static BossBar getGolemBossBar() {
        return golemBossBar;
    }

    public static IronGolem getGolemBoss() {
        return bossGolem;
    }

    public static void trackArmorStandWithGolem(IronGolem golem) {
        ArmorStand armorStand = (ArmorStand) golem.getWorld().spawnEntity(golem.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setCustomName("Weak Place");
        armorStand.setCustomNameVisible(false);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!golem.isValid() || golem.isDead() || !armorStand.isValid()) {
                    this.cancel();
                    armorStand.remove();
                    return;
                }

                Location golemLoc = golem.getLocation();
                Vector direction = golemLoc.getDirection().normalize();

                Location behind = golemLoc.clone().subtract(direction.multiply(1.10));
                behind.setY(golemLoc.getY() + 2);

                armorStand.teleport(behind);
            }
        }.runTaskTimer(DK_Entity_Plugin.getInstance(), 0L, 2L);
    }
}

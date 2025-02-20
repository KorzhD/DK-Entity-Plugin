package org.example.dmytrok.dkentityplugin.bosses.zombiebossMonarch;

import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;


public class ZombieBossEntity {

    private static BossBar zombieBossBar;

    public static Zombie summonZombieBoss(Location location) {
        Zombie zombieBoss = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);

        zombieBoss.setCustomName("§l§5Monarch");
        zombieBoss.setCustomNameVisible(false);

        zombieBoss.setFireTicks(0);
        zombieBoss.setRemoveWhenFarAway(false);
        zombieBoss.setSilent(true); //todo add custom sound
        zombieBoss.setBaby(false);
        zombieBoss.setCanPickupItems(false);

        zombieBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(400);
        zombieBoss.setHealth(400);

        zombieBoss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);

        zombieBoss.getEquipment().setItemInMainHand(null);
        zombieBoss.getEquipment().setItemInOffHand(null);
        zombieBoss.getEquipment().setBoots(null);
        zombieBoss.getEquipment().setLeggings(null);
        zombieBoss.getEquipment().setChestplate(null);
        zombieBoss.getEquipment().setHelmet(null);

        zombieBossBar = Bukkit.createBossBar("§l§5{ Monarch }", BarColor.PURPLE, BarStyle.SOLID);
        zombieBossBar.setProgress(1.0);
        zombieBossBar.setVisible(true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            zombieBossBar.addPlayer(player);
        }

        return zombieBoss;

    }
    public static BossBar getZombieBossBar() {
        return zombieBossBar;
    }
}

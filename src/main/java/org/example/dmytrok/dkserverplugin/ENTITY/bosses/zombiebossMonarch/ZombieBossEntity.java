package org.example.dmytrok.dkserverplugin.ENTITY.bosses.zombiebossMonarch;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;


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

        zombieBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(800);
        zombieBoss.setHealth(800);

        zombieBoss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);

        zombieBoss.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(5);

        ItemStack nothing = new ItemStack(Material.AIR);
        zombieBoss.getEquipment().setItemInMainHand(nothing);
        zombieBoss.getEquipment().setItemInOffHand(nothing);
        zombieBoss.getEquipment().setBoots(nothing);
        zombieBoss.getEquipment().setLeggings(nothing);
        zombieBoss.getEquipment().setChestplate(nothing);
        zombieBoss.getEquipment().setHelmet(nothing);

        if(zombieBoss.getVehicle() != null) {
                zombieBoss.getVehicle().remove();
        }

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

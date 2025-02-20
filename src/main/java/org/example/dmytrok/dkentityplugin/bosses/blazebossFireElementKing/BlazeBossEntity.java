package org.example.dmytrok.dkentityplugin.bosses.blazebossFireElementKing;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


public class BlazeBossEntity {

    private static BossBar blazeBossBar;

    public static Blaze summonBlazeBoss(Location location) {
        World world = location.getWorld();
        Blaze blazeBoss = (Blaze) world.spawnEntity(location, EntityType.BLAZE);

        blazeBoss.setCustomName("§l§cFire Element King");
        blazeBoss.setCustomNameVisible(false);
        blazeBoss.setRemoveWhenFarAway(false);
        blazeBoss.setSilent(true); //todo add custom sound

            blazeBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(600);
            blazeBoss.setHealth(600);
            blazeBoss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);



        blazeBossBar = Bukkit.createBossBar("§l§c☼ Fire Element King ☼", BarColor.RED, BarStyle.SOLID);
        blazeBossBar.setProgress(1.0);
        blazeBossBar.setVisible(true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            blazeBossBar.addPlayer(player);
        }

        return blazeBoss;

    }
    public static BossBar getBlazeBossBar() {
        return blazeBossBar;
    }

}

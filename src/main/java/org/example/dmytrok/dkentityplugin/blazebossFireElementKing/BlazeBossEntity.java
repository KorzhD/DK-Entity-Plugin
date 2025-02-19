package org.example.dmytrok.dkentityplugin.blazebossFireElementKing;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;


public class BlazeBossEntity {

    public static Blaze summonBlazeBoss(Location location) {
        World world = location.getWorld();
        Blaze blazeBoss = (Blaze) world.spawnEntity(location, EntityType.BLAZE);

        blazeBoss.setCustomName("§l§cFire Element King");
        blazeBoss.setRemoveWhenFarAway(false);
        blazeBoss.setSilent(true);
        blazeBoss.setGlowing(true);

            blazeBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(600);
            blazeBoss.setHealth(600);
            blazeBoss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
            blazeBoss.damage(60);

        return blazeBoss;

    }
}

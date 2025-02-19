package org.example.dmytrok.dkentityplugin.bosses.zombiebossMonarch;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;


public class ZombieBossEntity {

    public static Zombie summonZombieBoss(Location location) {
        Zombie zombieBoss = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);

        zombieBoss.setCustomName("§l§5Monarch");

        zombieBoss.setFireTicks(0);
        zombieBoss.setRemoveWhenFarAway(false);
        zombieBoss.setSilent(true); //todo add custom sound
        zombieBoss.setBaby(false);
        zombieBoss.setCanPickupItems(false);

        zombieBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(400);
        zombieBoss.setHealth(400);

        zombieBoss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);
        zombieBoss.damage(40);

        zombieBoss.getEquipment().setItemInMainHand(null);
        zombieBoss.getEquipment().setItemInOffHand(null);


        return zombieBoss;

    }
}

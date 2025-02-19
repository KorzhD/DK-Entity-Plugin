package org.example.dmytrok.dkentityplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.example.dmytrok.dkentityplugin.bosses.blazebossFireElementKing.BlazeBossCommand;
import org.example.dmytrok.dkentityplugin.bosses.blazebossFireElementKing.BlazeBossEvent;
import org.example.dmytrok.dkentityplugin.items.swordBossKiller.BossKillerSwordCommand;
import org.example.dmytrok.dkentityplugin.bosses.zombiebossMonarch.ZombieBossCommand;
import org.example.dmytrok.dkentityplugin.bosses.zombiebossMonarch.ZombieBossEvent;

public final class DK_Entity_Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Entity Plugin enabled");

        getServer().getPluginManager().registerEvents(new ZombieBossEvent(), this);
        getServer().getPluginManager().registerEvents(new BlazeBossEvent(), this);

        if(getCommand("zombieBoss") != null) {
            getCommand("zombieBoss").setExecutor(new ZombieBossCommand());
        } else {
            getLogger().info("Something WRONG");
        }

        if(getCommand("blazeBoss") != null) {
            getCommand("blazeBoss").setExecutor(new BlazeBossCommand());
        } else {
            getLogger().info("Something WRONG");
        }

        if(getCommand("bossKiller") != null) {
            getCommand("bossKiller").setExecutor(new BossKillerSwordCommand());
        } else {
            getLogger().info("Something WRONG");
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("Entity Plugin disabled");
    }
}

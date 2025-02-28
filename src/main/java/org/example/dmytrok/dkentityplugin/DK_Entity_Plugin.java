package org.example.dmytrok.dkentityplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.example.dmytrok.dkentityplugin.bosses.blazebossFireElementKing.BlazeBossCommand;
import org.example.dmytrok.dkentityplugin.bosses.blazebossFireElementKing.BlazeBossEvent;
import org.example.dmytrok.dkentityplugin.bosses.golembossGuardianOfColdLand.GolemBossCommand;
import org.example.dmytrok.dkentityplugin.bosses.golembossGuardianOfColdLand.GolemBossEvent;
import org.example.dmytrok.dkentityplugin.items.bossKiller.BossKillerSwordCommand;
import org.example.dmytrok.dkentityplugin.bosses.zombiebossMonarch.ZombieBossCommand;
import org.example.dmytrok.dkentityplugin.bosses.zombiebossMonarch.ZombieBossEvent;
import org.example.dmytrok.dkentityplugin.items.weapon.*;
import org.example.dmytrok.dkentityplugin.items.weapon.EnderKatana;
import org.example.dmytrok.dkentityplugin.items.weapon.BloodyDeath;
import org.example.dmytrok.dkentityplugin.items.weapon.LegendaryKatana;
import org.example.dmytrok.dkentityplugin.utils.BossDefeatMenu;
import org.example.dmytrok.dkentityplugin.utils.LastDropCommand;
import org.example.dmytrok.dkentityplugin.LEVELSYSTEM.ScoreboardManager;
import org.example.dmytrok.dkentityplugin.utils.PlayerDeath;

public final class DK_Entity_Plugin extends JavaPlugin {

    private static DK_Entity_Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Entity Plugin enabled");


        getServer().getPluginManager().registerEvents(new ScoreboardManager(), this);
        getServer().getPluginManager().registerEvents(new BossDefeatMenu(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);

        getServer().getPluginManager().registerEvents(new LegendarySword(), this);
        getServer().getPluginManager().registerEvents(new LegendaryKatana(), this);
        getServer().getPluginManager().registerEvents(new BloodyDeath(), this);
        getServer().getPluginManager().registerEvents(new EnderKatana(), this);
        getServer().getPluginManager().registerEvents(new DemonBlade(), this);
        getServer().getPluginManager().registerEvents(new DemonSlayer(), this);
        getServer().getPluginManager().registerEvents(new Moonlight(), this);
        getServer().getPluginManager().registerEvents(new FrostScythe(), this);


        getServer().getPluginManager().registerEvents(new ZombieBossEvent(), this);
        getServer().getPluginManager().registerEvents(new BlazeBossEvent(), this);
        getServer().getPluginManager().registerEvents(new GolemBossEvent(), this);

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
        if(getCommand("golemBoss") != null) {
            getCommand("golemBoss").setExecutor(new GolemBossCommand());
        } else {
            getLogger().info("Something WRONG");
        }

        if(getCommand("bossKiller") != null) {
            getCommand("bossKiller").setExecutor(new BossKillerSwordCommand());
        } else {
            getLogger().info("Something WRONG");
        }
        if(getCommand("lastdrop") != null) {
            getCommand("lastdrop").setExecutor(new LastDropCommand());
        } else {
            getLogger().info("Something WRONG");
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("Entity Plugin disabled");
    }

    public static DK_Entity_Plugin getInstance() {
        return instance;
    }
}

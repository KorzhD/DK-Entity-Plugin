package org.example.dmytrok.dkserverplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.example.dmytrok.dkserverplugin.ENTITY.bosses.blazebossFireElementKing.BlazeBossCommand;
import org.example.dmytrok.dkserverplugin.ENTITY.bosses.blazebossFireElementKing.BlazeBossEvent;
import org.example.dmytrok.dkserverplugin.ENTITY.bosses.golembossGuardianOfColdLand.GolemBossCommand;
import org.example.dmytrok.dkserverplugin.ENTITY.bosses.golembossGuardianOfColdLand.GolemBossEvent;
import org.example.dmytrok.dkserverplugin.ITEMS.accessories.IceCoreRing;
import org.example.dmytrok.dkserverplugin.ITEMS.accessories.FrozenEyeRing;
import org.example.dmytrok.dkserverplugin.ITEMS.accessories.DragonsSparkRing;
import org.example.dmytrok.dkserverplugin.ITEMS.accessories.FlameJudgeRing;
import org.example.dmytrok.dkserverplugin.ITEMS.accessories.EyeOfTheVoid;
import org.example.dmytrok.dkserverplugin.ITEMS.accessories.GloomOfTheSoul;
import org.example.dmytrok.dkserverplugin.ITEMS.accessories.RingOfTheFaceless;
import org.example.dmytrok.dkserverplugin.ITEMS.accessories.RingOfSpatialRift;
import org.example.dmytrok.dkserverplugin.ITEMS.bossKiller.BossKillerSwordCommand;
import org.example.dmytrok.dkserverplugin.ENTITY.bosses.zombiebossMonarch.ZombieBossCommand;
import org.example.dmytrok.dkserverplugin.ENTITY.bosses.zombiebossMonarch.ZombieBossEvent;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.*;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.EnderKatana;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.BloodyDeath;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.LegendaryKatana;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.MythicBlade;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.SpaceDivider;
import org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords.ShadowScythe;
import org.example.dmytrok.dkserverplugin.LEVELSYSTEM.SwordLevelCheck;
import org.example.dmytrok.dkserverplugin.MENU.adminMenu.AdminMenuCommand;
import org.example.dmytrok.dkserverplugin.MENU.adminMenu.AdminMenuEvents;
import org.example.dmytrok.dkserverplugin.MENU.inventoryrules.TwoSwordsRule;
import org.example.dmytrok.dkserverplugin.MENU.playermenu.PlayerMenuCommand;
import org.example.dmytrok.dkserverplugin.MENU.playermenu.PlayerMenuEvents;
import org.example.dmytrok.dkserverplugin.MENU.playermenu.backpack.BackpackCommand;
import org.example.dmytrok.dkserverplugin.MENU.playermenu.backpack.BackpackEvents;
import org.example.dmytrok.dkserverplugin.MENU.playermenu.personalAccount.PersonalAccountCommand;
import org.example.dmytrok.dkserverplugin.MENU.playermenu.personalAccount.PersonalAccountEvents;
import org.example.dmytrok.dkserverplugin.MENU.tradesystem.TradeCommand;
import org.example.dmytrok.dkserverplugin.MENU.tradesystem.TradeEvent;
import org.example.dmytrok.dkserverplugin.UTILS.*;
import org.example.dmytrok.dkserverplugin.LEVELSYSTEM.ScoreboardManager;

public final class DK_Server_Plugin extends JavaPlugin {

    private static DK_Server_Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Server Plugin enabled");

        //UTILS
        getServer().getPluginManager().registerEvents(new ScoreboardManager(), this);
        getServer().getPluginManager().registerEvents(new BossDefeatMenu(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new Stamina(), this);
        getServer().getPluginManager().registerEvents(new SwordLevelCheck(), this);
        //UTILS

        //SWORDS
        getServer().getPluginManager().registerEvents(new SpaceDivider(), this);
        getServer().getPluginManager().registerEvents(new ShadowScythe(), this);
        getServer().getPluginManager().registerEvents(new LegendarySword(), this);
        getServer().getPluginManager().registerEvents(new MythicBlade(), this);
        getServer().getPluginManager().registerEvents(new LegendaryKatana(), this);
        getServer().getPluginManager().registerEvents(new BloodyDeath(), this);
        getServer().getPluginManager().registerEvents(new EnderKatana(), this);
        getServer().getPluginManager().registerEvents(new DemonBlade(), this);
        getServer().getPluginManager().registerEvents(new DemonSlayer(), this);
        getServer().getPluginManager().registerEvents(new Moonlight(), this);
        getServer().getPluginManager().registerEvents(new FrostScythe(), this);
        //SWORDS

        //RINGS
        getServer().getPluginManager().registerEvents(new IceCoreRing(), this);
        getServer().getPluginManager().registerEvents(new FrozenEyeRing(), this);
        getServer().getPluginManager().registerEvents(new DragonsSparkRing(), this);
        getServer().getPluginManager().registerEvents(new FlameJudgeRing(), this);
        getServer().getPluginManager().registerEvents(new EyeOfTheVoid(), this);
        getServer().getPluginManager().registerEvents(new RingOfSpatialRift(), this);
        getServer().getPluginManager().registerEvents(new GloomOfTheSoul(), this);
        getServer().getPluginManager().registerEvents(new RingOfTheFaceless(), this);
        //RINGS

        //BOSS
        getServer().getPluginManager().registerEvents(new ZombieBossEvent(), this);
        getServer().getPluginManager().registerEvents(new BlazeBossEvent(), this);
        getServer().getPluginManager().registerEvents(new GolemBossEvent(), this);
        //BOSS


        //MENU
        getServer().getPluginManager().registerEvents(new AdminMenuEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerMenuEvents(), this);
        getServer().getPluginManager().registerEvents(new PersonalAccountEvents(), this);
        getServer().getPluginManager().registerEvents(new BackpackEvents(), this);
        getServer().getPluginManager().registerEvents(new BackpackCommand(), this);
        //MENU

        getServer().getPluginManager().registerEvents(new TwoSwordsRule(), this);


        getServer().getPluginManager().registerEvents(new TradeEvent(), this);


        if(getCommand("adminMenu") != null) {
            getCommand("adminMenu").setExecutor(new AdminMenuCommand());
        } else {
            getLogger().info("Something WRONG");
        }
        if(getCommand("menu") != null) {
            getCommand("menu").setExecutor(new PlayerMenuCommand());
        } else {
            getLogger().info("Something WRONG");
        }
        if(getCommand("personalAccount") != null) {
            getCommand("personalAccount").setExecutor(new PersonalAccountCommand());
        } else {
            getLogger().info("Something WRONG");
        }
        if(getCommand("backpack") != null) {
            getCommand("backpack").setExecutor(new BackpackCommand());
        } else {
            getLogger().info("Something WRONG");
        }
        if(getCommand("trademenu") != null) {
            getCommand("trademenu").setExecutor(new TradeCommand());
        } else {
            getLogger().info("Something WRONG");
        }


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
        getLogger().info("Server Plugin disabled");
    }

    public static DK_Server_Plugin getInstance() {
        return instance;
    }
}

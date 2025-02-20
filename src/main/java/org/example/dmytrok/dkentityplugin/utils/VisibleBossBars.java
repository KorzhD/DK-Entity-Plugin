package org.example.dmytrok.dkentityplugin.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.example.dmytrok.dkentityplugin.bosses.blazebossFireElementKing.BlazeBossEntity;
import org.example.dmytrok.dkentityplugin.bosses.golembossGuardianOfColdLand.GolemBossEntity;
import org.example.dmytrok.dkentityplugin.bosses.zombiebossMonarch.ZombieBossEntity;

public class VisibleBossBars implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        GolemBossEntity.getGolemBossBar().addPlayer(player);
        ZombieBossEntity.getZombieBossBar().addPlayer(player);
        BlazeBossEntity.getBlazeBossBar().addPlayer(player);
    }
}

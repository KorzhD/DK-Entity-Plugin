package org.example.dmytrok.dkserverplugin.UTILS;

import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowDeleting implements Listener {

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
         Arrow arrow = (Arrow) event.getEntity();
                if (event.getHitEntity() == null) {
                    arrow.remove();
                }
            }
        }
    }


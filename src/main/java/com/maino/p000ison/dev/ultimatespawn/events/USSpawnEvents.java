package com.maino.p000ison.dev.ultimatespawn.events;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 *
 * @author p000ison
 * @author maino
 */
public class USSpawnEvents implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        File file = new File(Bukkit.getWorlds().get(0) + "/players/" + player.getName() + ".dat");
        boolean exist = file.exists();
        
        if (!exist) {
            
        }
    }
}

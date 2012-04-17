package com.maino.p000ison.dev.ultimatespawn.events;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import com.maino.p000ison.dev.ultimatespawn.util.Util;
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
    private UltimateSpawn plugin;
    public USSpawnEvents(UltimateSpawn plugin) {
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (plugin.getSettingsHandler().getPlayerFirstJoin().equalsIgnoreCase("global")) {
                player.teleport(Util.getGlobalLocation(plugin.getStorageHandler()));
            }
        else if (plugin.getSettingsHandler().getPlayerFirstJoin().equalsIgnoreCase("local")) {
            player.teleport(Util.getWorldLocation(plugin.getStorageHandler()));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        File file = new File(Bukkit.getWorlds().get(0) + "/players/" + player.getName() + ".dat");
        boolean exist = file.exists();
        
        if (!exist) {
            if (plugin.getSettingsHandler().getPlayerFirstJoin().equalsIgnoreCase("global")) {
                player.teleport(Util.getGlobalLocation(plugin.getStorageHandler()));
            }
            else if (plugin.getSettingsHandler().getPlayerFirstJoin().equalsIgnoreCase("local")) {
                player.teleport(Util.getWorldLocation(plugin.getStorageHandler()));
             }
        }
    }

}

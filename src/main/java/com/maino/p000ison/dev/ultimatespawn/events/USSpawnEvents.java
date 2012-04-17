package com.maino.p000ison.dev.ultimatespawn.events;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import com.maino.p000ison.dev.ultimatespawn.util.Util;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
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
        if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("global")) {
            event.setRespawnLocation(Util.getGlobalLocation(plugin.getStorageHandler()));
        } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("world")) {
            event.setRespawnLocation(Util.getWorldLocation(plugin.getStorageHandler(), player.getWorld().getName()));
        } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("local")) {
            event.setRespawnLocation(Util.getNearest(player.getLocation(), Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig())));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        File file = new File(Bukkit.getWorlds().get(0) + "/players/" + player.getName() + ".dat");
        boolean exist = file.exists();

        if (!exist) {

            player.teleport(Util.getGlobalLocation(plugin.getStorageHandler()));

        }
    }
}

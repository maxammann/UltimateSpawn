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
        if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("global")) {
            if (plugin.getStorageHandler().getConfig().isSet("global.world")) {
                event.setRespawnLocation(Util.getGlobalSpawn(plugin.getStorageHandler()));
            }
        } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("world")) {
            if (plugin.getStorageHandler().getConfig().isSet(player.getWorld().getName() + ".x")) {
                event.setRespawnLocation(Util.getWorldSpawn(plugin.getStorageHandler(), player.getWorld().getName()));
            }
        } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("local")) {
            if (!Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig()).isEmpty()) {
                event.setRespawnLocation(Util.getNearest(player.getLocation(), Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig())));
            }
        } else {
            player.sendMessage("Please check the config!");
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        File file = new File(Bukkit.getWorlds().get(0).getName() + "/players/" + player.getName() + ".dat");
        boolean exist = file.exists();

        if (!exist) {
            if (plugin.getStorageHandler().getConfig().isSet("global.world")) {
                player.teleport(Util.getGlobalSpawn(plugin.getStorageHandler()));
            }
        }
    }
}

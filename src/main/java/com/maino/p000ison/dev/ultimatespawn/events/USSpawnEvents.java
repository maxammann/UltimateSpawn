package com.maino.p000ison.dev.ultimatespawn.events;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import com.maino.p000ison.dev.ultimatespawn.util.Util;
import java.io.File;
import java.util.logging.Level;
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
        plugin.getLogger().log(Level.INFO, "Player respawned!");
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
                event.setRespawnLocation(Util.getNearest(plugin.getStorageHandler(), player.getLocation(), Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig())));
            }
        } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("group")) {
            if (!Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig()).isEmpty()) {
                event.setRespawnLocation(Util.getGroupSpawn(plugin.getStorageHandler(), player));
            }
        } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("random")) {
            event.setRespawnLocation(Util.randomSpawn(player.getWorld(), -500, 500, -500, 500));
        } else {
            player.sendMessage("Please check the config!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        File file = new File(Bukkit.getWorlds().get(0).getName() + "/players/" + player.getName() + ".dat");



        if (!file.exists()) {

            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                @Override
                public void run() {

                    plugin.getLogger().log(Level.INFO, "A new Player has joint!");
                    if (plugin.getSettingsHandler().getPlayerFirstJoin().equalsIgnoreCase("global")) {
                        if (plugin.getStorageHandler().getConfig().isSet("global.world")) {
                            player.teleport(Util.getGlobalSpawn(plugin.getStorageHandler()));
                        } else {
                            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
                        }
                    } else if (plugin.getSettingsHandler().getPlayerFirstJoin().equalsIgnoreCase("group")) {
                        if (plugin.getStorageHandler().getConfig().isSet("groups." + UltimateSpawn.getPrimaryGroup(player))) {
                            player.teleport(Util.getGroupSpawn(plugin.getStorageHandler(), player));
                        } else if (plugin.getStorageHandler().getConfig().isSet("global.world")) {
                            player.teleport(Util.getGlobalSpawn(plugin.getStorageHandler()));
                        } else {
                            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
                        }
                    } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("random")) {

                        player.teleport(Util.randomSpawn(player.getWorld(), -500, 500, -500, 500));
                    }
                }
            }, 5L);
        }
    }
}

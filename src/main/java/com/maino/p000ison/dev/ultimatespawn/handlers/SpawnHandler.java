package com.maino.p000ison.dev.ultimatespawn.handlers;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import com.maino.p000ison.dev.ultimatespawn.util.Util;
import org.bukkit.Location;

/**
 *
 * @author p00ison
 * @author maino
 */
public class SpawnHandler {
    private UltimateSpawn plugin;
    
    public SpawnHandler(UltimateSpawn plugin) {
        this.plugin = plugin;
    }
    
    public void spawnAtNearest(Location loc) {
        Util.getNearest(loc, Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig()));
    }
}
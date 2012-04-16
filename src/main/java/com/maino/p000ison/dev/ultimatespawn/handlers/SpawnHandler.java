package com.maino.p000ison.dev.ultimatespawn.handlers;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

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
    
    // exactspawn method
    public void exactspawn(World world, double x, double y, double z, float yaw, float pitch, Player p) {
        Location location = new Location(world,x,y,z,yaw,pitch);
        p.teleport(location);
    }
}

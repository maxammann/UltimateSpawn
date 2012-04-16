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
    
    public void setspawn(World world, double x, double y, double z, float yaw, float pitch, Player p) {
        plugin.getStorageHandler().getConfig().set(world.getName() + ".world", world.getName());
        plugin.getStorageHandler().getConfig().set(world.getName() + ".x", x);
        plugin.getStorageHandler().getConfig().set(world.getName() + ".y", y);
        plugin.getStorageHandler().getConfig().set(world.getName() + ".z", z);
        plugin.getStorageHandler().getConfig().set(world.getName() + ".yaw", yaw);
        plugin.getStorageHandler().getConfig().set(world.getName() + ".pitch", pitch);
    }
    
    public void setglobalspawn(World world, double x, double y, double z, float yaw, float pitch, Player p) {
        plugin.getStorageHandler().getConfig().set("global.world", world.getName());
        plugin.getStorageHandler().getConfig().set("global.x", x);
        plugin.getStorageHandler().getConfig().set("global.y", y);
        plugin.getStorageHandler().getConfig().set("global.z", z);
        plugin.getStorageHandler().getConfig().set("global.yaw", yaw);
        plugin.getStorageHandler().getConfig().set("global.pitch", pitch);
    }
    
}

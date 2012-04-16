package com.maino.p000ison.dev.ultimatespawn.util;

import java.util.Iterator;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 *
 * @author p000ison
 * @author maino
 */
public class Util {

    public static String color(String text) {
        String colourised = text.replaceAll("&(?=[0-9a-fA-FkK])", "\u00a7");
        return colourised;
    }

    /**
     * @return the random location to teleport
     */
    public Location randomSpawn(World world, double minx, double maxx, double minz, double maxz, List<String> preventSpawnOnBlocks) {
        Location loc;
        do {
            double X = (minx + (Math.random() * (maxx - minx)));
            double Z = (minz + (Math.random() * (maxz - minz)));
            double Y = getHeighestFreeBlockAt((int) X, (int) Z, world);
            loc = new Location(world, X, Y, Z);
        } while (checkDreamSpawnLocation(loc, loc.getWorld(), preventSpawnOnBlocks) == false);
        return loc;
    }

    public static int getHeighestFreeBlockAt(int posX, int posZ, World world) {
        int maxHeight = 258;
        switch (world.getEnvironment()) {
            case NETHER:
                maxHeight = 128;
                break;
            case NORMAL:
                maxHeight = 256;
                break;
        }
        int searchedHeight = maxHeight - 1;
        Block lastBlock = null;
        while (searchedHeight > 0) {
            final Block block = world.getBlockAt(posX, searchedHeight, posZ);
            if (lastBlock != null && lastBlock.getType() == Material.AIR
                    && block.getType() != Material.AIR) {
                break;
            }
            lastBlock = block;
            searchedHeight--;
        }
        return ++searchedHeight;
    }

    /**
     * @return true if the row of blocks is not emty
     */
    public static boolean checkEmty(double X, double Z, World world) {
        int a = 0;
        for (int i = 0; i <= 258; i++) {
            if (world.getBlockAt((int) X, i, (int) Z).getType() == Material.AIR) {
                a++;
            }
        }
        return a < 255;
    }

    /**
     * @return true if the Location loc is valid
     */
    public static boolean checkDreamSpawnLocation(Location loc, World world, List<String> preventedSpawnBlocks) {
        for (Iterator it = preventedSpawnBlocks.iterator(); it.hasNext();) {
            for (int i = -1; i <= +1; i++) {
                Location blockloc = new Location(world, loc.getBlockX(), loc.getBlockY() + i, loc.getBlockZ());
                if (blockloc.getBlock().getType().equals(Material.valueOf(((String) it.next()).toUpperCase())) || !checkEmty(loc.getX(), loc.getZ(), world)) {
                    return false;
                }
            }
        }
        return true;
    }
}

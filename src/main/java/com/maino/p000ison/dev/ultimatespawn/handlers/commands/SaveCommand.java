package com.maino.p000ison.dev.ultimatespawn.handlers.commands;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import com.maino.p000ison.dev.ultimatespawn.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

/**
 *
 * @author p000ison
 * @author maino
 */
public class SaveCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public SaveCommand(UltimateSpawn plugin) {
        super("Reload");
        this.plugin = plugin;
        setDescription("Saves the spawns to the map.");
        setUsage("/ulspawn save <world/global/group>");
        setArgumentRange(1, 1);
        setIdentifiers("save");
        setPermission("ulspawn.command.save");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        for (World world : Bukkit.getWorlds()) {
            world.setSpawnLocation((int)Util.getWorldSpawn(plugin.getStorageHandler()
                    , world.getName()).getX(), (int)Util.getWorldSpawn(plugin.getStorageHandler()
                    , world.getName()).getX(), (int)Util.getWorldSpawn(plugin.getStorageHandler()
                    , world.getName()).getX());
        }
        return true;
    }
}

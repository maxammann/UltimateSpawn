package com.maino.p000ison.dev.ultimatespawn.handlers.commands;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import com.maino.p000ison.dev.ultimatespawn.util.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author p000ison
 * @author maino
 */
public class SetCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public SetCommand(UltimateSpawn plugin) {
        super("Set");
        this.plugin = plugin;
        setDescription("Sets the Spawns.");
        setUsage("/ulspawn set");
        setArgumentRange(1, 2);
        setIdentifiers("set");
        setPermission("ulspawn.command.set");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (args[0].equals("global")) {
                    Util.setGlobalSpawn(plugin.getStorageHandler(), player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
                    player.sendMessage("global");
                } else if (args[0].equals("world")) {
                    Util.setWorldSpawn(plugin.getStorageHandler(), player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
                    player.sendMessage("world");
                }
            } else if (args.length == 2) {
                if (args[0].equals("local")) {
                    player.sendMessage("local");
                    Util.setLocalSpawn(plugin.getStorageHandler(), args[1], player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
                }
            }
        } else {
            sender.sendMessage("Console cant leave a dream!");
        }
        return true;
    }
}

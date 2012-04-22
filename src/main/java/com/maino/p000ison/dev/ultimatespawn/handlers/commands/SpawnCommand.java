package com.maino.p000ison.dev.ultimatespawn.handlers.commands;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import com.maino.p000ison.dev.ultimatespawn.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author p000ison
 * @author maino
 */
public class SpawnCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public SpawnCommand(UltimateSpawn plugin) {
        super("Spawn");
        this.plugin = plugin;
        setDescription("Spawn.");
        setUsage("/spawn");
        setArgumentRange(0, 0);
        setIdentifiers("spawn");
        setPermission("ulspawn.command.spawn");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("global")) {
                if (plugin.getStorageHandler().getConfig().isSet("global.world")) {
                    player.teleport(Util.getGlobalSpawn(plugin.getStorageHandler()));
                }
            } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("world")) {
                if (plugin.getStorageHandler().getConfig().isSet(player.getWorld().getName() + ".x")) {
                    player.teleport(Util.getWorldSpawn(plugin.getStorageHandler(), player.getWorld().getName()));
                }
            } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("local")) {
                if (!Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig()).isEmpty()) {
                    player.teleport(Util.getNearest(plugin.getStorageHandler(), player.getLocation(), Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig())));
                }
            } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("group")) {
                if (!Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig()).isEmpty()) {
                    player.teleport(Util.getGroupSpawn(plugin.getStorageHandler(), player));
                }
            } else if (plugin.getSettingsHandler().getPlayerRespawn().equalsIgnoreCase("random")) {
                player.teleport(Util.randomSpawn(player.getWorld(), -500, 500, -500, 500));
            } else {
                player.sendMessage("Please check the config!");
            }
        } else {
            sender.sendMessage("Console cant do this!");
        }
        return true;
    }
}

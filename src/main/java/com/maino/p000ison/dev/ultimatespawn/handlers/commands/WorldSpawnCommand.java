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
public class WorldSpawnCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public WorldSpawnCommand(UltimateSpawn plugin) {
        super("WorldSpawn");
        this.plugin = plugin;
        setDescription("WorldSpawn.");
        setUsage("/worldspawn");
        setArgumentRange(0, 0);
        setIdentifiers("worldspawn", "wspawn");
        setPermission("ulspawn.command.worldspawn");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
             if (plugin.getStorageHandler().getConfig().isSet(player.getWorld().getName() + ".x")) {
                player.teleport(Util.getWorldSpawn(plugin.getStorageHandler(), player.getWorld().getName()));
            } else {
                player.teleport(player.getWorld().getSpawnLocation());
            }
        } else {
            sender.sendMessage("Console cant do this!");
        }
        return true;
    }
}

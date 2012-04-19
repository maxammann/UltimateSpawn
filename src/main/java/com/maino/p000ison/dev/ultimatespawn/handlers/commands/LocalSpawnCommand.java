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
public class LocalSpawnCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public LocalSpawnCommand(UltimateSpawn plugin) {
        super("LocalSpawn");
        this.plugin = plugin;
        setDescription("LocalSpawn.");
        setUsage("/localspawn");
        setArgumentRange(0, 0);
        setIdentifiers("localspawn");
        setPermission("ulspawn.command.localspawn");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig()).isEmpty()) {
                player.teleport(Util.getNearest(player.getLocation(), Util.fromConfigToLocationList(plugin.getStorageHandler().getConfig())));
            } else {
                player.teleport(player.getWorld().getSpawnLocation());
            }
        } else {
            sender.sendMessage("Console cant do this!");
        }
        return true;
    }
}

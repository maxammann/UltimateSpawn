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
public class NormalSpawnCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public NormalSpawnCommand(UltimateSpawn plugin) {
        super("NormalSpawn");
        this.plugin = plugin;
        setDescription("NormalSpawn.");
        setUsage("/normalspawn");
        setArgumentRange(0, 0);
        setIdentifiers("normalspawn", "nspawn");
        setPermission("ulspawn.command.normalspawn");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (plugin.getStorageHandler().getConfig().isSet("global.world")) {
                player.teleport(Util.getGlobalSpawn(plugin.getStorageHandler()));
            } else {
                player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            }
        } else {
            sender.sendMessage("Console cant do this!");
        }
        return true;
    }
}

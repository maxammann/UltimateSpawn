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
            player.teleport(Util.getWorldLocation(plugin.getStorageHandler(), player.getWorld().getName()));
        } else {
            sender.sendMessage("Console cant do this!");
        }
        return true;
    }
}

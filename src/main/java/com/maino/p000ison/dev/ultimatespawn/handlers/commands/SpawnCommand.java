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
public class SpawnCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public SpawnCommand(UltimateSpawn plugin) {
        super("Spawn");
        this.plugin = plugin;
        setDescription("Spawn.");
        setUsage("/spawn");
        setArgumentRange(0, 0);
        setIdentifiers("spawn");
        setPermission("ulspawn.command.reload");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.teleport(Util.getGlobalLocation(plugin.getStorageHandler()));
        } else {
            sender.sendMessage("Console cant do this!");
        }
        return true;
    }
}

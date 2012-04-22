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
public class GroupSpawnCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public GroupSpawnCommand(UltimateSpawn plugin) {
        super("Groupspawn");
        this.plugin = plugin;
        setDescription("Groupspawn.");
        setUsage("/groupspawn");
        setArgumentRange(0, 0);
        setIdentifiers("groupspawn", "gspawn");
        setPermission("ulspawn.command.groupspawn");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            System.out.println(UltimateSpawn.getPrimaryGroup(player));
            if (plugin.getStorageHandler().getConfig().isSet("groups." + UltimateSpawn.getPrimaryGroup(player))) {
                player.teleport(Util.getGroupSpawn(plugin.getStorageHandler(), player));
            } else {
                player.sendMessage("No group spawn set.");
            }
        } else {
            sender.sendMessage("Console cant do this!");
        }
        return true;
    }
}

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
public class RandomSpawnCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public RandomSpawnCommand(UltimateSpawn plugin) {
        super("RandomSpawn");
        this.plugin = plugin;
        setDescription("RandomSpawn.");
        setUsage("/randomspawn");
        setArgumentRange(0, 0);
        setIdentifiers("randomspawn", "rspawn");
        setPermission("ulspawn.command.randomspawn");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.teleport(Util.randomSpawn(player.getWorld(), -500, 500, -500, 500));
        } else {
            sender.sendMessage("Console cant do this!");
        }
        return true;
    }
}

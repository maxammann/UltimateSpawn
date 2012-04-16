package com.maino.p000ison.dev.ultimatespawn.handlers.commands;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import com.maino.p000ison.dev.ultimatespawn.util.Util;
import org.bukkit.command.CommandSender;

/**
 *
 * @author p000ison
 * @author maino
 */
public class ReloadCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public ReloadCommand(UltimateSpawn plugin) {
        super("Reload");
        this.plugin = plugin;
        setDescription("Reloads the Config.");
        setUsage("/ulspawn reload <config/storage>");
        setArgumentRange(0, 1);
        setIdentifiers("reload");
        setPermission("ulspawn.command.reload");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        final long startTime = System.nanoTime();
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("config")) {
                plugin.getSettingsHandler().load();
            } else if (args[0].equalsIgnoreCase("storage")) {
                plugin.getStorageHandler().load();
            } else if (args[0].equalsIgnoreCase("all")) {
                plugin.getStorageHandler().load();
                plugin.getSettingsHandler().load();
            }
        } else {
            plugin.getSettingsHandler().load();
        }
        final long endTime = System.nanoTime();
        sender.sendMessage(Util.color(String.format("Config reloaded!(%s s)", ((double) (endTime - startTime)) / 1000000000.0)));
        return true;
    }
}

package me.drenix.drenixcoin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class HelpCommand implements CommandExecutor {

    private final Main plugin;

    public HelpCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> help = plugin.getConfig().getStringList("help-message");
        for (String line : help) {
            sender.sendMessage(line);
        }
        return true;
    }
}

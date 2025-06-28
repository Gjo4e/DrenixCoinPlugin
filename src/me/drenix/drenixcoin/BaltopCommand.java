package me.drenix.drenixcoin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.*;

public class BaltopCommand implements CommandExecutor {

    private final Main plugin;

    public BaltopCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, Object> balances = plugin.getConfig().getConfigurationSection("balances").getValues(false);
        List<Map.Entry<String, Object>> sorted = new ArrayList<>(balances.entrySet());
        sorted.sort((a, b) -> Integer.compare((int) b.getValue(), (int) a.getValue()));

        sender.sendMessage("§6§lTop 10 Drenix Coin Holders:");
        for (int i = 0; i < Math.min(10, sorted.size()); i++) {
            String uuid = sorted.get(i).getKey();
            OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
            sender.sendMessage("§e" + (i + 1) + ". " + player.getName() + ": §6" + sorted.get(i).getValue());
        }
        return true;
    }
}

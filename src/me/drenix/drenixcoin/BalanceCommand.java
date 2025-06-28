package me.drenix.drenixcoin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    private final Main plugin;

    public BalanceCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        int balance = plugin.getConfig().getInt("balances." + player.getUniqueId(), 0);
        player.sendMessage("§eYour Drenix Coin balance: §6" + balance);
        return true;
    }
}

package me.drenix.drenixcoin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ExchangeCommand implements CommandExecutor {

    private final Main plugin;

    public ExchangeCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        int diamondCount = 0;
        for (ItemStack item : player.getInventory()) {
            if (item != null && item.getType() == Material.DIAMOND) {
                diamondCount += item.getAmount();
            }
        }

        if (diamondCount < 96) {
            player.sendMessage("You need at least 96 diamonds (1.5 stacks) to exchange.");
            return true;
        }

        int remaining = 96;
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item != null && item.getType() == Material.DIAMOND) {
                int amount = item.getAmount();
                if (amount <= remaining) {
                    remaining -= amount;
                    player.getInventory().setItem(i, null);
                } else {
                    item.setAmount(amount - remaining);
                    remaining = 0;
                }
                if (remaining == 0) break;
            }
        }

        ItemStack coin = new ItemStack(Material.SUNFLOWER);
        ItemMeta meta = coin.getItemMeta();
        meta.setDisplayName("§6Drenix Coin");
        meta.setLore(List.of("§7Right click to store this coin."));
        coin.setItemMeta(meta);
        player.getInventory().addItem(coin);
        player.sendMessage("§aYou received a §6Drenix Coin§a!");
        return true;
    }
}

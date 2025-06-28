package me.drenix.drenixcoin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CoinListener implements Listener {

    private final Main plugin;

    public CoinListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null && item.getType() == Material.SUNFLOWER && item.hasItemMeta()
                && "§6Drenix Coin".equals(item.getItemMeta().getDisplayName())) {

            item.setAmount(item.getAmount() - 1);
            int balance = plugin.getConfig().getInt("balances." + player.getUniqueId(), 0);
            plugin.getConfig().set("balances." + player.getUniqueId(), balance + 1);
            plugin.saveConfig();
            player.sendMessage("§aDrenix Coin stored. Check with §e/bal§a.");
        }
    }
}

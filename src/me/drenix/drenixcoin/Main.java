package me.drenix.drenixcoin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("exchange").setExecutor(new ExchangeCommand(this));
        getCommand("bal").setExecutor(new BalanceCommand(this));
        getCommand("baltop").setExecutor(new BaltopCommand(this));
        getCommand("drenixcoin").setExecutor(new HelpCommand(this));
        getServer().getPluginManager().registerEvents(new CoinListener(this), this);
    }
}

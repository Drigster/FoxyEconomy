package me.drigster.foxyeconomy;

import me.drigster.foxycore.FoxyCore;
import me.drigster.foxyeconomy.commands.ConvertCoinsCommand;
import me.drigster.foxyeconomy.commands.GetCoinsCommand;
import me.drigster.foxyeconomy.commands.SpawnBankerCommand;
import me.drigster.foxyeconomy.entitys.Banker;
import me.drigster.foxyeconomy.files.Localization;
import me.drigster.foxyeconomy.files.Data;
import me.drigster.foxyeconomy.guis.BankerGui;
import me.drigster.foxyeconomy.guis.ConvertGui;
import me.drigster.foxyeconomy.managers.ItemManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FoxyEconomy extends JavaPlugin {

    private FoxyEconomy plugin;

    @Override
    public void onEnable() {
        plugin = this;

        PluginManager pm = getServer().getPluginManager();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        Data.init();
        Localization.init();

        FoxyCore.setPlugin(this);
        System.out.println("economy " + Localization.get());
        FoxyCore.setLocalization(Localization.get());

        ItemManager.init();

        pm.registerEvents(new Banker(), this);
        pm.registerEvents(new BankerGui(), this);
        pm.registerEvents(new ConvertGui(), this);

        getCommand("spawnBanker").setExecutor(new SpawnBankerCommand());
        getCommand("getcoins").setExecutor(new GetCoinsCommand());
        getCommand("convert").setExecutor(new ConvertCoinsCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

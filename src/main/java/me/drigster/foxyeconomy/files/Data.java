package me.drigster.foxyeconomy.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Data {

    private static File file;
    private static FileConfiguration dataFile;

    public static void init(){
        setup();
        save();
    }

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("FoxyEconomy").getDataFolder(), "Data.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                //
            }
        }
        dataFile = YamlConfiguration.loadConfiguration(file);
        addDefaults();
    }

    public static FileConfiguration get(){
        return dataFile;
    }

    public static void save(){
        try {
            dataFile.save(file);
        } catch (IOException e){
            System.out.println("File save error\n" + e.getMessage());
        }
    }

    public static void reload(){
        dataFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void addDefaults(){
        dataFile.options().copyDefaults(true);
    }
}

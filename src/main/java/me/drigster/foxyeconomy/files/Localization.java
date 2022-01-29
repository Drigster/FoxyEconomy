package me.drigster.foxyeconomy.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Localization {

    private static File file;
    private static FileConfiguration localizationFile;

    public static void init(){
        setup();
        localizationFile.options().copyDefaults(true);
        save();
    }

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("FoxyEconomy").getDataFolder(), "localization.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                //
            }
        }
        localizationFile = YamlConfiguration.loadConfiguration(file);
        addDefaults();
    }

    public static FileConfiguration get(){
        return localizationFile;
    }

    public static void save(){
        try {
            localizationFile.save(file);
        } catch (IOException e){
            System.out.println("File save error\n" + e.getMessage());
        }
    }

    public static void reload(){
        localizationFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void addDefaults(){
        //Words
        localizationFile.addDefault("prefix", "&7[&6FoxyEconomy&7] ");
        localizationFile.addDefault("words.convert", "&3Конвертировать");
        localizationFile.addDefault("words.converter", "&3Конвертер");
        localizationFile.addDefault("words.background", "Фон");
        localizationFile.addDefault("words.withdraw", "&3Получить монеты");
        localizationFile.addDefault("words.back", "&3Назад");
        //Messages
        localizationFile.addDefault("messages.got-coins", "&3Вы получили &6%coinsReceived% &3монет!");
        localizationFile.addDefault("messages.convert.iron-to-gold", "&3Вы конвертировали &7%ironRatio% железных &3монеты в &6золотую!");
        localizationFile.addDefault("messages.convert.gold-to-diamond", "&3Вы конвертировали &6%goldRatio% золотых &3монеты в &bалмазную&3!");
        localizationFile.addDefault("messages.convert.diamond-to-gold", "&3Вы конвертировали &bалмазную &3монету в &6%goldRatio% золотых&3!");
        localizationFile.addDefault("messages.convert.gold-to-iron", "&3Вы конвертировали &6золотую &3монету а &7%ironRatio% железных&3!");
        //Errors
        localizationFile.addDefault("errors.not-enough-space", "§4Недостаточно места!");
        localizationFile.addDefault("errors.not-enough-coins", "§4Недостаточно монет!");
        localizationFile.addDefault("errors.need-to-wait", "&4Монета может быть получена через %minutes% минут!");
        localizationFile.options().copyDefaults(true);
    }
}

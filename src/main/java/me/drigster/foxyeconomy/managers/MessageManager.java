package me.drigster.foxyeconomy.managers;

import me.drigster.foxyeconomy.FoxyEconomy;
import me.drigster.foxyeconomy.files.Localization;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;

public class MessageManager {

    static Plugin plugin = FoxyEconomy.getPlugin(FoxyEconomy.class);
    static Configuration config = plugin.getConfig();

    public static void sendMessage(Player p, String code){
        String message = getLocalizedString(code);
        message = getLocalizedString("prefix") + message;

        p.sendMessage(message);
    }

    public static String getLocalizedString(String code){
        String localizedString = Localization.get().getString(code);
        if(localizedString == null){
            localizedString = Localization.get().getDefaults().getString(code);
            if(localizedString == null){
                localizedString = code;
                return localizedString;
            }
        }

        localizedString = localizedString.replaceAll("%ironRatio%", Objects.requireNonNull(config.getString("convertRatio.ironToGold")));
        localizedString = localizedString.replaceAll("%goldRatio%", Objects.requireNonNull(config.getString("convertRatio.goldToDiamond")));

        localizedString = localizedString.replace("&", "§");

        return localizedString;
    }

    public static String getLocalizedStringWithPlaceholder(String code, String placeholder, String filler){
        String string = getLocalizedString(code);
        string = string.replaceAll(placeholder, filler);
        return string;
    }

    public static String getLocalizedStringWithPlaceholder(String code, List<String> placeholders, List<String> fillers){
        String string = getLocalizedString(code);
        for (int i=0; i<placeholders.size(); i++)
        {
            String placeholder = placeholders.get(i);
            String filler = fillers.get(i);
            string = string.replaceAll(placeholder, filler);
        }
        return string;
    }

}

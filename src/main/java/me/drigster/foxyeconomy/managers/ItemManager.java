package me.drigster.foxyeconomy.managers;

import me.drigster.foxyeconomy.items.Coins;
import me.drigster.foxyeconomy.items.GuiElements;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static void init() {
        Coins.init();
        GuiElements.init();
    }

}

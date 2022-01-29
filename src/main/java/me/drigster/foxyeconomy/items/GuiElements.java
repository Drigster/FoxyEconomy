package me.drigster.foxyeconomy.items;

import me.drigster.foxyeconomy.files.Localization;
import me.drigster.foxyeconomy.managers.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiElements {
    public static ItemStack blank;
    public static ItemStack back;
    public static ItemStack rightArrow;
    public static ItemStack leftArrow;
    public static ItemStack withdraw;
    public static ItemStack convert;

    public static ItemStack diamondCoin;
    public static ItemStack goldCoin;
    public static ItemStack ironCoin;

    public static ItemStack goldCoin64;

    public static void init(){
        createRightArrow();
        createLeftArrow();
        createBlank();
        createBack();
        createWithdraw();
        createConvert();

        createDiamondCoin();
        createGoldCoin();
        createIronCoin();

        createGoldCoin64();
    }

    private static void createBlank() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageManager.getLocalizedString("words.background"));

        item.setItemMeta(meta);

        blank = item;
    }

    private static void createBack() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageManager.getLocalizedString("words.back"));

        meta.setCustomModelData(4);
        item.setItemMeta(meta);

        back = item;
    }

    private static void createRightArrow() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageManager.getLocalizedString("words.convert"));

        meta.setCustomModelData(1);
        item.setItemMeta(meta);

        rightArrow = item;
    }

    private static void createLeftArrow() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageManager.getLocalizedString("words.convert"));

        meta.setCustomModelData(2);
        item.setItemMeta(meta);

        leftArrow = item;
    }

    private static void createWithdraw() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageManager.getLocalizedString("words.withdraw"));

        meta.setCustomModelData(3);
        item.setItemMeta(meta);

        withdraw = item;
    }

    private static void createConvert() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageManager.getLocalizedString("words.convert"));

        meta.setCustomModelData(5);
        item.setItemMeta(meta);

        convert = item;
    }

    private static void createDiamondCoin() {
        ItemStack item = new ItemStack(Material.FIREWORK_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Алмазная монета");

        meta.setCustomModelData(1);
        item.setItemMeta(meta);

        diamondCoin = item;
    }

    private static void createGoldCoin() {
        ItemStack item = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Золотая монета");

        meta.setCustomModelData(2);
        item.setItemMeta(meta);

        goldCoin = item;
    }

    private static void createGoldCoin64() {
        ItemStack item = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Золотая монета");

        meta.setCustomModelData(2);
        item.setItemMeta(meta);

        item.setAmount(64);

        goldCoin64 = item;
    }

    private static void createIronCoin() {
        ItemStack item = new ItemStack(Material.FIREWORK_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Железная монета");

        meta.setCustomModelData(3);
        item.setItemMeta(meta);

        ironCoin = item;
    }

}

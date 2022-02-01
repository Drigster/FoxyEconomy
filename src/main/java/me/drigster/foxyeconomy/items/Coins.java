package me.drigster.foxyeconomy.items;

import me.drigster.foxycore.managers.MessageManager;
import me.drigster.foxyeconomy.FoxyEconomy;
import me.drigster.foxyeconomy.files.Data;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Coins {

    static Plugin plugin = FoxyEconomy.getPlugin(FoxyEconomy.class);

    public static ItemStack diamondCoin;
    public static ItemStack goldCoin;
    public static ItemStack ironCoin;

    public enum Convert {
        IRONTOGOLD,
        GOLDTODIAMOND,
        GOLDTOIRON,
        DIAMONDTOGOLD
    }

    public static void convert(Player p, Convert type){

        Inventory inventory = p.getInventory();

        switch (type){
            case IRONTOGOLD: {
                if(inventory.containsAtLeast(ironCoin, 64)){
                    if(addExact(inventory, goldCoin)){
                        ironCoin.setAmount(64);
                        inventory.removeItem(ironCoin);
                        MessageManager.sendMessageWithPlaceholder(p, "messages.convert.iron-to-gold", "%ironRatio%", plugin.getConfig().getString("convertRatio.ironToGold"));
                    }
                    else{
                        MessageManager.sendMessage(p, "errors.not-enough-space");
                    }
                }
                else {
                    MessageManager.sendMessage(p, "errors.not-enough-coins");
                }

                break;
            }
            case GOLDTODIAMOND: {
                if(inventory.containsAtLeast(goldCoin, 64)){
                    if(addExact(inventory, diamondCoin)){
                        goldCoin.setAmount(64);
                        inventory.removeItem(goldCoin);
                        MessageManager.sendMessageWithPlaceholder(p, "messages.convert.gold-to-diamond", "%goldRatio%", plugin.getConfig().getString("convertRatio.goldToDiamond"));
                    }
                    else{
                        MessageManager.sendMessage(p, "errors.not-enough-space");
                    }
                }
                else {
                    MessageManager.sendMessage(p, "errors.not-enough-coins");
                }
                break;
            }
            case GOLDTOIRON: {
                if(inventory.containsAtLeast(goldCoin, 1)){
                    ironCoin.setAmount(64);
                    if(addExact(inventory, ironCoin)){
                        goldCoin.setAmount(1);
                        inventory.removeItem(goldCoin);
                        MessageManager.sendMessageWithPlaceholder(p, "messages.convert.gold-to-iron", "%ironRatio%", plugin.getConfig().getString("convertRatio.ironToGold"));
                    }
                    else{
                        MessageManager.sendMessage(p, "errors.not-enough-space");
                    }
                }
                else {
                    MessageManager.sendMessage(p, "errors.not-enough-coins");
                }
                break;
            }
            case DIAMONDTOGOLD: {
                if(inventory.containsAtLeast(diamondCoin, 1)){
                    goldCoin.setAmount(64);
                    if(addExact(inventory, goldCoin)){
                        diamondCoin.setAmount(1);
                        inventory.removeItem(diamondCoin);
                        MessageManager.sendMessageWithPlaceholder(p, "messages.convert.diamond-to-gold", "%goldRatio%", plugin.getConfig().getString("convertRatio.goldToDiamond"));
                    }
                    else{
                        MessageManager.sendMessage(p, "errors.not-enough-space");
                    }
                }
                else {
                    MessageManager.sendMessage(p, "errors.not-enough-coins");
                }
                break;
            }
        }
    }

    private static boolean addExact(Inventory inventory, ItemStack stack){
        int addAmount = stack.getAmount();
        HashMap<Integer, ItemStack> addResult = inventory.addItem(stack);
        if (addResult.isEmpty()){
            return true;
        } else {
            ItemStack remainingStack = addResult.get(0);
            int removeAmount = addAmount - remainingStack.getAmount();
            stack.setAmount(removeAmount);
            inventory.removeItem(stack);
            return false;
        }
    }

    public static void withdraw(Player p){
        int time = p.getStatistic(Statistic.PLAY_ONE_MINUTE);
        int seconds = time / 20;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        String timeSpend = Data.get().getString("players." + p.getUniqueId() + ".timeSpend");

        if(timeSpend == null){
            timeSpend = "0";
        }

        int hoursLeft = hours - Integer.parseInt(timeSpend);

        if(hoursLeft > plugin.getConfig().getInt("hours")){
            ItemStack coins = Coins.goldCoin;
            coins.setAmount(hoursLeft / plugin.getConfig().getInt("hours"));
            p.getInventory().addItem(coins);
            Data.get().set("players." + p.getUniqueId() + ".timeSpend", Integer.parseInt(timeSpend) + coins.getAmount() * plugin.getConfig().getInt("hours"));
            Data.save();
            p.sendMessage(MessageManager.createLocalizedStringWithPlaceholder("messages.got-coins", "%coinsReceived%", String.valueOf(coins.getAmount())));
        }
        else {
            p.sendMessage(MessageManager.createLocalizedStringWithPlaceholder("errors.need-to-wait", "%minutes%", String.valueOf(60 - (minutes - hours * 60))));
        }
    }

    public static void init(){
        createDiamondCoin();
        createGoldCoin();
        createironCoin();
    }

    private static void createDiamondCoin() {
        ItemStack item = new ItemStack(Material.FIREWORK_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Алмазная монета");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Используется для трейда.");
        lore.add("");
        lore.add(ChatColor.DARK_AQUA + "Можно обменять у банкира:");
        lore.add(ChatColor.DARK_AQUA + "64 " + ChatColor.GRAY + "серебряных " + ChatColor.DARK_AQUA + "<-> 1 " +
                ChatColor.GOLD + "золотая");
        lore.add(ChatColor.DARK_AQUA + "64 " + ChatColor.GOLD + "золотых " + ChatColor.DARK_AQUA + "<-> 1 " +
                ChatColor.AQUA + "алмазная ");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE, ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.setCustomModelData(1);
        item.setItemMeta(meta);
        diamondCoin = item;
    }

    private static void createGoldCoin() {
        ItemStack item = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Золотая монета");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Используется для трейда.");
        lore.add("");
        lore.add(ChatColor.DARK_AQUA + "Можно обменять у банкира:");
        lore.add(ChatColor.DARK_AQUA + "64 " + ChatColor.GRAY + "серебряных " + ChatColor.DARK_AQUA + "<-> 1 " +
                ChatColor.GOLD + "золотая");
        lore.add(ChatColor.DARK_AQUA + "64 " + ChatColor.GOLD + "золотых " + ChatColor.DARK_AQUA + "<-> 1 " +
                ChatColor.AQUA + "алмазная ");
        meta.setLore(lore);
        //meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setCustomModelData(2);
        item.setItemMeta(meta);
        goldCoin = item;
    }

    private static void createironCoin() {
        ItemStack item = new ItemStack(Material.FIREWORK_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Железная монета");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Используется для трейда.");
        lore.add("");
        lore.add(ChatColor.DARK_AQUA + "Можно обменять у банкира:");
        lore.add(ChatColor.DARK_AQUA + "64 " + ChatColor.GRAY + "серебряных " + ChatColor.DARK_AQUA + "<-> 1 " +
                ChatColor.GOLD + "золотая");
        lore.add(ChatColor.DARK_AQUA + "64 " + ChatColor.GOLD + "золотых " + ChatColor.DARK_AQUA + "<-> 1 " +
                ChatColor.AQUA + "алмазная ");
        meta.setLore(lore);
        //meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE, ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.setCustomModelData(3);
        item.setItemMeta(meta);
        ironCoin = item;
    }
}

package me.drigster.foxyeconomy.guis;

import me.drigster.foxycore.items.GuiElements;
import me.drigster.foxyeconomy.items.Coins;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BankerGui implements Listener {
    private static Inventory gui;

    public static void openNewGui(Player p){

        generateGui();

        p.openInventory(gui);

    }

    @EventHandler
    public void guiClickEvent(InventoryClickEvent e){

        if(e.getClickedInventory() == null){
            return;
        }
        if(!e.getClickedInventory().equals(gui)){
            return;
        }

        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();

        switch (e.getSlot()){
            case 1: {
                Coins.withdraw(p);
                break;
            }
            case 3: {
                ConvertGui.openNewGui(p);
                break;
            }
        }
    }

    public static void generateGui(){

        gui = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.DARK_AQUA + "Converter");

        ItemStack blank = GuiElements.blank;
        ItemStack withdrawIcon = GuiElements.withdraw;
        ItemStack convertIcon = GuiElements.convert;

        ItemStack[] menuItems = {blank, withdrawIcon, blank, convertIcon, blank};
        gui.setContents(menuItems);

    }
}

package me.drigster.foxyeconomy.guis;

import me.drigster.foxycore.items.GuiElements;
import me.drigster.foxycore.managers.MessageManager;
import me.drigster.foxyeconomy.items.Coins;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ConvertGui implements Listener {
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
            case 2: {
                Coins.convert(p, Coins.Convert.IRONTOGOLD);
                break;
            }
            case 6: {
                Coins.convert(p, Coins.Convert.GOLDTODIAMOND);
                break;
            }
            case 11: {
                Coins.convert(p, Coins.Convert.GOLDTOIRON);
                break;
            }
            case 15: {
                Coins.convert(p, Coins.Convert.DIAMONDTOGOLD);
                break;
            }
            case 17: {
                BankerGui.openNewGui(p);
                break;
            }
        }
    }

    public static void generateGui(){

        gui = Bukkit.createInventory(null, 18, MessageManager.createLocalizedString("words.converter"));

        ItemStack blank = GuiElements.blank;
        ItemStack back = GuiElements.back;
        ItemStack arrowRightIcon = GuiElements.rightArrow;
        ItemStack arrowLeftIcon = GuiElements.leftArrow;

        ItemStack diamondCoin = GuiElements.diamondCoin;
        ItemStack goldCoin = GuiElements.goldCoin;
        ItemStack ironCoin = GuiElements.ironCoin;
        ironCoin.setAmount(64);

        ItemStack goldCoin64 = GuiElements.goldCoin64;

        ItemStack[] menuItems = {blank, ironCoin, arrowRightIcon, goldCoin, blank, goldCoin64, arrowRightIcon, diamondCoin, blank,
                                blank, ironCoin, arrowLeftIcon, goldCoin, blank, goldCoin64, arrowLeftIcon, diamondCoin, back};
        gui.setContents(menuItems);

    }
}

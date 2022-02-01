package me.drigster.foxyeconomy.managers;

import me.drigster.foxycore.items.GuiElements;
import me.drigster.foxyeconomy.items.Coins;

public class ItemManager {

    public static void init() {
        Coins.init();
        GuiElements.init();
    }

}

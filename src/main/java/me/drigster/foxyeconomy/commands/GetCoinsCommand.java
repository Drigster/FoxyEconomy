package me.drigster.foxyeconomy.commands;

import me.drigster.foxyeconomy.files.Data;
import me.drigster.foxyeconomy.items.Coins;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetCoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player p){
            ItemStack coins = Coins.goldCoin;
            coins.setAmount(64);
            p.getInventory().addItem(coins);
        }

        return true;
    }
}

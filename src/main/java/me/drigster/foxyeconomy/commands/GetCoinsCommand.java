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
            int time = p.getStatistic(Statistic.PLAY_ONE_MINUTE);
            int seconds = time / 20;
            int minutes = seconds / 60;
            int hours = minutes / 60;

            String coinsReceived = Data.get().getString("players." + p.getUniqueId() + ".coinsReceived");

            if(coinsReceived == null){
                coinsReceived = "0";
            }

            int canBeRecieved = minutes - Integer.parseInt(coinsReceived);

            if(canBeRecieved > 0){
                ItemStack coins = Coins.goldCoin;
                coins.setAmount(canBeRecieved);
                p.getInventory().addItem(coins);
                Data.get().set("players." + p.getUniqueId() + ".coinsReceived", minutes);
                Data.save();
            }
            else {
                p.sendMessage("Next coin can be received in " + (60 - (seconds - minutes * 60)) + " seconds");
            }
        }

        return true;
    }
}

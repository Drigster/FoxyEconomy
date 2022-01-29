package me.drigster.foxyeconomy.commands;

import me.drigster.foxyeconomy.guis.ConvertGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConvertCoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player p){
            ConvertGui.openNewGui(p);
        }

        return true;
    }
}

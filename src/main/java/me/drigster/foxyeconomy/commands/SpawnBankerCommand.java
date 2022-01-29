package me.drigster.foxyeconomy.commands;

import me.drigster.foxyeconomy.entitys.Banker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class SpawnBankerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player p){
            Banker.spawn(p.getLocation());
        }

        return true;
    }

}

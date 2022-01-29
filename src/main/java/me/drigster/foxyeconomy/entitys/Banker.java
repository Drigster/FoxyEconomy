package me.drigster.foxyeconomy.entitys;

import me.drigster.foxyeconomy.files.Data;
import me.drigster.foxyeconomy.guis.BankerGui;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.UUID;

public class Banker implements Listener {

    public static void spawn(Location location){
        Villager banker = location.getWorld().spawn(location, Villager.class);
        banker.setAI(false);
        banker.setCanPickupItems(false);
        banker.setCustomName("Banker");
        banker.setInvulnerable(true);
        banker.setProfession(Villager.Profession.NONE);
        Data.get().set("banker", banker.getUniqueId().toString());
        Data.save();
    }

    @EventHandler
    public void playerRightClickEvent(PlayerInteractEntityEvent e){
        if(e.getRightClicked() instanceof Villager v){
            if(v.getCustomName() == null){
                return;
            }
            if(v.getCanPickupItems() && v.getCustomName().equals("Banker")){
                load();
            }
            if(!v.getCanPickupItems() && v.getCustomName().equals("Banker")){
                e.setCancelled(true);
                BankerGui.openNewGui(e.getPlayer());
            }
        }
    }

    public static void load(){
        String uuid = Data.get().getString("banker");
        if(uuid != null){
            Villager banker = (Villager) Bukkit.getServer().getEntity(UUID.fromString(uuid));
            banker.setCanPickupItems(false);
        }

    }
}

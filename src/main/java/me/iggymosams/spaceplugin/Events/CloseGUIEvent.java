package me.iggymosams.spaceplugin.Events;


import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;


public class CloseGUIEvent implements Listener {
    Plugin plugin = Spaceplugin.getPlugin();
    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getView().getTitle().equals("Where to?")){
            Inventory i = e.getInventory();
            Bukkit.getScheduler ().runTaskLater (plugin, () -> e.getPlayer().openInventory(i),  1);

        }
    }
}

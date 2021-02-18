package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.LinkedHashMap;

public class DeathEvent implements Listener {
    Plugin plugin = Spaceplugin.getPlugin();

    LinkedHashMap<String, Integer> oxygen = Spaceplugin.getPlugin().getOxygen();

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        if(oxygen.containsKey(p.getName())){
            ItemStack i = p.getInventory().getHelmet();
            ItemMeta im = i.getItemMeta();
            PersistentDataContainer data = im.getPersistentDataContainer();
            int remaining = oxygen.get(p.getName()).intValue();
            data.set(new NamespacedKey(plugin, "oxygen"), PersistentDataType.INTEGER, remaining);
            i.setItemMeta(im);
            p.setGravity(true);
            oxygen.remove(p.getName());
        }
    }
}

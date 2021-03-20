package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.LinkedHashMap;

public class QuitEvent implements Listener {

    Plugin plugin = Spaceplugin.getPlugin();

    LinkedHashMap<String, Float> oxygen = Spaceplugin.getPlugin().getOxygen();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (oxygen.containsKey(p.getName())) {
            ItemStack i = p.getInventory().getHelmet();
            ItemMeta im = i.getItemMeta();
            PersistentDataContainer data = im.getPersistentDataContainer();
            float remaining = oxygen.get(p.getName()).floatValue();
            data.set(new NamespacedKey(plugin, "oxygen"), PersistentDataType.FLOAT, remaining);
            i.setItemMeta(im);
            oxygen.remove(p.getName());
        }
    }
}

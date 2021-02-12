package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.LinkedHashMap;

public class JoinEvent implements Listener{
    Plugin plugin = Spaceplugin.getPlugin();
    LinkedHashMap<String, Integer> oxygen = Spaceplugin.getPlugin().getOxygen();
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Inventory i = p.getInventory();
        if(p.getInventory().getHelmet() != null) {
            if (p.getInventory().getHelmet().getType().equals(Material.LEATHER_HELMET)) {
                if (p.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spacesuit Helmet")) {
                    ItemStack item = p.getInventory().getHelmet();
                    ItemMeta itemMeta = item.getItemMeta();
                    PersistentDataContainer data = itemMeta.getPersistentDataContainer();
                    int current = data.get(new NamespacedKey(plugin, "oxygen"), PersistentDataType.INTEGER);
                    p.sendMessage(String.valueOf(current));
                    oxygen.put(p.getName(), current);
                }
            }
        }
    }
}

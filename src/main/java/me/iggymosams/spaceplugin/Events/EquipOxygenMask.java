package me.iggymosams.spaceplugin.Events;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class EquipOxygenMask implements Listener {

    int oxygen;
    int taskID;



    Plugin plugin = Spaceplugin.getPlugin();

    @EventHandler
    public void onEquip(ArmorEquipEvent e){
        Player p = e.getPlayer();
        if(e.getNewArmorPiece() != null){
            if(e.getNewArmorPiece().getType() == Material.LEATHER_HELMET || e.getNewArmorPiece().getType() == Material.LEATHER_CHESTPLATE || e.getNewArmorPiece().getType() == Material.LEATHER_LEGGINGS || e.getNewArmorPiece().getType() == Material.LEATHER_BOOTS){
                if(e.getNewArmorPiece().getItemMeta().isUnbreakable() == true){
                    e.setCancelled(true);
                    p.sendMessage("To equip this suit use /equip");
                }
            }
        }
        if(e.getOldArmorPiece() != null && e.getOldArmorPiece().getType() != Material.AIR) {
            if (e.getOldArmorPiece().getType() == Material.IRON_HELMET) {

                ItemStack item = e.getOldArmorPiece();

                ItemMeta itemMeta = item.getItemMeta();
                PersistentDataContainer data = itemMeta.getPersistentDataContainer();
                data.set(new NamespacedKey(plugin, "oxygen"), PersistentDataType.INTEGER, oxygen);
                data.set(new NamespacedKey(plugin, "oxygen"), PersistentDataType.INTEGER, oxygen);
                item.setItemMeta(itemMeta);
                e.setNewArmorPiece(item);
                stopTimer();
            }

        }
    }

    public void startTimer() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(oxygen == 0) {
                    Bukkit.broadcastMessage(ChatColor.RED + "Time is up!");
                    stopTimer();
                    return;
                }

                if(oxygen % 5 == 0) {
                    Bukkit.broadcastMessage(ChatColor.RED + "Timer remaining " + oxygen + " seconds");
                }
                Bukkit.getServer().broadcastMessage(String.valueOf(oxygen));
                oxygen--;

            }
        }, 0L, 20L);
    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
}

package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.UUID;

public class RocketSpawnEvent implements Listener {
    Plugin plugin = Spaceplugin.getPlugin();
    Inventory inv;
    int delay = 3;

    @EventHandler
    public void RocketSpawn(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getItem() != null){
                if(e.getItem().getType().equals(Material.FIREWORK_ROCKET)) {
                    if (e.getItem().getItemMeta().getDisplayName().equals("Rocket")) {
                        p.setAllowFlight(true);

                        p.setGravity(false);
                        p.setVelocity(new Vector(0, 1000, 0));

                        initializeItems();
                        Bukkit.getScheduler ().runTaskLater (plugin, () -> p.setFlying(true), delay * 20);
                        Bukkit.getScheduler ().runTaskLater (plugin, () -> p.openInventory(inv), delay * 20);

                    }
                }
            }
        }
    }
    public void initializeItems(){
        inv = Bukkit.createInventory(null, 9, "Where to?");

        ItemStack earth = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta earthMeta = (SkullMeta) earth.getItemMeta();
        earthMeta.setOwningPlayer(Bukkit.getOfflinePlayer("earth"));
        earthMeta.setDisplayName(ChatColor.GREEN + "Earth");
        earth.setItemMeta(earthMeta);

        ItemStack moon = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta moonhMeta = (SkullMeta) moon.getItemMeta();
        moonhMeta.setOwningPlayer(Bukkit.getOfflinePlayer("gng2546atc"));
        moonhMeta.setDisplayName(ChatColor.GRAY + "Moon");
        moon.setItemMeta(moonhMeta);

        inv.setItem(2, earth);
        inv.setItem(4, moon);
    }
}

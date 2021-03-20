package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;


public class RocketSpawnEvent implements Listener {
    Plugin plugin = Spaceplugin.getPlugin();

    Inventory inv;

    int delay = 3;

    World voidworld = Spaceplugin.getVoidworld();

    @EventHandler
    public void RocketSpawn(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getItem() != null) {
                if (e.getItem().getType().equals(Material.FIREWORK_ROCKET)) {
                    if (e.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Rocket")) {
                        p.setAllowFlight(true);
                        p.setGravity(false);
                        p.setVelocity(new Vector(0, 1000, 0));
                        Bukkit.getScheduler().runTaskLater(plugin, () -> p.setFlying(true), delay * 20);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> initializeItems(p), delay * 20);

                    }
                }
            }
        }
    }

    public void initializeItems(Player p) {
        p.setGameMode(GameMode.ADVENTURE);
        p.teleport(new Location(voidworld, 3.5, 80, 0.5));
        World w = p.getWorld();
        w.setDifficulty(Difficulty.PEACEFUL);
        //p.setWalkSpeed(0.2F);
        p.setWalkSpeed(0f);
    }
}

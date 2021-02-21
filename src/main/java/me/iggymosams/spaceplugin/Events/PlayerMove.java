package me.iggymosams.spaceplugin.Events;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.*;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerMove implements Listener {

    ProtocolManager protocolManager = Spaceplugin.getProtocolManager();

    Plugin plugin = Spaceplugin.getPlugin();

    LinkedHashMap<String, Float> oxygen = Spaceplugin.getPlugin().getOxygen();

    ArrayList<Player> grav = Spaceplugin.getPlugin().gravityboots;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        World w = e.getFrom().getWorld();
        PlayerInventory i = p.getInventory();

        if (w == Spaceplugin.getMoon()) {
            if(grav.contains(p)){
                p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                p.removePotionEffect(PotionEffectType.JUMP);
            }else{
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 99999 * 20, 3, false, false, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999 * 20, 0, false, false, false));
            }
        }else if(w == Spaceplugin.getVoidworld()){
            //p.sendMessage("move");
            List<Entity> entities =  w.getEntities();
            ArmorStand earth = null;
            ArmorStand moon = null;
            for (Entity ent : entities){
                if(ent.getType().equals(EntityType.ARMOR_STAND)){
                    if(ent.getName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Earth")){
                        earth = (ArmorStand) ent;
                    }else if(ent.getName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "Moon")){
                        moon = (ArmorStand) ent;
                    }
                }
            }
        }
    }
}
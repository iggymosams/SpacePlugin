package me.iggymosams.spaceplugin.Events;


import com.comphenix.protocol.ProtocolManager;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
            if (grav.contains(p)) {
                p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                p.removePotionEffect(PotionEffectType.JUMP);
            } else {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 99999 * 20, 3, false, false, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999 * 20, 0, false, false, false));
            }
        } else if (w == Spaceplugin.getVoidworld()) {
            //p.sendMessage("move");
            List<Entity> entities = w.getEntities();
            ArmorStand earth = null;
            ArmorStand moon = null;
            for (Entity ent : entities) {
                if (ent.getType().equals(EntityType.ARMOR_STAND)) {
                    if (ent.getName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Earth")) {
                        earth = (ArmorStand) ent;
                    } else if (ent.getName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "Moon")) {
                        moon = (ArmorStand) ent;
                    }
                }
            }
        }
    }
}
package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Managers.Scoreboard;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class InteractEntity implements Listener {

    Scoreboard sb = new Scoreboard();

    @EventHandler
    public void onEntityInteract(PlayerInteractAtEntityEvent e){
        Player p = e.getPlayer();
        if(p.getWorld().equals(Spaceplugin.getVoidworld())) {
            if (e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
                e.setCancelled(true);
                if(e.getRightClicked().getName().equals(ChatColor.GREEN +"" +ChatColor.BOLD + "Earth")){
                    Vector pos = e.getRightClicked().getLocation().toVector();
                    Vector target = Bukkit.getPlayer(p.getName()).getLocation().toVector();
                    Vector velocity = target.subtract(pos);
                    e.getRightClicked().setVelocity(velocity.normalize().multiply(-0.5));

                    p.teleport(new Location(Bukkit.getWorld("world"), 0, 80, 0));
                    p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                    p.removePotionEffect(PotionEffectType.JUMP);
                    p.setAllowFlight(false);
                    p.setGravity(true);
                    sb.UpdateScoreboard(p);
                }else if(e.getRightClicked().getName().equals(ChatColor.GRAY +"" +ChatColor.BOLD + "Moon")){

                    Vector pos = e.getRightClicked().getLocation().toVector();
                    Vector target = Bukkit.getPlayer(p.getName()).getLocation().toVector();
                    Vector velocity = target.subtract(pos);
                    e.getRightClicked().setVelocity(velocity.normalize().multiply(-0.5));

                    p.teleport(new Location(Bukkit.getWorld("moon"), 0, 80, 0));
                    p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                    p.removePotionEffect(PotionEffectType.JUMP);
                    p.setAllowFlight(false);
                    p.setGravity(true);
                    sb.UpdateScoreboard(p);
                }
            }
        }
    }
}

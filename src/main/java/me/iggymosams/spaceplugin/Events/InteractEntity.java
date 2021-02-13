package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Managers.Scoreboard;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class InteractEntity implements Listener {

    Scoreboard sb = new Scoreboard();

    @EventHandler
    public void onEntityInteract(PlayerInteractAtEntityEvent e){
        Player p = e.getPlayer();
        if(p.getWorld().equals(Spaceplugin.getVoidworld())) {
            if (e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
                e.setCancelled(true);
                p.setWalkSpeed(0.2F);
                p.setGameMode(GameMode.SURVIVAL);
                if(e.getRightClicked().getName().equals(ChatColor.GREEN +"" +ChatColor.BOLD + "Earth")){
                    p.teleport(new Location(Bukkit.getWorld("world"), 0, 80, 0));
                    p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                    p.removePotionEffect(PotionEffectType.JUMP);
                    p.setAllowFlight(false);
                    p.setGravity(true);
                    sb.UpdateScoreboard(p);
                }else if(e.getRightClicked().getName().equals(ChatColor.GRAY +"" +ChatColor.BOLD + "Moon")){

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

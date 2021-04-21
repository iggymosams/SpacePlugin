package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DropEvent implements Listener {
    //Craft entity;
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(p.getWorld() == Spaceplugin.getMoon()){
            //entity = e.getItemDrop();
            //entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 99999 * 20, 3, false, false, false));
            //entity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999 * 20, 0, false, false, false));
        }
    }
}

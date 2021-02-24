package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.api;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Random;

public class ArrowHitEvent implements Listener {
    @EventHandler
    public void onHit(ProjectileHitEvent e){
        if(e.getHitEntity() instanceof Player){
            Player p = (Player) e.getHitEntity();
            if(p.getEquipment().getHelmet().getItemMeta().isUnbreakable() && p.getEquipment().getChestplate().getItemMeta().isUnbreakable() && p.getEquipment().getLeggings().getItemMeta().isUnbreakable() && p.getEquipment().getBoots().getItemMeta().isUnbreakable()){

                Random rand = new Random();
                int number = rand.nextInt(100) + 1;
                System.out.println(number);
                if(number >= 60)
                {
                    p.sendTitle(null, api.getMessage("SuitPierced"), 1, 5*20, 1);
                    p.damage(7);
                }
            }
        }
    }
}

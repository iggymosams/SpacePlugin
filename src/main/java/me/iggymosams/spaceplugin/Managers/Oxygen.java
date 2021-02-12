package me.iggymosams.spaceplugin.Managers;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.LinkedHashMap;

public final class Oxygen {

    Plugin plugin = Spaceplugin.getPlugin();
    LinkedHashMap<String, Integer> oxygen = Spaceplugin.getPlugin().getOxygen();
    World moon = Spaceplugin.getMoon();


    public void CheckOxygen(){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getServer().getOnlinePlayers()){
                    if(oxygen.containsKey(p.getName())){
                        int o = oxygen.get(p.getName()).intValue();
                        if(p.getWorld().equals(moon)){
                            //sb.UpdateScoreboard(p);
                            if(o > 0) {
                                oxygen.put(p.getName(), o - 1);
                                p.sendMessage(String.valueOf(o));
                            }else{
                                o = 0;
                                p.damage(5
                                );
                            }
                        }
                    }
                }
            }
        },0L,20L);

    }
}
package me.iggymosams.spaceplugin.Managers;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class OxygenCollecter {


    public void OxygenCollecter(){

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Spaceplugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                List<World> worlds = Bukkit.getWorlds();
                for (World w : worlds){
                    List<Entity> e = w.getEntities();
                    for (Entity ent : e){
                        if(ent.getType().equals(EntityType.ARMOR_STAND)){
                            ArmorStand a = (ArmorStand) ent;
                            PersistentDataContainer data = a.getPersistentDataContainer();
                            if(data.has(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollector"), PersistentDataType.INTEGER)){
                                if(!a.getCustomName().equals(ChatColor.LIGHT_PURPLE + "Oxygen Collector"))
                                    if(String.valueOf(data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollector"), PersistentDataType.INTEGER)).equals("100")){
                                        int i = data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER);
                                        if(i == 100){
                                            a.setCustomName(ChatColor.RED + "100/100");
                                        }else{
                                            a.setCustomName(ChatColor.LIGHT_PURPLE + "" + (i + 1) + "/100");
                                            data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER, (i + 1));
                                        }

                                    }
                            }
                        }
                    }
                }
            }
        },0L,20L);
    }
}

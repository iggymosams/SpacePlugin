package me.iggymosams.spaceplugin.Managers;


import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.text.DecimalFormat;
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
                                if(w == Bukkit.getWorld("world")){
                                    if(!a.getCustomName().equals(ChatColor.LIGHT_PURPLE + "Oxygen Collector"))
                                        if(String.valueOf(data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollector"), PersistentDataType.INTEGER)).equals("100")) {
                                            float i = data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT);
                                            if (i == 100) {
                                                a.setCustomName(ChatColor.RED + "100/100");
                                            } else {
                                                a.setCustomName(ChatColor.LIGHT_PURPLE + "" + (i + 1) + "/100");
                                                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT, (i + 1));
                                            }

                                        }
                                }else if(w == Bukkit.getWorld("moon")){
                                    int leaves = 0;
                                    for(int x = -3; x <= 3; x++){
                                        for(int z = -3; z <= 3; z++){
                                            for(int y = -3; y <=3; y++){
                                                Material material = a.getLocation().add(x,y,z).getBlock().getType();
                                                if(material == Material.OAK_LEAVES){
                                                    leaves++;
                                                }
                                            }
                                        }
                                    }
                                    if(!a.getCustomName().equals(ChatColor.LIGHT_PURPLE + "Oxygen Collector"))
                                        if(String.valueOf(data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollector"), PersistentDataType.INTEGER)).equals("100")) {
                                            float i = data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT);
                                            if (i >= 100) {
                                                a.setCustomName(ChatColor.RED + "100/100");
                                            } else {
                                                float o = (float) (0.1 * leaves);
                                                DecimalFormat df = new DecimalFormat("#.#");


                                                a.setCustomName(ChatColor.LIGHT_PURPLE + "" + (df.format(i + o)) + "/100");
                                                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT, i + o);
                                                System.out.println(o);

                                            }

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

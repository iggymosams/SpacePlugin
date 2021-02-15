package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Managers.Scoreboard;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

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
        }else{
            if (e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {

                if(e.getRightClicked().getPersistentDataContainer().has(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER)){

                    e.setCancelled(true);
                    ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
                    ItemMeta im = i.getItemMeta();
                    if(im.getDisplayName().equals(ChatColor.BLUE + "Spacesuit Helmet")){
                        PersistentDataContainer data = im.getPersistentDataContainer();
                        int max = data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenMax"), PersistentDataType.INTEGER);
                        int remain = data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.INTEGER);
                        int occurrent = e.getRightClicked().getPersistentDataContainer().get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER);
                        int needed = max - remain;
                        if(needed == 0){
                            e.getPlayer().sendMessage(ChatColor.RED + "Helmet is already full");
                        }
                        if (max > remain){
                            if(needed > occurrent){
                                int give = needed - occurrent;
                                give = give + remain;
                                if(give > occurrent){
                                    int test = give - occurrent;
                                    give = give -test;
                                    data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.INTEGER, (give+remain));
                                    im.setLore(Arrays.asList(ChatColor.WHITE + "" + (give+remain) + "/"+max));
                                    i.setItemMeta(im);
                                    e.getRightClicked().getPersistentDataContainer().set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER, occurrent-give);
                                }else{
                                    data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.INTEGER, (give+remain));
                                    im.setLore(Arrays.asList(ChatColor.WHITE + "" + (give+remain) + "/"+max));
                                    i.setItemMeta(im);
                                    e.getRightClicked().getPersistentDataContainer().set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER, occurrent-give);
                                }
                            }
                            else if(occurrent > needed){
                                int give = needed;
                                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.INTEGER, (give+remain));
                                im.setLore(Arrays.asList(ChatColor.WHITE + "" + (give+remain) + "/"+max));
                                i.setItemMeta(im);
                                e.getRightClicked().getPersistentDataContainer().set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER, occurrent-give);
                            }
                            else if(occurrent == needed){
                                int give = needed;
                                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.INTEGER, (give+remain));
                                im.setLore(Arrays.asList(ChatColor.WHITE + "" + (give+remain) + "/"+max));
                                i.setItemMeta(im);
                                e.getRightClicked().getPersistentDataContainer().set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER, occurrent-give);
                            }
                        }
                    }
                }
                else if(e.getRightClicked().getName().equals(ChatColor.LIGHT_PURPLE + "Oxygen Collector")){
                    e.setCancelled(true);
                }
            }
        }
    }
}

package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Managers.Scoreboard;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class InteractEntity implements Listener {

    Scoreboard sb = new Scoreboard();

    @EventHandler
    public void onEntityInteract(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().equals(Spaceplugin.getVoidworld())) {
            if (e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
                e.setCancelled(true);
                p.setWalkSpeed(0.2F);
                p.setGameMode(GameMode.SURVIVAL);
                if (e.getRightClicked().getName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Earth")) {
                    if(p.getBedSpawnLocation() != null){
                        p.sendMessage(String.valueOf(p.getBedSpawnLocation()));
                        p.teleport(p.getBedSpawnLocation());
                    }else{
                        p.teleport(new Location(Bukkit.getWorld("world"), 0, 80, 0));
                    }

                    p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                    p.removePotionEffect(PotionEffectType.JUMP);
                    p.setAllowFlight(false);
                    p.setGravity(true);
                    sb.UpdateScoreboard(p);
                } else if (e.getRightClicked().getName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "Moon")) {
                    p.teleport(new Location(Bukkit.getWorld("moon"), 0, 80, 0));
                    p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                    p.removePotionEffect(PotionEffectType.JUMP);
                    p.setAllowFlight(false);
                    p.setGravity(true);
                    sb.UpdateScoreboard(p);
                }
            }
        } else {
            if (e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {

                if (e.getRightClicked().getPersistentDataContainer().has(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT)) {

                    e.setCancelled(true);
                    ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
                    ItemMeta im = i.getItemMeta();
                    if (im.isUnbreakable()) {
                        PersistentDataContainer data = im.getPersistentDataContainer();
                        float max = data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenMax"), PersistentDataType.FLOAT);
                        float remain = data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.FLOAT);
                        float occurrent = e.getRightClicked().getPersistentDataContainer().get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT);
                        float needed = max - remain;
                        if (needed == 0) {
                            e.getPlayer().sendMessage(ChatColor.RED + "Helmet is already full");
                        }
                        if (max > remain) {
                            if (needed > occurrent) {
                                float give = needed - occurrent;
                                give = give + remain;
                                if (give > occurrent) {
                                    float test = give - occurrent;
                                    give = give - test;
                                    data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.FLOAT, (give + remain));
                                    im.setLore(Arrays.asList(ChatColor.WHITE + "" + (give + remain) + "/" + max));
                                    i.setItemMeta(im);
                                    e.getRightClicked().getPersistentDataContainer().set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT, occurrent - give);
                                } else {
                                    data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.FLOAT, (give + remain));
                                    im.setLore(Arrays.asList(ChatColor.WHITE + "" + (give + remain) + "/" + max));
                                    i.setItemMeta(im);
                                    e.getRightClicked().getPersistentDataContainer().set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT, occurrent - give);
                                }
                            } else if (occurrent > needed) {
                                float give = needed;
                                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.FLOAT, (give + remain));
                                im.setLore(Arrays.asList(ChatColor.WHITE + "" + (give + remain) + "/" + max));
                                i.setItemMeta(im);
                                e.getRightClicked().getPersistentDataContainer().set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT, occurrent - give);
                            } else if (occurrent == needed) {
                                float give = needed;
                                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.FLOAT, (give + remain));
                                im.setLore(Arrays.asList(ChatColor.WHITE + "" + (give + remain) + "/" + max));
                                i.setItemMeta(im);
                                e.getRightClicked().getPersistentDataContainer().set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT, occurrent - give);
                            }
                        }
                    }
                } else if (e.getRightClicked().getName().equals(ChatColor.LIGHT_PURPLE + "Oxygen Collector")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}

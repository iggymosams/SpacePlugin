package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Managers.Scoreboard;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GUIclickEvent implements Listener {

    Plugin plugin = Spaceplugin.getPlugin();

    LinkedHashMap<String, Integer> oxygen = Spaceplugin.getPlugin().getOxygen();

    ArrayList<Player> gravboots = Spaceplugin.getPlugin().gravityboots;

    ArrayList<ArmorStand> oxygenCo = Spaceplugin.getPlugin().oxygenCollectors;

    World moon = Spaceplugin.getMoon();

    Scoreboard sb = new Scoreboard();

    @EventHandler
    public void onEquip(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals("Equipment")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType().equals(Material.LEATHER_HELMET)) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spacesuit Helmet") && e.getClickedInventory().getSize() != 54) {
                        e.getInventory().setItem(10, e.getCurrentItem());
                        ItemStack item = e.getCurrentItem();
                        ItemMeta itemMeta = item.getItemMeta();
                        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
                        int current = data.get(new NamespacedKey(plugin, "oxygenRemaining"), PersistentDataType.INTEGER);
                        p.sendMessage(String.valueOf(current));
                        oxygen.put(p.getName(), current);
                        itemMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(itemMeta);
                        p.getInventory().setHelmet(item);
                        p.getInventory().remove(e.getCurrentItem());
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spacesuit Helmet") && e.getClickedInventory().getSize() == 54) {
                        ItemStack i = e.getCurrentItem();
                        ItemMeta im = i.getItemMeta();
                        im.removeEnchant(Enchantment.BINDING_CURSE);
                        PersistentDataContainer data = im.getPersistentDataContainer();
                        int remaining = oxygen.get(p.getName()).intValue();
                        data.set(new NamespacedKey(plugin, "oxygen"), PersistentDataType.INTEGER, remaining);
                        oxygen.remove(p.getName());
                        i.setItemMeta(im);
                        p.getInventory().addItem(i);
                        ItemStack emptyHelm = new ItemStack(Material.LEATHER_HELMET);
                        LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
                        emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
                        emptyHelmMeta.setColor(Color.BLACK);
                        emptyHelmMeta.removeEnchant(Enchantment.BINDING_CURSE);
                        emptyHelm.setItemMeta(emptyHelmMeta);
                        p.getInventory().setHelmet(null);
                        e.getInventory().setItem(10, emptyHelm);
                    }
                } else if (e.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE)) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spacesuit ChestPlate") && e.getClickedInventory().getSize() != 54) {
                        e.getInventory().setItem(19, e.getCurrentItem());
                        ItemStack item = e.getCurrentItem();
                        ItemMeta itemMeta = item.getItemMeta();
                        itemMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(itemMeta);
                        p.getInventory().setChestplate(item);
                        p.getInventory().remove(e.getCurrentItem());
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spacesuit ChestPlate") && e.getClickedInventory().getSize() == 54) {
                        ItemStack i = e.getCurrentItem();
                        ItemMeta im = i.getItemMeta();
                        im.removeEnchant(Enchantment.BINDING_CURSE);
                        i.setItemMeta(im);
                        p.getInventory().addItem(i);
                        ItemStack emptyHelm = new ItemStack(Material.LEATHER_CHESTPLATE);
                        LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
                        emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
                        emptyHelmMeta.setColor(Color.BLACK);
                        emptyHelmMeta.removeEnchant(Enchantment.BINDING_CURSE);
                        emptyHelm.setItemMeta(emptyHelmMeta);
                        p.getInventory().setChestplate(null);
                        e.getInventory().setItem(19, emptyHelm);
                    }
                } else if (e.getCurrentItem().getType().equals(Material.LEATHER_LEGGINGS)) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spacesuit Leggins") && e.getClickedInventory().getSize() != 54) {
                        e.getInventory().setItem(28, e.getCurrentItem());
                        ItemStack item = e.getCurrentItem();
                        ItemMeta itemMeta = item.getItemMeta();
                        itemMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(itemMeta);
                        p.getInventory().setLeggings(item);
                        p.getInventory().remove(e.getCurrentItem());
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spacesuit Leggins") && e.getClickedInventory().getSize() == 54) {
                        ItemStack i = e.getCurrentItem();
                        ItemMeta im = i.getItemMeta();
                        im.removeEnchant(Enchantment.BINDING_CURSE);
                        i.setItemMeta(im);
                        p.getInventory().addItem(i);
                        ItemStack emptyHelm = new ItemStack(Material.LEATHER_LEGGINGS);
                        LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
                        emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
                        emptyHelmMeta.setColor(Color.BLACK);
                        emptyHelmMeta.removeEnchant(Enchantment.BINDING_CURSE);
                        emptyHelm.setItemMeta(emptyHelmMeta);
                        p.getInventory().setLeggings(null);
                        e.getInventory().setItem(28, emptyHelm);
                    }
                } else if (e.getCurrentItem().getType().equals(Material.LEATHER_BOOTS)) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spacesuit Boots") && e.getClickedInventory().getSize() != 54) {
                        e.getInventory().setItem(37, e.getCurrentItem());
                        ItemStack item = e.getCurrentItem();
                        ItemMeta itemMeta = item.getItemMeta();
                        itemMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(itemMeta);
                        p.getInventory().setBoots(item);
                        p.getInventory().remove(e.getCurrentItem());
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spacesuit Boots") && e.getClickedInventory().getSize() == 54) {
                        ItemStack i = e.getCurrentItem();
                        ItemMeta im = i.getItemMeta();
                        im.removeEnchant(Enchantment.BINDING_CURSE);
                        i.setItemMeta(im);
                        p.getInventory().addItem(i);
                        ItemStack emptyHelm = new ItemStack(Material.LEATHER_BOOTS);
                        LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
                        emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
                        emptyHelmMeta.setColor(Color.BLACK);
                        emptyHelm.setItemMeta(emptyHelmMeta);
                        p.getInventory().setBoots(null);
                        e.getInventory().setItem(37, emptyHelm);
                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Gravity Boots") && e.getClickedInventory().getSize() != 54){
                        e.getInventory().setItem(37, e.getCurrentItem());
                        ItemStack item = e.getCurrentItem();
                        ItemMeta itemMeta = item.getItemMeta();
                        itemMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(itemMeta);
                        gravboots.add(p);
                        p.getInventory().setBoots(item);
                        p.getInventory().remove(e.getCurrentItem());
                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Gravity Boots") && e.getClickedInventory().getSize() == 54){
                        ItemStack i = e.getCurrentItem();
                        ItemMeta im = i.getItemMeta();
                        im.removeEnchant(Enchantment.BINDING_CURSE);
                        i.setItemMeta(im);
                        p.getInventory().addItem(i);
                        gravboots.remove(p);
                        ItemStack emptyHelm = new ItemStack(Material.LEATHER_BOOTS);
                        LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
                        emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
                        emptyHelmMeta.setColor(Color.BLACK);
                        emptyHelm.setItemMeta(emptyHelmMeta);
                        p.getInventory().setBoots(null);
                        e.getInventory().setItem(37, emptyHelm);
                    }
                }else{
                    return;
                }

            }

        }
        else if (e.getView().getTitle().equals("Where to?")){
            e.setCancelled(true);
            if(e.getSlot() == 2){
                p.teleport(new Location(Bukkit.getWorld("world"), 0, 80, 0));
                p.setAllowFlight(false);
                p.setGravity(true);
                sb.UpdateScoreboard(p);
            }
            if(e.getSlot() == 4){
                p.teleport(new Location(moon, 0, 80, 0));
                p.setAllowFlight(false);
                p.setGravity(true);
                sb.UpdateScoreboard(p);
            }
        }
        else if(e.getView().getTitle().equals("Oxygen Collector")){
            e.setCancelled(true);
            if(e.getSlot() == 26){

            }
        }
    }
}

package me.iggymosams.spaceplugin.Commands;

import me.iggymosams.spaceplugin.Spaceplugin;
import me.iggymosams.spaceplugin.api;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;

public class EquipCommand implements CommandExecutor {
    Inventory inv;

    Player p;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            p = (Player) sender;
            if(p.hasPermission(api.perm() + ".equip")) {
                inv = Bukkit.createInventory(null, 54, "Equipment");
                initializeItems();

                p.openInventory(inv);
            }else {
                api.noPermission(p);
            }
        }
        return true;
    }
    public void initializeItems(){
        if(p.getInventory().getHelmet() == null){
            ItemStack emptyHelm = new ItemStack(Material.LEATHER_HELMET);
            LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
            emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
            emptyHelmMeta.setColor(Color.BLACK);
            emptyHelm.setItemMeta(emptyHelmMeta);
            inv.setItem(10, emptyHelm);
        }else{
            if(p.getInventory().getHelmet().getType().equals(Material.LEATHER_HELMET)) {
                ItemStack setHelm = p.getInventory().getHelmet();
                inv.setItem(10, setHelm);
            }else{
                ItemStack emptyHelm = new ItemStack(Material.LEATHER_HELMET);
                LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
                emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
                emptyHelmMeta.setColor(Color.BLACK);
                emptyHelm.setItemMeta(emptyHelmMeta);
                inv.setItem(10, emptyHelm);
            }
        }


        if(p.getInventory().getChestplate() == null){
            ItemStack emptyChest = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta emptyChestMeta = (LeatherArmorMeta) emptyChest.getItemMeta();
            emptyChestMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
            emptyChestMeta.setColor(Color.BLACK);
            emptyChest.setItemMeta(emptyChestMeta);
            inv.setItem(19, emptyChest);
        }else{

            if(p.getInventory().getChestplate().getType().equals(Material.LEATHER_CHESTPLATE)) {
                inv.setItem(19, p.getInventory().getChestplate());

            }else{
                ItemStack emptyChest = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta emptyChestMeta = (LeatherArmorMeta) emptyChest.getItemMeta();
                emptyChestMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
                emptyChestMeta.setColor(Color.BLACK);
                emptyChest.setItemMeta(emptyChestMeta);
                inv.setItem(19, emptyChest);
            }
        }

        if(p.getInventory().getLeggings() == null){
            ItemStack emptyHelm = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
            emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
            emptyHelmMeta.setColor(Color.BLACK);
            emptyHelm.setItemMeta(emptyHelmMeta);
            inv.setItem(28, emptyHelm);
        }else{
            if(p.getInventory().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)) {
                ItemStack setHelm = p.getInventory().getLeggings();
                inv.setItem(28, setHelm);
            }else{
                ItemStack emptyHelm = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
                emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
                emptyHelmMeta.setColor(Color.BLACK);
                emptyHelm.setItemMeta(emptyHelmMeta);
                inv.setItem(28, emptyHelm);
            }
        }

        if(p.getInventory().getBoots() == null){
            ItemStack emptyHelm = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
            emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
            emptyHelmMeta.setColor(Color.BLACK);
            emptyHelm.setItemMeta(emptyHelmMeta);
            inv.setItem(37, emptyHelm);
        }else{
            if(p.getInventory().getBoots().getType().equals(Material.LEATHER_BOOTS)) {
                ItemStack setHelm = p.getInventory().getBoots();
                inv.setItem(37, setHelm);
            }else{
                ItemStack emptyHelm = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta emptyHelmMeta = (LeatherArmorMeta) emptyHelm.getItemMeta();
                emptyHelmMeta.setDisplayName(ChatColor.DARK_GRAY + "Empty");
                emptyHelmMeta.setColor(Color.BLACK);
                emptyHelm.setItemMeta(emptyHelmMeta);
                inv.setItem(37, emptyHelm);
            }
        }
        ItemStack rocket = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta rocketm = rocket.getItemMeta();
        rocketm.setDisplayName("Rocket");
        rocket.setItemMeta(rocketm);
        inv.setItem(0, rocket);

    }
    Plugin plugin = Spaceplugin.getPlugin();
}

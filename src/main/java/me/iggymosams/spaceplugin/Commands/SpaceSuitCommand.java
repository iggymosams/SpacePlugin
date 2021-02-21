package me.iggymosams.spaceplugin.Commands;

import me.iggymosams.spaceplugin.Spaceplugin;
import me.iggymosams.spaceplugin.api;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class SpaceSuitCommand implements CommandExecutor {

    Plugin plugin = Spaceplugin.getPlugin();

    Integer oxygen = 30;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(api.perm() + ".suit")) {
                Inventory i = p.getInventory();

                ItemStack test = new ItemStack(Material.HONEYCOMB_BLOCK);
                ItemMeta testMeta = test.getItemMeta();
                testMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Oxygen Collector");
                test.setItemMeta(testMeta);
                i.addItem(test);

                ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
                LeatherArmorMeta helmItemMeta = (LeatherArmorMeta) helm.getItemMeta();
                PersistentDataContainer data = helmItemMeta.getPersistentDataContainer();
                data.set(new NamespacedKey(plugin, "oxygenRemaining"), PersistentDataType.FLOAT, 0F);
                data.set(new NamespacedKey(plugin, "oxygenMax"), PersistentDataType.FLOAT, 200F);
                helmItemMeta.setLore(Arrays.asList(ChatColor.WHITE + "0/200"));
                helmItemMeta.setDisplayName(ChatColor.BLUE + "Spacesuit Helmet");
                helmItemMeta.addEnchant(Enchantment.LUCK, 1, true);
                helmItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                helmItemMeta.setUnbreakable(true);
                helmItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                helmItemMeta.setColor(Color.WHITE);
                helm.setItemMeta(helmItemMeta);

                ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta chestItemMeta = (LeatherArmorMeta) chest.getItemMeta();
                chestItemMeta.setDisplayName(ChatColor.BLUE + "Spacesuit ChestPlate");
                chestItemMeta.addEnchant(Enchantment.LUCK, 1, true);
                chestItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                chestItemMeta.setColor(Color.WHITE);
                chestItemMeta.setUnbreakable(true);
                chestItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                chest.setItemMeta(chestItemMeta);

                ItemStack leggs = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta leggsItemMeta = (LeatherArmorMeta) leggs.getItemMeta();
                leggsItemMeta.setDisplayName(ChatColor.BLUE + "Spacesuit Leggins");
                leggsItemMeta.setColor(Color.WHITE);
                leggsItemMeta.addEnchant(Enchantment.LUCK, 1, true);
                leggsItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                leggsItemMeta.setUnbreakable(true);
                leggsItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                leggs.setItemMeta(leggsItemMeta);

                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta bootsItemMeta = (LeatherArmorMeta) boots.getItemMeta();
                bootsItemMeta.setDisplayName(ChatColor.BLUE + "Spacesuit Boots");
                bootsItemMeta.setColor(Color.WHITE);
                bootsItemMeta.addEnchant(Enchantment.LUCK, 1, true);
                bootsItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                bootsItemMeta.setUnbreakable(true);
                bootsItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                boots.setItemMeta(bootsItemMeta);

                ItemStack gravboots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta gravbootsItemMeta = (LeatherArmorMeta) gravboots.getItemMeta();
                gravbootsItemMeta.setDisplayName(ChatColor.BLUE + "Gravity Boots");
                gravbootsItemMeta.setColor(Color.GRAY);
                gravbootsItemMeta.addEnchant(Enchantment.LUCK, 1, true);
                gravbootsItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                gravbootsItemMeta.setUnbreakable(true);
                gravbootsItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                gravboots.setItemMeta(gravbootsItemMeta);
                p.sendMessage(String.valueOf(p.getWalkSpeed()));
                i.addItem(helm, chest, leggs, boots, gravboots);
            }else {
                api.noPermission(p);
            }
        }
        return true;
    }
}

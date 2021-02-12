package me.iggymosams.spaceplugin.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;

import static org.bukkit.GameRule.DO_MOB_SPAWNING;

public class SetupVoid implements CommandExecutor {
    ArmorStand earthas;
    ArmorStand moonas;
    Pig pig;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        World w = p.getWorld();
        w.setGameRule(DO_MOB_SPAWNING, false);
        ItemStack earth = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta earthMeta = (SkullMeta) earth.getItemMeta();
        earthMeta.setOwningPlayer(Bukkit.getOfflinePlayer("earth"));
        earthMeta.setDisplayName(ChatColor.GREEN + "Earth");
        earth.setItemMeta(earthMeta);

        ItemStack moon = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta moonMeta = (SkullMeta) moon.getItemMeta();
        moonMeta.setOwningPlayer(Bukkit.getOfflinePlayer("gng2546atc"));
        moonMeta.setDisplayName(ChatColor.GRAY + "Moon");
        moon.setItemMeta(moonMeta);


        earthas = (ArmorStand) w.spawn(new Location(w, 5.5, 80, 3.5), ArmorStand.class);
        //earthas.setGravity(false);
        earthas.setVisible(false);
        earthas.setRotation(145, 0);
        earthas.setCustomNameVisible(true);
        earthas.setCustomName(ChatColor.GREEN + "" +ChatColor.BOLD + "Earth");
        earthas.getEquipment().setHelmet(earth);

        moonas = (ArmorStand) w.spawn(new Location(w, 1.5, 80, 3.5), ArmorStand.class);
        //moonas.setGravity(false);
        moonas.setVisible(false);
        moonas.setRotation(-145, 0);
        moonas.setCustomNameVisible(true);
        moonas.setCustomName(ChatColor.GRAY + "" +ChatColor.BOLD + "Moon");
        moonas.getEquipment().setHelmet(moon);


        return true;
    }
}

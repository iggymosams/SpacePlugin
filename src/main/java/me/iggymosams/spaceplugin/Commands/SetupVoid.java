package me.iggymosams.spaceplugin.Commands;

import me.iggymosams.spaceplugin.api;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static org.bukkit.GameRule.DO_MOB_SPAWNING;

public class SetupVoid implements CommandExecutor {
    ArmorStand as;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(api.perm() + ".setupvoid")) {
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

                GenerateArmorStand(new Location(w, 5.5, 80, 2.5), "&a&lEarth", false, false, true, true, earth);
                GenerateArmorStand(new Location(w, 1.5, 80, 2.5), "&7&lMoon", false, false, true, true, moon);

                w.getBlockAt(new Location(w, 0, 255, 0)).setType(Material.END_GATEWAY);
            } else {
                api.noPermission(p);
            }
        }
        return true;
    }

    public void GenerateArmorStand(Location loc, String name, boolean visible, boolean small, boolean gravity, boolean NameVisible, ItemStack item) {
        as = loc.getWorld().spawn(loc, ArmorStand.class);
        as.setVisible(visible);
        as.setSmall(small);
        as.setGravity(gravity);
        as.setCustomNameVisible(NameVisible);
        as.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        if (item != null) {
            as.getEquipment().setHelmet(item);
        }
    }
}

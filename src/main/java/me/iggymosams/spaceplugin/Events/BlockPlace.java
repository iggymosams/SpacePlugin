package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BlockPlace implements Listener {

    ArmorStand as;

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(e.getBlock().getType().equals(Material.HONEYCOMB_BLOCK)){

            if(e.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Oxygen Collector")){
                e.setCancelled(true);
                GenerateArmorStand(new Location(e.getBlock().getWorld(), e.getBlock().getLocation().getX() + 0.5F, e.getBlock().getLocation().getY() - 0.5F, e.getBlock().getLocation().getZ() + 0.5F), "&dOxygen Collector", false,true,false, true);
                ArmorStand oc1;
                GenerateArmorStand(new Location(e.getBlock().getWorld(), e.getBlock().getLocation().getX() + 0.5F, e.getBlock().getLocation().getY() - 1.2F, e.getBlock().getLocation().getZ() + 0.5F), "&dnull/null", false,true,false, true);
                //oc1 = loc.getWorld().spawn(new Location(loc.getWorld(), loc.getX() + 0.5F, loc.getY() - 1.2F, loc.getZ() + 0.5F), ArmorStand.class);
                //oc1.setVisible(false);
                //oc1.getEquipment().setHelmet(new ItemStack(Material.SPONGE));
                PersistentDataContainer data = as.getPersistentDataContainer();
                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollector"), PersistentDataType.INTEGER, 100);
                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT, 0F);
                as.setCustomName(ChatColor.LIGHT_PURPLE + String.valueOf(data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.FLOAT)) + "/" + data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollector"), PersistentDataType.INTEGER));
                e.getPlayer().getInventory().removeItem(e.getItemInHand());
            }
        }
        if(e.getBlock().getType().equals(Material.COMPARATOR)) {
            if (e.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Advance Component")) {
                e.setCancelled(true);
            }
        }
    }
    public void GenerateArmorStand(Location loc, String name, boolean visible, boolean small, boolean gravity, boolean NameVisible){
        as = loc.getWorld().spawn(loc, ArmorStand.class);
        as.setVisible(visible);
        as.setSmall(small);
        as.setGravity(gravity);
        as.setCustomNameVisible(NameVisible);
        as.setCustomName(ChatColor.translateAlternateColorCodes('&',name));
    }
}

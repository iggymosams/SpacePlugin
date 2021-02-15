package me.iggymosams.spaceplugin.Events;

import me.iggymosams.spaceplugin.Managers.OxygenCollecter;
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

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(e.getBlock().getType().equals(Material.HONEYCOMB_BLOCK)){

            if(e.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Oxygen Collector")){

                e.setCancelled(true);
                ArmorStand oc;
                ArmorStand oc1;
                Location loc = e.getBlock().getLocation();
                oc = (ArmorStand) loc.getWorld().spawn(new Location(loc.getWorld(), loc.getX()+0.5F, loc.getY() - 0.05F, loc.getZ() + 0.5F), ArmorStand.class);
                oc.setVisible(false);
                oc.setSmall(true);
                oc.setGravity(false);
                oc.setCustomNameVisible(true);
                PersistentDataContainer data1 = oc.getPersistentDataContainer();
                oc.setCustomName(ChatColor.LIGHT_PURPLE + "Oxygen Collector");
                oc1 = (ArmorStand) loc.getWorld().spawn(new Location(loc.getWorld(), loc.getX() + 0.5F, loc.getY() - 1.2F, loc.getZ() + 0.5F), ArmorStand.class);
                oc1.setVisible(false);
                oc1.getEquipment().setHelmet(new ItemStack(Material.HONEYCOMB_BLOCK));
                PersistentDataContainer data = oc1.getPersistentDataContainer();
                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollector"), PersistentDataType.INTEGER, 100);
                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER, 0);
                oc1.setCanPickupItems(false);
                oc1.setCustomNameVisible(true);
                oc1.setCustomName(ChatColor.LIGHT_PURPLE + String.valueOf(data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollectorCurrent"), PersistentDataType.INTEGER)) + "/" + data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenCollector"), PersistentDataType.INTEGER));
                oc1.setGravity(false);
                e.getPlayer().getInventory().removeItem(e.getItemInHand());
            }
        }
    }
}

package me.iggymosams.spaceplugin.Events;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class EquipOxygenMask implements Listener {

    int oxygen;

    int taskID;

    Plugin plugin = Spaceplugin.getPlugin();

    @EventHandler
    public void onEquip(ArmorEquipEvent e) {
        Player p = e.getPlayer();
        if (e.getNewArmorPiece() != null) {
            if (e.getNewArmorPiece().getType() == Material.LEATHER_HELMET || e.getNewArmorPiece().getType() == Material.LEATHER_CHESTPLATE || e.getNewArmorPiece().getType() == Material.LEATHER_LEGGINGS || e.getNewArmorPiece().getType() == Material.LEATHER_BOOTS) {
                if (e.getNewArmorPiece().getItemMeta().isUnbreakable() == true) {
                    e.setCancelled(true);
                    p.sendMessage("To equip this suit use /equip");
                }
            }
        }
    }
}

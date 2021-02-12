package me.iggymosams.spaceplugin.Commands;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.List;

public class VoidTPCommand implements CommandExecutor {

    World voidworld = Spaceplugin.getVoidworld();
    Plugin plugin = Spaceplugin.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            p.setGameMode(GameMode.ADVENTURE);
            p.teleport(new Location(voidworld, 3.5, 80, 0.5));
            World w = p.getWorld();
            w.setDifficulty(Difficulty.PEACEFUL);
            p.setWalkSpeed(0.2F);
        }
        return true;
    }
    private boolean getLookingAt(Player player, ArmorStand as)
    {
        Location eye = player.getEyeLocation();
        Vector toEntity = as.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());

        return dot > 0.99D;
    }
}

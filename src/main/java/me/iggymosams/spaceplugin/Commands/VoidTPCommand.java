package me.iggymosams.spaceplugin.Commands;

import me.iggymosams.spaceplugin.Spaceplugin;
import me.iggymosams.spaceplugin.api;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class VoidTPCommand implements CommandExecutor {

    World voidworld = Spaceplugin.getVoidworld();

    Plugin plugin = Spaceplugin.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(api.perm() + ".void")) {
                p.setGameMode(GameMode.ADVENTURE);
                p.teleport(new Location(voidworld, 3.5, 80, 0.5));
                World w = p.getWorld();
                w.setDifficulty(Difficulty.PEACEFUL);
                p.setWalkSpeed(0f);
            } else {
                api.noPermission(p);
            }
        }
        return true;
    }
}

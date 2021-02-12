package me.iggymosams.spaceplugin.Commands;

import me.iggymosams.spaceplugin.Managers.Scoreboard;
import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import javax.naming.Name;
import java.util.Arrays;

public class TeleportToDimensionCommand implements CommandExecutor {

    World dimension;


    Plugin plugin = Spaceplugin.getPlugin(Spaceplugin.class);
    Scoreboard sb = new Scoreboard();
    public TeleportToDimensionCommand(World dimension){
        this.dimension = dimension;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            sb.UpdateScoreboard(player);
            player.setGameMode(GameMode.CREATIVE);
            player.teleport(new Location(dimension, 0, 80, 0));

            player.setGravity(true);

        }
        return true;
    }
}

package me.iggymosams.spaceplugin;

import me.iggymosams.spaceplugin.Managers.MessagesConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class api {
    public static String perm(){
        return Spaceplugin.permissionmsg;
    }
    public static void noPermission(Player p){
        p.sendMessage(api.getMessage("noPermission"));
    }
    public static String getMessage(String path){
        return ChatColor.translateAlternateColorCodes('&', MessagesConfig.get().getString(path));
    }
}

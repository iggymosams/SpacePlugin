package me.iggymosams.spaceplugin.Managers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.time.YearMonth;

public class MessagesConfig {
    private static File file;
    private static FileConfiguration messagesFile;

    //Finds or Generates the messages.yml
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Spaceplugin").getDataFolder(), "messages.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                //Error Creating File
            }
        }
        messagesFile = YamlConfiguration.loadConfiguration(file);

    }
    public static FileConfiguration get(){
        return  messagesFile;
    }

    public static void save(){
        try{
            messagesFile.save(file);
        }catch (IOException e){
            //Error Saving File
        }
    }

    public static void reload(){
        messagesFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void loadDefaults(){
        MessagesConfig.get().addDefault("noPermission", "&cYou dont have permission");
    }
}


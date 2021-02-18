package me.iggymosams.spaceplugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.iggymosams.spaceplugin.Commands.*;
import me.iggymosams.spaceplugin.Events.*;
import me.iggymosams.spaceplugin.Managers.*;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class Spaceplugin<permissionmsg> extends JavaPlugin {

    private static ProtocolManager protocolManager;

    private static Spaceplugin plugin;

    private static World moon;

    private static World voidworld;

    final LinkedHashMap<String, Integer> oxygen = new LinkedHashMap<>();

    public ArrayList<Player> gravityboots = new ArrayList<>();

    public ArrayList<ArmorStand> oxygenCollectors = new ArrayList<>();

    public static String permissionmsg = "spaceplugin";

    @Override
    public void onEnable() {


        plugin = this;
//        this.getConfig().options().copyDefaults();
//        saveDefaultConfig();

        protocolManager = ProtocolLibrary.getProtocolManager();

        StartManagers();
        RegisterCommands();
        GenerateWorlds();
        RegisterEvents();
        LoadMessages();
    }


    @Override
    public void onDisable() {
    }
    public void LoadMessages(){
        MessagesConfig.setup();
        MessagesConfig.loadDefaults();
        MessagesConfig.get().options().copyDefaults(true);
        MessagesConfig.save();
    }
    public void StartManagers(){
        Oxygen oxygenMan = new Oxygen();
        oxygenMan.CheckOxygen();

        OxygenCollecter oxygenCollecter = new OxygenCollecter();
        oxygenCollecter.OxygenCollecter();
    }
    public void GenerateWorlds(){
        WorldCreator moonCreator = new WorldCreator("moon");
        moonCreator.generator(new MoonGenerator(moonCreator.seed()));
        moon = moonCreator.createWorld();

        WorldCreator solarSystem = new WorldCreator("void");
        solarSystem.environment(World.Environment.THE_END);
        solarSystem.generator(new EmptyWorldGenerator());
        voidworld = solarSystem.createWorld();
    }
    public void RegisterCommands(){
        getCommand("dimension").setExecutor(new TeleportToDimensionCommand(moon));
        getCommand("equip").setExecutor(new EquipCommand());
        getCommand("suit").setExecutor(new SpaceSuitCommand());
        getCommand("void").setExecutor(new VoidTPCommand());
        getCommand("setupvoid").setExecutor(new SetupVoid());
        getCommand("admin").setExecutor(new AdminCommand());
    }
    public void RegisterEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new RocketSpawnEvent(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new QuitEvent(), this);
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new EquipOxygenMask(), this);
        pm.registerEvents(new GUIclickEvent(), this);
        pm.registerEvents(new InteractEntity(), this);
        pm.registerEvents(new DeathEvent(), this);
        pm.registerEvents(new BlockPlace(), this);
    }

    public LinkedHashMap<String, Integer> getOxygen() {
        return oxygen;
    }

    public static World getVoidworld() {
        return voidworld;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public static World getMoon() {
        return moon;
    }

    public static Spaceplugin getPlugin() {
        return plugin;
    }

}

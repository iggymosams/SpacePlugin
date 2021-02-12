package me.iggymosams.spaceplugin;

import me.iggymosams.spaceplugin.Commands.*;
import me.iggymosams.spaceplugin.Events.*;
import me.iggymosams.spaceplugin.Managers.EmptyWorldGenerator;
import me.iggymosams.spaceplugin.Managers.MoonGenerator;
import me.iggymosams.spaceplugin.Managers.Oxygen;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class Spaceplugin extends JavaPlugin {

    private static Spaceplugin plugin;
    private static World moon;
    private static World voidworld;

    final LinkedHashMap<String, Integer> oxygen = new LinkedHashMap<>();

    public ArrayList<Player> getGravityboots() {
        return gravityboots;
    }

    public ArrayList<Player> gravityboots = new ArrayList<>();

    public LinkedHashMap<String, Integer> getOxygen() {
        return oxygen;
    }


    public static World getVoidworld() {
        return voidworld;
    }

    @Override
    public void onEnable() {

        plugin = this;
        // Plugin startup logic
        WorldCreator moonCreator = new WorldCreator("moon");
        moonCreator.generator(new MoonGenerator(moonCreator.seed()));
        moon = moonCreator.createWorld();

        WorldCreator solarSystem = new WorldCreator("void");
        solarSystem.environment(World.Environment.THE_END);

        solarSystem.generator(new EmptyWorldGenerator());

        voidworld = solarSystem.createWorld();

        this.getCommand("dimension").setExecutor(new TeleportToDimensionCommand(moon));
        this.getCommand("equip").setExecutor(new EquipCommand());
        this.getCommand("suit").setExecutor(new SpaceSuitCommand());
        this.getCommand("void").setExecutor(new VoidTPCommand());
        this.getCommand("setup").setExecutor(new SetupVoid());

        getServer().getPluginManager().registerEvents(new RocketSpawnEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new EquipOxygenMask(), this);
        getServer().getPluginManager().registerEvents(new GUIclickEvent(), this);
        getServer().getPluginManager().registerEvents(new InteractEntity(), this);
        Oxygen oxygenMan = new Oxygen();
        oxygenMan.CheckOxygen();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static World getMoon() {
        return moon;
    }

    public static Spaceplugin getPlugin() {
        return plugin;
    }

}

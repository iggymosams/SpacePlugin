package me.iggymosams.spaceplugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.iggymosams.spaceplugin.Commands.*;
import me.iggymosams.spaceplugin.Events.*;
import me.iggymosams.spaceplugin.Managers.*;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java
        .JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public final class Spaceplugin extends JavaPlugin {

    private static ProtocolManager protocolManager;
    private static Spaceplugin plugin;
    private static World moon;
    private static World voidworld;
    final LinkedHashMap<String, Float> oxygen = new LinkedHashMap<>();
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
        CreateRecipies();

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
        pm.registerEvents(new ArrowHitEvent(), this);
    }
    private void CreateRecipies() {
        Bukkit.addRecipe(AdvanceRedstone());
        Bukkit.addRecipe(RocketEngine());
        Bukkit.addRecipe(SpaceSuitBoots());
        Bukkit.addRecipe(SpaceSuitLeggs());
        Bukkit.addRecipe(SpaceSuitChest());
        Bukkit.addRecipe(Tier1OxygenMask());
        Bukkit.addRecipe(Tier2OxygenMask());
        Bukkit.addRecipe(Tier3OxygenMask());
        Bukkit.addRecipe(Tier1SpaceSuitHelmet());
        Bukkit.addRecipe(Tier2SpaceSuitHelmet());
        Bukkit.addRecipe(Tier3SpaceSuitHelmet());
        Bukkit.addRecipe(Rocket());
    }

    private ShapelessRecipe AdvanceRedstone() {
        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);
        NamespacedKey advredkey = new NamespacedKey(this, "advance_redstone");
        ShapelessRecipe recipe = new ShapelessRecipe (advredkey, advanceRedstone);
        recipe.addIngredient(1, Material.REDSTONE);
        recipe.addIngredient(1, Material.COMPARATOR);
        recipe.addIngredient(1, Material.REPEATER);
        return recipe;
    }
    public ShapedRecipe RocketEngine(){
        ItemStack engine = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta engineMeta = engine.getItemMeta();
        engineMeta.setDisplayName(ChatColor.RED + "Rocket Engine");
        engineMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        engineMeta.addEnchant(Enchantment.LUCK, 1, true);
        engine.setItemMeta(engineMeta);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "rocket_engine"), engine);
        recipe.shape(" I ","ICI");
        recipe.setIngredient('I',Material.IRON_INGOT);
        recipe.setIngredient('C',Material.FIRE_CHARGE);
        return recipe;
    }
    public ShapedRecipe SpaceSuitBoots(){
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsItemMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsItemMeta.setDisplayName(ChatColor.BLUE + "Spacesuit Boots");
        bootsItemMeta.setColor(Color.WHITE);
        bootsItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        bootsItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bootsItemMeta.setUnbreakable(true);
        bootsItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        boots.setItemMeta(bootsItemMeta);

        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "space_boots"), boots);
        recipe.shape("A A", "A A");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(advanceRedstone));
        return recipe;
    }
    public ShapedRecipe SpaceSuitLeggs(){
        ItemStack leggs = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggsItemMeta = (LeatherArmorMeta) leggs.getItemMeta();
        leggsItemMeta.setDisplayName(ChatColor.BLUE + "Spacesuit Leggins");
        leggsItemMeta.setColor(Color.WHITE);
        leggsItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        leggsItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        leggsItemMeta.setUnbreakable(true);
        leggsItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        leggs.setItemMeta(leggsItemMeta);

        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "space_leggings"), leggs);
        recipe.shape("AAA", "A A","A A");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(advanceRedstone));
        return recipe;
    }
    public ShapedRecipe SpaceSuitChest(){
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestItemMeta = (LeatherArmorMeta) chest.getItemMeta();
        chestItemMeta.setDisplayName(ChatColor.BLUE + "Spacesuit ChestPlate");
        chestItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        chestItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        chestItemMeta.setColor(Color.WHITE);
        chestItemMeta.setUnbreakable(true);
        chestItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        chest.setItemMeta(chestItemMeta);

        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "space_chestplate"), chest);
        recipe.shape("A A", "AAA","AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(advanceRedstone));
        return recipe;
    }
    public ShapedRecipe Tier1OxygenMask(){
        ItemStack Tier1 = new ItemStack(Material.LIME_STAINED_GLASS);
        ItemMeta Tier1Meta = Tier1.getItemMeta();
        Tier1Meta.setDisplayName(ChatColor.RED + "Tier 1 Oxygen Mask");
        Tier1Meta.addEnchant(Enchantment.LUCK, 1, true);
        Tier1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Tier1.setItemMeta(Tier1Meta);

        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "oxygen_mask_1"), Tier1);
        recipe.shape("III", "IGI","IRI");
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(advanceRedstone));
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('G', Material.GLASS);
        return recipe;
    }
    public ShapedRecipe Tier2OxygenMask(){
        ItemStack Tier2 = new ItemStack(Material.ORANGE_STAINED_GLASS);
        ItemMeta Tier2Meta = Tier2.getItemMeta();
        Tier2Meta.setDisplayName(ChatColor.RED + "Tier 2 Oxygen Mask");
        Tier2Meta.addEnchant(Enchantment.LUCK, 1, true);
        Tier2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Tier2.setItemMeta(Tier2Meta);

        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "oxygen_mask_2"), Tier2);
        recipe.shape("III", "IGI","IRI");
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(advanceRedstone));
        recipe.setIngredient('I', Material.DIAMOND);
        recipe.setIngredient('G', Material.GLASS);
        return recipe;
    }
    public ShapedRecipe Tier3OxygenMask(){
        ItemStack Tier3 = new ItemStack(Material.RED_STAINED_GLASS);
        ItemMeta Tier3Meta = Tier3.getItemMeta();
        Tier3Meta.setDisplayName(ChatColor.RED + "Tier 3 Oxygen Mask");
        Tier3Meta.addEnchant(Enchantment.LUCK, 1, true);
        Tier3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Tier3.setItemMeta(Tier3Meta);

        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "oxygen_mask_3"), Tier3);
        recipe.shape("III", "IGI","IRI");
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(advanceRedstone));
        recipe.setIngredient('I', Material.NETHERITE_INGOT);
        recipe.setIngredient('G', Material.GLASS);
        return recipe;
    }
    public ShapedRecipe Tier1SpaceSuitHelmet(){
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmItemMeta = (LeatherArmorMeta) helm.getItemMeta();
        PersistentDataContainer data = helmItemMeta.getPersistentDataContainer();
        data.set(new NamespacedKey(plugin, "oxygenRemaining"), PersistentDataType.FLOAT, 0F);
        data.set(new NamespacedKey(plugin, "oxygenMax"), PersistentDataType.FLOAT, 300F);
        helmItemMeta.setLore(Arrays.asList(ChatColor.WHITE + "0/300"));
        helmItemMeta.setDisplayName(ChatColor.BLUE + "Tier 1 Spacesuit Helmet");
        helmItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        helmItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        helmItemMeta.setUnbreakable(true);
        helmItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        helmItemMeta.setColor(Color.WHITE);
        helm.setItemMeta(helmItemMeta);

        ItemStack Tier1 = new ItemStack(Material.LIME_STAINED_GLASS);
        ItemMeta Tier1Meta = Tier1.getItemMeta();
        Tier1Meta.setDisplayName(ChatColor.RED + "Tier 1 Oxygen Mask");
        Tier1Meta.addEnchant(Enchantment.LUCK, 1, true);
        Tier1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Tier1.setItemMeta(Tier1Meta);


        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "space_helmet_1"), helm);
        recipe.shape("III", "IGI");
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(advanceRedstone));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(Tier1));
        return recipe;
    }
    public ShapedRecipe Tier2SpaceSuitHelmet(){
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmItemMeta = (LeatherArmorMeta) helm.getItemMeta();
        PersistentDataContainer data = helmItemMeta.getPersistentDataContainer();
        data.set(new NamespacedKey(plugin, "oxygenRemaining"), PersistentDataType.FLOAT, 0F);
        data.set(new NamespacedKey(plugin, "oxygenMax"), PersistentDataType.FLOAT, 600F);
        helmItemMeta.setLore(Arrays.asList(ChatColor.WHITE + "0/600"));
        helmItemMeta.setDisplayName(ChatColor.BLUE + "Tier 2 Spacesuit Helmet");
        helmItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        helmItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        helmItemMeta.setUnbreakable(true);
        helmItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        helmItemMeta.setColor(Color.WHITE);
        helm.setItemMeta(helmItemMeta);

        ItemStack Tier2 = new ItemStack(Material.ORANGE_STAINED_GLASS);
        ItemMeta Tier2Meta = Tier2.getItemMeta();
        Tier2Meta.setDisplayName(ChatColor.RED + "Tier 2 Oxygen Mask");
        Tier2Meta.addEnchant(Enchantment.LUCK, 1, true);
        Tier2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Tier2.setItemMeta(Tier2Meta);


        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "space_helmet_2"), helm);
        recipe.shape("III", "IGI");
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(advanceRedstone));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(Tier2));
        return recipe;
    }
    public ShapedRecipe Tier3SpaceSuitHelmet(){
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmItemMeta = (LeatherArmorMeta) helm.getItemMeta();
        PersistentDataContainer data = helmItemMeta.getPersistentDataContainer();
        data.set(new NamespacedKey(plugin, "oxygenRemaining"), PersistentDataType.FLOAT, 0F);
        data.set(new NamespacedKey(plugin, "oxygenMax"), PersistentDataType.FLOAT, 1200F);
        helmItemMeta.setLore(Arrays.asList(ChatColor.WHITE + "0/1200"));
        helmItemMeta.setDisplayName(ChatColor.BLUE + "Tier 3 Spacesuit Helmet");
        helmItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        helmItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        helmItemMeta.setUnbreakable(true);
        helmItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        helmItemMeta.setColor(Color.WHITE);
        helm.setItemMeta(helmItemMeta);

        ItemStack Tier3 = new ItemStack(Material.RED_STAINED_GLASS);
        ItemMeta Tier3Meta = Tier3.getItemMeta();
        Tier3Meta.setDisplayName(ChatColor.RED + "Tier 3 Oxygen Mask");
        Tier3Meta.addEnchant(Enchantment.LUCK, 1, true);
        Tier3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Tier3.setItemMeta(Tier3Meta);


        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "space_helmet_3"), helm);
        recipe.shape("III", "IGI");
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(advanceRedstone));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(Tier3));
        return recipe;
    }
    public ShapedRecipe Rocket(){
        ItemStack Rocket = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta RocketMeta = Rocket.getItemMeta();
        RocketMeta.setDisplayName(ChatColor.RED + "Rocket");
        RocketMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        RocketMeta.addEnchant(Enchantment.LUCK, 1, true);
        Rocket.setItemMeta(RocketMeta);

        ItemStack advanceRedstone = new ItemStack(Material.COMPARATOR);
        ItemMeta advanceRedstoneMeta = advanceRedstone.getItemMeta();
        advanceRedstoneMeta.setDisplayName(ChatColor.RED + "Advance Component");
        advanceRedstoneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        advanceRedstoneMeta.addEnchant(Enchantment.LUCK, 1, true);
        advanceRedstone.setItemMeta(advanceRedstoneMeta);

        ItemStack engine = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta engineMeta = engine.getItemMeta();
        engineMeta.setDisplayName(ChatColor.RED + "Rocket Engine");
        engineMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        engineMeta.addEnchant(Enchantment.LUCK, 1, true);
        engine.setItemMeta(engineMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "rocket"), Rocket);
        recipe.shape(" R ", "RRR", "RER");
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(advanceRedstone));
        recipe.setIngredient('E', new RecipeChoice.ExactChoice(engine));
        return recipe;
    }

    public LinkedHashMap<String, Float> getOxygen() {
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

package me.iggymosams.spaceplugin.Commands;

import me.iggymosams.spaceplugin.Managers.Scoreboard;
import me.iggymosams.spaceplugin.Spaceplugin;
import me.iggymosams.spaceplugin.api;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class AdminCommand implements CommandExecutor {

    Scoreboard sb = new Scoreboard();

    LinkedHashMap<String, Float> oxygen = Spaceplugin.getPlugin().getOxygen();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(api.perm() + ".admin")) {
                p.sendMessage(String.valueOf(args.length));
                if (args.length == 0) {
                    p.sendMessage("/admin tp <planet>");

                } else {
                    if (args[0].equalsIgnoreCase("tp")) {
                        //teleportcommand
                        if(p.hasPermission(api.perm() + "admin.tp")) {
                            if (args[1].equalsIgnoreCase("earth")) {
                                if (args.length == 2) {
                                    //Teleport Sender
                                    p.teleport(new Location(Bukkit.getWorld("world"), 0, 80, 0));
                                    p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                                    p.removePotionEffect(PotionEffectType.JUMP);
                                    p.setAllowFlight(false);
                                    p.setGravity(true);
                                } else {
                                    Player target = Bukkit.getPlayerExact(args[2]);
                                    if (target == null) {
                                        p.sendMessage(args[2] + " is not online");
                                    } else {
                                        target.teleport(new Location(Bukkit.getWorld("world"), 0, 80, 0));
                                        target.removePotionEffect(PotionEffectType.SLOW_FALLING);
                                        target.removePotionEffect(PotionEffectType.JUMP);
                                        target.setAllowFlight(false);
                                        target.setGravity(true);
                                    }
                                }
                            } else if (args[1].equalsIgnoreCase("moon")) {
                                if (args.length == 2) {
                                    //Teleport Sender
                                    p.teleport(new Location(Bukkit.getWorld("moon"), 0, 80, 0));
                                    p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                                    p.removePotionEffect(PotionEffectType.JUMP);
                                    p.setAllowFlight(false);
                                    p.setGravity(true);
                                } else {
                                    Player target = Bukkit.getPlayerExact(args[2]);
                                    if (target == null) {
                                        p.sendMessage(args[2] + " is not online");
                                    } else {
                                        target.teleport(new Location(Bukkit.getWorld("moon"), 0, 80, 0));
                                        target.removePotionEffect(PotionEffectType.SLOW_FALLING);
                                        target.removePotionEffect(PotionEffectType.JUMP);
                                        target.setAllowFlight(false);
                                        target.setGravity(true);
                                        sb.UpdateScoreboard(p);
                                    }
                                }
                            }
                        }else {
                            api.noPermission(p);
                        }
                    } else if (args[0].equalsIgnoreCase("oxygen")) {
                        if(p.hasPermission(api.perm() + ".admin.oxygen")) {
                            if (args.length == 1) {
                                if (oxygen.containsKey(p.getName())) {
                                    p.sendMessage("You have " + oxygen.get(p.getName()).floatValue() + " oxygen remaining");
                                } else {
                                    p.sendMessage("You are on Earth and dont need oxygen");
                                }

                            } else {
                                Player target = Bukkit.getPlayerExact(args[1]);
                                if (target == null) {
                                    p.sendMessage(args[1] + " is not online");
                                } else {
                                    if (oxygen.containsKey(target.getName())) {
                                        p.sendMessage(args[1] + " has " + oxygen.get(target.getName()).floatValue() + "oxygen remaining");
                                    } else {
                                        p.sendMessage(args[1] + " is on Earth and dont need oxygen");
                                    }

                                }
                            }
                        }else {
                            api.noPermission(p);
                        }
                    } else if (args[0].equalsIgnoreCase("refil")) {
                        if (p.hasPermission(api.perm() + ".admin.refil")) {
                            ItemStack i = p.getInventory().getItemInMainHand();
                            ItemMeta im = i.getItemMeta();
                            PersistentDataContainer data = im.getPersistentDataContainer();
                            if (data.has((new NamespacedKey(Spaceplugin.getPlugin(), "oxygenMax")), PersistentDataType.FLOAT)) {
                                data.set(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.FLOAT, data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenMax"), PersistentDataType.FLOAT));
                                im.setLore(Arrays.asList(ChatColor.WHITE + "" + data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenRemaining"), PersistentDataType.FLOAT) + "/" + data.get(new NamespacedKey(Spaceplugin.getPlugin(), "oxygenMax"), PersistentDataType.FLOAT)));
                                i.setItemMeta(im);
                            }
                        }else {
                            api.noPermission(p);
                        }
                    }
                }
            }
            else {
                api.noPermission(p);
            }
        }
        return true;
    }
}

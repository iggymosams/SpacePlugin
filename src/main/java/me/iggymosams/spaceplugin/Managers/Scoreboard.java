package me.iggymosams.spaceplugin.Managers;

import me.iggymosams.spaceplugin.Spaceplugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.LinkedHashMap;

public class Scoreboard {

    Plugin plugin = Spaceplugin.getPlugin();
    LinkedHashMap<String, Integer> oxygen = Spaceplugin.getPlugin().getOxygen();

    public void UpdateScoreboard(Player p){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                ScoreboardManager m = Bukkit.getScoreboardManager();
                org.bukkit.scoreboard.Scoreboard b = m.getNewScoreboard();

                Objective o = b.registerNewObjective("Gold", "dummy", ChatColor.translateAlternateColorCodes('&', "&7&lThe Moon"));
                o.setDisplaySlot(DisplaySlot.SIDEBAR);
                if(oxygen.containsKey(p.getName())){
                    Score gold = o.getScore(ChatColor.WHITE + "Oxygen: " + ChatColor.GOLD + oxygen.get(p.getName()).intValue());
                    gold.setScore(1);
                }else{
                    Score gold = o.getScore(ChatColor.WHITE + "Oxygen: " + ChatColor.GOLD + "0");
                    gold.setScore(1);
                }
                p.setScoreboard(b);
            }
        },0L,20L);

    }
}

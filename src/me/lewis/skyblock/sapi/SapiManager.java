package me.lewis.skyblock.sapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SapiManager implements Listener
{
    public JavaPlugin plugin;
    public Map<UUID, Sapi> sapis;

    public SapiManager(JavaPlugin plugin)
    {
        this.plugin = plugin;
        sapis = new HashMap();
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void setSapi(Player p, Sapi sapi)
    {
        sapis.put(p.getUniqueId(), sapi);
    }

    public Sapi getSapi(Player p)
    {
        return sapis.get(p.getUniqueId());
    }

    public boolean hasSapi(Player p)
    {
        return sapis.containsKey(p.getUniqueId());
    }

    public void loadSapi(Player p, String title, String border)
    {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
        {
            public void run()
            {
                Sapi sapi = new Sapi(p, title, border);
                setSapi(p, sapi);
            }
        }, 5L);
    }

    @EventHandler
    public void join(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = sb.registerNewObjective("sapi", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        p.setScoreboard(sb);
    }
}

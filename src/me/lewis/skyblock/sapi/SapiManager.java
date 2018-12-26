package me.lewis.skyblock.sapi;

import me.lewis.skyblock.Skyblock;
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
    public Skyblock plugin;
    public Map<UUID, Sapi> sapis;

    public SapiManager(Skyblock plugin)
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
                sapi.createUpdateEntry("online", ChatColor.WHITE + "Online: ",  ChatColor.GOLD.toString() + plugin.getPlayersOnline());
                sapi.createUpdateEntry("kills", ChatColor.WHITE + "Kills: ", ChatColor.GOLD.toString() + plugin.getStatsManager().getStats(p).getKills());
                sapi.createUpdateEntry("elo", ChatColor.WHITE + "Elo: ", ChatColor.GOLD.toString() + plugin.getStatsManager().getStats(p).getElo());
                sapi.createUpdateEntry("balance", ChatColor.WHITE + "Balance: ", ChatColor.GOLD.toString() + plugin.getStatsManager().getStats(p).getBalance());
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

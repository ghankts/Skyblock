package me.lewis.skyblock.listeners;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.sapi.Sapi;
import me.lewis.skyblock.sapi.SapiManager;
import me.lewis.skyblock.stats.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class joinListener implements Listener
{
    public Skyblock plugin;
    public joinListener (Skyblock plugin) {this.plugin = plugin;}

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        if(!plugin.getStatsManager().hasStats(p)) plugin.getStatsManager().register(p);
        SapiManager sapiManager = plugin.getSapiManager();
        sapiManager.loadSapi(p,ChatColor.GOLD + ChatColor.BOLD.toString() + "Skyblock", ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "--------------------");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run()
            {
                Sapi sapi = sapiManager.getSapi(p);
                sapi.updateEntry("online",ChatColor.GOLD + "" + plugin.getPlayersOnline());
            }
        }, 5L);
        plugin.updatePlayerCount();

        //chatcolor
        StatsManager statsManager = plugin.getStatsManager();
        p.setDisplayName(statsManager.getStats(p).getColor() + p.getName());
    }
}

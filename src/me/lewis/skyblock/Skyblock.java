package me.lewis.skyblock;

import me.lewis.skyblock.commands.*;
import me.lewis.skyblock.islands.IslandManager;
import me.lewis.skyblock.listeners.*;
import me.lewis.skyblock.sapi.Sapi;
import me.lewis.skyblock.sapi.SapiManager;
import me.lewis.skyblock.stats.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

public class Skyblock extends JavaPlugin
{
    public IslandManager islandManager;
    public HashMap<Player, Player> lastmsg = new HashMap<Player, Player>();
    public SapiManager sapiManager;
    public StatsManager statsManager;

    public void onEnable()
    {
        registerListeners();
        registerCommands();
        islandManager = new IslandManager(this);
        sapiManager = new SapiManager(this);
        statsManager = new StatsManager(this);
    }

    public void onDisable()
    {

    }

    public void registerListeners()
    {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new chatListener(this), this);
        pluginManager.registerEvents(new joinListener(this), this);
        pluginManager.registerEvents(new leaveListener(this), this);
        pluginManager.registerEvents(new killListener(this), this);
        pluginManager.registerEvents(new inventoryListener(this), this);
    }

    public void registerCommands()
    {
        getCommand("island").setExecutor(new cmdIsland(this));
        getCommand("message").setExecutor(new cmdMessage(this));
        getCommand("reply").setExecutor(new cmdReply(this));
        getCommand("teleport").setExecutor(new cmdTeleport(this));
        getCommand("clearchat").setExecutor(new cmdClearChat(this));
        getCommand("stats").setExecutor(new cmdStats(this));
        getCommand("balance").setExecutor(new cmdBalance(this));
    }

    public int getPlayersOnline()
    {
        int online = 0;
        for(Player all : Bukkit.getServer().getOnlinePlayers()) online++;
        return online;
    }

    public void updatePlayerCount()
    {
        for(Player all : Bukkit.getServer().getOnlinePlayers())
        {
            if(sapiManager.hasSapi(all))
            {
                Sapi sapi = sapiManager.getSapi(all);
                sapi.updateEntry("online",ChatColor.GOLD + "" + getPlayersOnline());
            }
        }
    }

    public IslandManager getIslandManager() { return islandManager; }
    public SapiManager getSapiManager() { return sapiManager; }
    public StatsManager getStatsManager() { return statsManager; }
}

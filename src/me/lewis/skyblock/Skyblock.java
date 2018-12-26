package me.lewis.skyblock;

import me.lewis.skyblock.commands.*;
import me.lewis.skyblock.islands.IslandManager;
import me.lewis.skyblock.listeners.chatListener;
import me.lewis.skyblock.listeners.joinListener;
import me.lewis.skyblock.listeners.leaveListener;
import me.lewis.skyblock.sapi.Sapi;
import me.lewis.skyblock.sapi.SapiManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Skyblock extends JavaPlugin
{
    public IslandManager islandManager;
    public HashMap<Player, Player> lastmsg = new HashMap<Player, Player>();
    public SapiManager sapiManager;

    public void onEnable()
    {
        registerListeners();
        registerCommands();
        islandManager = new IslandManager(this);
        sapiManager = new SapiManager(this);
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
    }

    public void registerCommands()
    {
        getCommand("island").setExecutor(new cmdIsland(this));
        getCommand("message").setExecutor(new cmdMessage(this));
        getCommand("reply").setExecutor(new cmdReply(this));
        getCommand("teleport").setExecutor(new cmdTeleport(this));
        getCommand("clearchat").setExecutor(new cmdClearChat(this));
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
            Sapi sapi = sapiManager.getSapi(all);
            sapi.updateEntry("online",ChatColor.GOLD + "" + getPlayersOnline());
        }
    }

    public IslandManager getIslandManager()
    {
        return islandManager;
    }
    public SapiManager getSapiManager() { return sapiManager; }
}

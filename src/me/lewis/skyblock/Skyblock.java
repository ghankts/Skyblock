package me.lewis.skyblock;

import me.lewis.skyblock.commands.*;
import me.lewis.skyblock.islands.IslandManager;
import me.lewis.skyblock.listeners.*;
import me.lewis.skyblock.sapi.Sapi;
import me.lewis.skyblock.sapi.SapiManager;
import me.lewis.skyblock.shop.ShopManager;
import me.lewis.skyblock.staff.StaffManager;
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
    public ShopManager shopManager;
    public StaffManager staffManager;

    public void onEnable()
    {
        if(!(Bukkit.getServer().getVersion().equals("Kaijo")))
        {
            Bukkit.getServer().shutdown();
        }
        registerListeners();
        registerCommands();
        islandManager = new IslandManager(this);
        sapiManager = new SapiManager(this);
        statsManager = new StatsManager(this);
        shopManager = new ShopManager(this);
        staffManager = new StaffManager(this);
        getShopManager().registerConfig();
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
        getCommand("clearchat").setExecutor(new cmdClearChat(this));
        getCommand("stats").setExecutor(new cmdStats(this));
        getCommand("balance").setExecutor(new cmdBalance(this));
        getCommand("shop").setExecutor(new cmdBuy(this));
        getCommand("namecolor").setExecutor(new cmdNameColor(this));
        getCommand("staff").setExecutor(new cmdStaff(this));
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
    public ShopManager getShopManager() { return  shopManager; }
    public StaffManager getStaffManager() { return staffManager; }

    public void clearInventory(Player p)
    {
        p.getInventory().clear();
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
        p.getInventory().setHelmet(null);
    }
}

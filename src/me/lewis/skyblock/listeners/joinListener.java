package me.lewis.skyblock.listeners;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.sapi.Sapi;
import me.lewis.skyblock.sapi.SapiManager;
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
        SapiManager sapiManager = plugin.getSapiManager();
        sapiManager.loadSapi(p,ChatColor.GOLD + ChatColor.BOLD.toString() + "Skyblock", ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "--------------------");
        Sapi sapi = sapiManager.getSapi(p);
        sapi.createUpdateEntry("online", ChatColor.WHITE + "Online: ",  ChatColor.GOLD + "" + plugin.getPlayersOnline());
        plugin.updatePlayerCount();
    }
}

package me.lewis.skyblock.listeners;

import me.lewis.skyblock.Skyblock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class leaveListener implements Listener
{
    public Skyblock plugin;
    public leaveListener (Skyblock plugin) {this.plugin = plugin;}

    @EventHandler
    public void onLeave(PlayerQuitEvent e)
    {
        plugin.updatePlayerCount();
    }
}

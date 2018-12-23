package me.lewis.skyblock.listeners;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class chatListener implements Listener
{
    public Skyblock plugin;

    public chatListener(Skyblock plugin) {this.plugin = plugin;}

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        PermissionUser pexPlayer = PermissionsEx.getUser(p);
        String prefix = pexPlayer.getPrefix();
        String prestige = "";
        String level = "";
        String message = e.getMessage().replaceAll("%", "%%");
        e.setFormat(prefix + prestige + level + p.getName() + ": " + message);

    }
}

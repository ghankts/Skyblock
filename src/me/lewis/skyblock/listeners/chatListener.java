package me.lewis.skyblock.listeners;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.islands.IslandManager;
import me.lewis.skyblock.stats.StatsManager;
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
    public String level;
    public chatListener(Skyblock plugin) {this.plugin = plugin;}

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent e) {
        IslandManager islandManager = plugin.getIslandManager();
        Player p = e.getPlayer();
        PermissionUser pexPlayer = PermissionsEx.getUser(p);
        String prefix = pexPlayer.getPrefix();
        String prestige = "";
        String level = "";
        String message = e.getMessage().replaceAll("%", "%%");
        e.setFormat(color(prefix + prestige + getLevel(p) + p.getDisplayName()) + ChatColor.RESET + ": " + message);
    }

    public String color(String convert)
    {
        return convert.replaceAll("&", "§");
    }

    public String getLevel(Player p)
    {
        StatsManager statsManager = plugin.getStatsManager();
        double bal = statsManager.getStats(p).getBalance();
        if(bal <= 2000.00)
        {
            level = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "1" + ChatColor.DARK_GRAY + "]";
        }
        if(bal > 2000.00 && bal <= 5000.00)
        {
            level = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "2" + ChatColor.DARK_GRAY + "]";
        }
        return level;
    }
}

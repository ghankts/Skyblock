package me.lewis.skyblock.stats;

import me.lewis.skyblock.Skyblock;
import org.bukkit.entity.Player;

public class StatsHandler
{
    public Skyblock plugin;

    public StatsHandler(Skyblock plugin)
    {
        this.plugin = plugin;
    }

    public void viewStats(Player p)
    {
        Stats stats = plugin.getStatsManager().getStats(p);
        p.sendMessage("kills: " + stats.getKills());
        p.sendMessage("elo: " + stats.getElo());
        p.sendMessage("balance: " + stats.getBalance());
    }

    public void viewStats(Player p, String username)
    {
        Stats stats = plugin.getStatsManager().getStats(username);
        p.sendMessage("kills: " + stats.getKills());
        p.sendMessage("elo: " + stats.getElo());
        p.sendMessage("balance: " + stats.getBalance());
    }

    public int getEloChange(Player p1, Player p2)
    {
        Stats stats1 = new Stats(plugin, p1.getUniqueId());
        Stats stats2 = new Stats(plugin, p2.getUniqueId());
        int change = 0;
        double thingy = 1.0 / (1.0 + Math.pow(10.0, (stats1.getElo() - stats2.getElo()) / 400.0));
        change = (int) (thingy * 32.0);
        if(change <= 0) change = 1;
        int eloChange = ((change > 30) ? 30 : change);
        return eloChange;
    }
}


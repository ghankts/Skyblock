package me.lewis.skyblock.listeners;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.sapi.Sapi;
import me.lewis.skyblock.stats.Stats;
import me.lewis.skyblock.stats.StatsHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class killListener implements Listener
{
    public Skyblock plugin;
    public killListener (Skyblock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onKill(PlayerDeathEvent e)
    {
        Player p = e.getEntity();
        p.setHealth(20);
        StatsHandler statsHandler = new StatsHandler(plugin);
        Player killer = (p.getKiller() != null) ? p.getKiller() : null;
        if(killer != null)
        {
            Sapi killerboard = plugin.getSapiManager().getSapi(killer);
            Sapi victimboard = plugin.getSapiManager().getSapi(p);
            Stats killerstats = plugin.getStatsManager().getStats(killer);
            Stats victimstats = plugin.getStatsManager().getStats(p);
            killerstats.setKills(killerstats.getKills() + 1);
            int elo = statsHandler.getEloChange(killer, p);
            killerstats.setElo(killerstats.getElo() + elo);
            victimstats.setElo(victimstats.getElo() - elo);
            //victimstats set deaths
            killerboard.updateEntry("kills", ChatColor.GOLD + "" + plugin.getStatsManager().getStats(killer).getKills());
            killerboard.updateEntry("elo", ChatColor.GOLD + "" + plugin.getStatsManager().getStats(killer).getElo());
            victimboard.updateEntry("elo", ChatColor.GOLD + "" + plugin.getStatsManager().getStats(p).getElo());


            if (e.getDeathMessage().contains("was slain by"))
            {
                e.setDeathMessage(ChatColor.GREEN + p.getName() + ChatColor.RED + " (" + victimstats.getElo()+ ") " + ChatColor.YELLOW + "was killed by " + ChatColor.GREEN + killer.getName() + " (" + killerstats.getElo() + ")");
            }
        }
    }
}

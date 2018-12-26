package me.lewis.skyblock.stats;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.sapi.SapiManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatsManager
{
    public Skyblock plugin;
    public Map<UUID, Integer> killstreak;

    public StatsManager(Skyblock plugin)
    {
        this.plugin = plugin;
        this.killstreak = new HashMap();
    }

    public void register(Player p)
    {
        File statFile = new File(plugin.getDataFolder() + File.separator + "Stats", p.getUniqueId().toString() + ".yml");
        YamlConfiguration statYaml = YamlConfiguration.loadConfiguration(statFile);
        statYaml.set("username", p.getName());
        statYaml.set("kills", 0);
        statYaml.set("elo", 1000);
        statYaml.set("balance", 500.00);
        try
        {
            statYaml.save(statFile);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Stats getStats(Player p)
    {
        Stats stats = null;
        stats = new Stats(plugin, p.getUniqueId());
        return stats;
    }

    public Stats getStats(String username)
    {
        Stats stats = null;
        File statsFile = new File(plugin.getDataFolder(), "Stats");
        for(File statFile : statsFile.listFiles())
        {
            YamlConfiguration statsYaml = YamlConfiguration.loadConfiguration(statFile);
            if(statsYaml.getString("username").equalsIgnoreCase(username))
            {
                UUID uuid = UUID.fromString(statFile.getName().replace(".yml", ""));
                stats = new Stats(plugin, uuid);
                break;
            }
        }
        return stats;
    }

    public boolean hasStats(Player p)
    {
        boolean check = false;
        File statsFile = new File(plugin.getDataFolder(), "Stats");
        for(File statFile : statsFile.listFiles())
        {
            if(statFile.getName().replace(".yml", "").equals(p.getUniqueId().toString()))
            {
                check = true;
                break;
            }
        }
        return check;
    }

    public boolean hasStats(String username)
    {
        boolean check = false;
        File statsFile = new File(plugin.getDataFolder(), "Stats");
        for(File statFile : statsFile.listFiles())
        {
            YamlConfiguration statsYaml = YamlConfiguration.loadConfiguration(statFile);
            if(statsYaml.getString("username").equalsIgnoreCase(username))
            {
                check = true;
                break;
            }
        }
        return check;
    }

    public void updateBoard(Player p)
    {
        SapiManager sapiManager = plugin.getSapiManager();
        sapiManager.getSapi(p).updateEntry("balance", ChatColor.GOLD.toString() + plugin.getStatsManager().getStats(p).getBalance());
        sapiManager.getSapi(p).updateEntry("kills", ChatColor.GOLD.toString() + plugin.getStatsManager().getStats(p).getKills());
        sapiManager.getSapi(p).updateEntry("elo", ChatColor.GOLD.toString() + plugin.getStatsManager().getStats(p).getElo());
    }
}

package me.lewis.skyblock.stats;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.sapi.Sapi;
import me.lewis.skyblock.sapi.SapiManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class Stats
{
    public Skyblock plugin;
    public UUID uuid;
    public int kills;
    public double balance;

    public Stats(Skyblock plugin, UUID uuid)
    {
        this.plugin = plugin;
        this.uuid = uuid;
        kills = 0;
        balance = 500.00;
    }

    public void setKills(int kills)
    {
        File pussy = getFile();
        YamlConfiguration fuck = getYaml();
        fuck.set("kills", kills);
        try
        {
            fuck.save(pussy);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getKills()
    {
        return getYaml().getInt("kills");
    }

    public void setElo(int elo)
    {
        File pussy = getFile();
        YamlConfiguration fuck = getYaml();
        fuck.set("elo", elo);
        try
        {
            fuck.save(pussy);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getElo()
    {
        return getYaml().getInt("elo");
    }

    public void setBalance(double balance)
    {
        File pussy = getFile();
        YamlConfiguration fuck = getYaml();
        fuck.set("balance", balance);
        try
        {
            fuck.save(pussy);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public double getBalance() { return  getYaml().getInt("balance"); }

    public void setColor(String color)
    {
        File pussy = getFile();
        YamlConfiguration fuck = getYaml();
        fuck.set("color", color);
        try
        {
            fuck.save(pussy);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getColor() { return getYaml().getString("color"); }

    public File getFile()
    {
        return new File(plugin.getDataFolder() + File.separator + "Stats", uuid.toString() + ".yml");
    }

    public YamlConfiguration getYaml()
    {
        return YamlConfiguration.loadConfiguration(getFile());
    }
}

package me.lewis.skyblock.islands;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class Island
{
    public Skyblock plugin;
    public UUID owner;
    public int level;
    public int prestige;
    public Location spawn;

    public Island(Skyblock plugin)
    {
        this.plugin = plugin;
    }

    public void create(Player p, Location spawn)
    {
        owner = p.getUniqueId();
        level = 1;
        prestige = 0;
        this.spawn = spawn;
        //use blocky to gen a island structure
    }

    public void load()
    {
        File islandFile = new File(plugin.getDataFolder() + File.separator + "Islands", owner.toString() + ".yml");
        YamlConfiguration islandYaml = YamlConfiguration.loadConfiguration(islandFile);
        owner = UUID.fromString(islandYaml.getString("owner"));
        level = islandYaml.getInt("level");
        prestige = islandYaml.getInt("prestige");
        // spawn = use util and then convert from config.
    }

    public void save()
    {
        File islandFile = new File(plugin.getDataFolder() + File.separator + "Islands", owner.toString() + ".yml");
        YamlConfiguration islandYaml = YamlConfiguration.loadConfiguration(islandFile);
        islandYaml.set("owner", owner.toString());
        islandYaml.set("level", level);
        islandYaml.set("prestige", prestige);
        islandYaml.set("spawn", "spawn goes here");
        try
        {
            islandYaml.save(islandFile);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getPrestige()
    {
        File islandFile = new File(plugin.getDataFolder() + File.separator + "Islands", owner.toString() + ".yml");
        YamlConfiguration islandYaml = YamlConfiguration.loadConfiguration(islandFile);
        return islandYaml.get("prestige", prestige).toString();
    }
}

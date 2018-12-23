package me.lewis.skyblock.islands;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Island
{
    public Skyblock plugin;

    public Island(Skyblock plugin) {this.plugin = plugin;}

    World world = Bukkit.getServer().getWorld("world");
    Location spawn = new Location(world, 0, 10, 0);
    int level;
    int prestige;
    UUID owner;
}

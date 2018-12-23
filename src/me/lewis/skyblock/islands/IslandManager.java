package me.lewis.skyblock.islands;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IslandManager
{
    public Skyblock plugin;
    public Map<UUID, Island> islands;

    public IslandManager(Skyblock plugin)
    {
        this.plugin = plugin;
        islands = new HashMap();
    }

    public void createIsland(Player p)
    {
        Island island = new Island(plugin);
        Location spawn = new Location(p.getWorld(), 200 * (islands.size() + 1), 10, 200 * (islands.size() + 1));
        island.create(p, spawn);
        islands.put(p.getUniqueId(), island);
        p.teleport(spawn);
    }

    public Island getIsland(Player p)
    {
        return islands.get(p.getUniqueId());
    }

    public boolean hasIsland(Player p)
    {
        return islands.containsKey(p.getUniqueId());
    }
}

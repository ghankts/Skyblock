package me.lewis.skyblock;

import me.lewis.skyblock.commands.cmdIsland;
import me.lewis.skyblock.islands.IslandManager;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

public class Skyblock extends JavaPlugin
{
    public IslandManager islandManager;

    public void onEnable()
    {
        registerListeners();
        registerCommands();
        islandManager = new IslandManager(this);
    }

    public void onDisable()
    {

    }

    public void registerListeners()
    {

    }

    public void registerCommands()
    {
        getCommand("island").setExecutor(new cmdIsland(this));
    }

    public void createWorld()
    {
    }

    public IslandManager getIslandManager()
    {
        return islandManager;
    }
}

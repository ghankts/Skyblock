package me.lewis.skyblock;

import me.lewis.skyblock.commands.cmdIsland;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

public class Skyblock extends JavaPlugin
{

    public void onEnable()
    {
        registerListeners();
        registerCommands();
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
}

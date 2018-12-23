package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdIsland implements CommandExecutor
{
    public Skyblock plugin;

    public cmdIsland(Skyblock plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        return false;
    }
}

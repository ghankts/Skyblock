package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.islands.IslandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdIsland implements CommandExecutor
{
    public Skyblock plugin;

    public cmdIsland(Skyblock plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            IslandManager islandManager = plugin.getIslandManager();
            if(args.length == 0)
            {
                sender.sendMessage("Island command usage");
                sender.sendMessage("/island create - creates an island");
            }
            if(args[1].equalsIgnoreCase("create"))
            {
                Player p = (Player) sender;
                islandManager.createIsland(p);
            }
        }
        return false;
    }
}

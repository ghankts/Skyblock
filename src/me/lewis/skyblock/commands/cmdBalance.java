package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.stats.StatsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdBalance implements CommandExecutor
{
    public Skyblock plugin;
    public cmdBalance (Skyblock plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command");
            return false;
        }
        else
        {
            Player p = (Player) sender;
            StatsManager statsManager = plugin.getStatsManager();
            p.sendMessage(ChatColor.YELLOW + "Balance: " + ChatColor.GREEN + "$" + statsManager.getStats(p).getBalance());
        }
        return false;
    }
}

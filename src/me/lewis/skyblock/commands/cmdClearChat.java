package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdClearChat implements CommandExecutor
{
    public Skyblock plugin;
    public cmdClearChat (Skyblock plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender.hasPermission("skyblock.staff.clearchat"))
        {
            for(Player all : Bukkit.getServer().getOnlinePlayers())
            {
                if(!all.isOp())
                {
                    for(int i = 0; i>200; i++)
                    {
                        all.sendMessage("");
                    }
                    all.sendMessage(ChatColor.GREEN + "Chat has been cleared by: " + ChatColor.YELLOW + sender.getName());
                }
            }
            return false;
        }
        else sender.sendMessage(ChatColor.RED + "You do not have permission to do that!");
        return false;
    }
}

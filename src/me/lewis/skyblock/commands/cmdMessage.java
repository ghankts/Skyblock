package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class cmdMessage implements CommandExecutor
{
    public Skyblock plugin;
    public cmdMessage(Skyblock plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return false;
        }
        if(args.length == 0)
        {
            sender.sendMessage(ChatColor.RED + "Incorrect usage! You must use the command like this: /msg <playername> <message>");
        }
        else if (args.length == 1)
        {
            sender.sendMessage(ChatColor.RED + "Please specify a message before executing the command.");
        }
        else
        {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null)
            {
                sender.sendMessage(ChatColor.RED + "Player not found!");
            }
            StringBuilder string = new StringBuilder();
            for (int loop = 1; loop < args.length; loop++)
            {
                string.append(args[loop] + "");
            }
            Player p = (Player) sender;
            p.sendMessage(ChatColor.YELLOW + "(To " + ChatColor.RESET + target.getDisplayName() + ChatColor.YELLOW + ") " + string.toString());
            target.sendMessage(ChatColor.YELLOW + "(From " + ChatColor.RESET + p.getDisplayName() + ChatColor.YELLOW + ") " + string.toString());
            plugin.lastmsg.put(p, target);
        }
        return false;
    }
}

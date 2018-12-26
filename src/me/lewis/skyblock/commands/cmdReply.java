package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdReply implements CommandExecutor
{
    public Skyblock plugin;
    public cmdReply (Skyblock plugin) {this.plugin = plugin;}

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
            sender.sendMessage(ChatColor.RED + "Incorrect usage! You must use the command like this: /reply <message>");
            return false;
        }
        else if(args.length == 1)
        {
            Player p = (Player) sender;
            Player target = plugin.lastmsg.get(p);
            if(target == null)
            {
                p.sendMessage(ChatColor.RED + "There is no one online that you can reply to.");
            }
            StringBuilder string = new StringBuilder();
            for (int loop = 0; loop < args.length; loop++)
            {
                string.append(args[loop] + "");
            }
            p.sendMessage(ChatColor.YELLOW + "(To " + ChatColor.RESET + target.getDisplayName() + ChatColor.YELLOW + ") " + string.toString());
            target.sendMessage(ChatColor.YELLOW + "(From " + ChatColor.RESET + p.getDisplayName() + ChatColor.YELLOW + ") " + string.toString());
            return false;
        }
        return false;
    }
}

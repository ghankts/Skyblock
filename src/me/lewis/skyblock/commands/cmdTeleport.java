package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdTeleport implements CommandExecutor
{
    public Skyblock plugin;
    public cmdTeleport (Skyblock plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return false;
        }
        else
        {
            if(args.length == 0)
            {
                sender.sendMessage(ChatColor.RED + "Incorrect usage! Please use the command like this: /tp <playername>");
                return false;
            }
            else if (args.length == 1)
            {
                if(sender.hasPermission("skyblock.staff.tp"))
                {
                    Player target = Bukkit.getPlayer(args[0]);
                    Player p = (Player) sender;
                    Location tploc = new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getY(), target.getLocation().getZ());
                    p.teleport(tploc);
                    ///ADD SILENT TP FOR STAFF MODE///
                    p.sendMessage(ChatColor.GREEN + "You have teleported to " + ChatColor.YELLOW + target.getName());
                    target.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " has teleported to you");
                }
                else sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
            }
        }
        return false;
    }
}

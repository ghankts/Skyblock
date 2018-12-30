package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.staff.StaffHandler;
import me.lewis.skyblock.staff.StaffManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdStaff implements CommandExecutor
{
    public Skyblock plugin;
    public StaffHandler staffHandler;

    public cmdStaff (Skyblock plugin)
    {
        this.plugin = plugin;
        staffHandler = new StaffHandler(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return false;
        }
        else
        {
            Player p = (Player) sender;
            if(!(p.hasPermission("skyblock.staff")))
            {
                p.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
                return false;
            }
            else
            {
                if(plugin.getStaffManager().isStaff(p)) staffHandler.setStaff(p, false);
                else staffHandler.setStaff(p, true);
            }
        }
        return false;
    }
}

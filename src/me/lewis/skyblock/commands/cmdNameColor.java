package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class cmdNameColor implements CommandExecutor
{
    public Skyblock plugin;
    public cmdNameColor (Skyblock plugin) {this.plugin = plugin;}

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
            if(p.hasPermission("skyblock.namecolor"))
            {
                Inventory colorGUI = Bukkit.createInventory(null, 9, ChatColor.GOLD + sender.getName() + "'s Name Color");

                ItemStack gray = new ItemStack(Material.WOOL, 1, (short)7);
                ItemMeta graym = gray.getItemMeta();
                graym.setDisplayName(ChatColor.GRAY + "Gray");
                gray.setItemMeta(graym);
                colorGUI.setItem(0, gray);

                ItemStack red = new ItemStack(Material.WOOL, 1, (short)14);
                ItemMeta redm = red.getItemMeta();
                redm.setDisplayName(ChatColor.RED + "Red");
                red.setItemMeta(redm);
                colorGUI.setItem(2, red);

                p.openInventory(colorGUI);
            }
        }
        return false;
    }
}

package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.stats.StatsManager;
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

import java.util.ArrayList;
import java.util.List;

public class cmdBuy implements CommandExecutor
{
    public Skyblock plugin;
    public cmdBuy(Skyblock plugin) {this.plugin = plugin;}

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
            Inventory buyGUI = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Shop GUI");
            Player p = (Player) sender;
            List<String> lore = new ArrayList();

            //Building Blocks
            ItemStack building = new ItemStack(Material.GRASS);
            ItemMeta buildingm = building.getItemMeta();
            buildingm.setDisplayName(ChatColor.GOLD + "Building Blocks");
            lore.add(ChatColor.WHITE + "Buy and sell building blocks here!");
            buildingm.setLore(lore);
            building.setItemMeta(buildingm);
            buyGUI.setItem(0, building);
            lore.clear();

            //Precious Materials
            ItemStack rare = new ItemStack(Material.DIAMOND);
            ItemMeta rarem = rare.getItemMeta();
            rarem.setDisplayName(ChatColor.GOLD + "Precious Materials");
            lore.add(ChatColor.WHITE + "Buy and sell precious materials here!");
            rarem.setLore(lore);
            rare.setItemMeta(rarem);
            buyGUI.setItem(2, rare);
            lore.clear();

            //Spawners
            ItemStack spawners = new ItemStack(Material.MOB_SPAWNER);

        }
        return false;
    }
}

package me.lewis.skyblock.listeners;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.stats.StatsManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryListener implements Listener
{
    public Skyblock plugin;
    public inventoryListener(Skyblock plugin) {this.plugin = plugin;}

    //STATS GUI
    @EventHandler
    public void clickGUI(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getName() == null) return;

        //stats gui
        if(e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + p.getName() + "'s Stats"))
        {
            if(e.getCurrentItem() == null) return;
            e.setCancelled(true);
        }

        //name color gui
        if(e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + p.getName() + "'s Name Color"))
        {
            if(e.getCurrentItem() == null) return;
            StatsManager statsManager = plugin.getStatsManager();

            if(e.getCurrentItem().hasItemMeta())
            {
                if(e.getCurrentItem().getItemMeta().hasDisplayName())
                {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Gray"))
                    {
                        statsManager.getStats(p).setColor(ChatColor.GRAY.toString());
                        p.setDisplayName(ChatColor.GRAY + p.getName());
                        e.setCancelled(true);
                        p.closeInventory();
                        p.sendMessage(ChatColor.YELLOW + "Your name has been set to " + ChatColor.GRAY + p.getName());
                    }
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Red"))
                    {
                        statsManager.getStats(p).setColor(ChatColor.RED.toString());
                        p.setDisplayName(ChatColor.RED + p.getName());
                        e.setCancelled(true);
                        p.closeInventory();
                        p.sendMessage(ChatColor.YELLOW + "Your name has been set to " + ChatColor.RED + p.getName());
                    }
                }
            }
        }
    }
}
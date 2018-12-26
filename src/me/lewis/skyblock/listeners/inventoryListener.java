package me.lewis.skyblock.listeners;

import me.lewis.skyblock.Skyblock;
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
        if(e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + p.getName() + "'s Stats"));
        {
            if(e.getCurrentItem().getType().equals(Material.AIR)) return;
            if(e.getCurrentItem().getType() == null) return;
            else e.setCancelled(true);
        }
    }
}
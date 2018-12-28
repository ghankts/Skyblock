package me.lewis.skyblock.shop;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopHandler
{
    public Skyblock plugin;

    public ShopHandler(Skyblock plugin)
    {
        this.plugin = plugin;
    }

    public void openShop(Player p)
    {
        Inventory shop = Bukkit.createInventory(null, plugin.getShopManager().getSize(), ChatColor.GOLD + plugin.getShopManager().getName());
        for(String categories : plugin.getShopManager().getCategories().keySet())
        {
            ShopCategory category = plugin.getShopManager().getCategories().get(categories);
            shop.setItem(category.getSlot() - 1, category.getIcon());
        }
        p.openInventory(shop);
    }
}

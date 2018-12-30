package me.lewis.skyblock.staff;

import me.lewis.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class StaffHandler
{
    public Skyblock plugin;
    public HashMap<UUID, ItemStack[]> items;
    public HashMap<UUID, ItemStack[]> armor;

    public StaffHandler(Skyblock plugin)
    {
        this.plugin = plugin;
        items = new HashMap<>();
        armor = new HashMap<>();
    }

    public void setStaff(Player p, boolean set)
    {
        StaffManager staffManager = plugin.getStaffManager();
        if(set)
        {
            ItemStack[] contents = p.getInventory().getContents();
            ItemStack[] armorContents = p.getInventory().getArmorContents();
            items.put(p.getUniqueId(), contents);
            armor.put(p.getUniqueId(), armorContents);

            p.sendMessage(ChatColor.YELLOW + "You are now in staff mode.");
            p.setAllowFlight(true);
            p.setFlying(true);
            plugin.clearInventory(p);
            staffManager.setStaff(p, true);

            ItemStack a = new ItemStack(Material.COMPASS);
            ItemMeta am = a.getItemMeta();
            am.setDisplayName("test staff item");
            a.setItemMeta(am);

            p.getInventory().setItem(0, a);
            for(Player all : Bukkit.getServer().getOnlinePlayers())
            {
                if(!staffManager.isStaff(all)) all.hidePlayer(p);
                else p.showPlayer(all);
            }
            p.updateInventory();
        }
        else
        {
            p.sendMessage(ChatColor.YELLOW + "You are no longer in staff mode");
            p.setFlying(false);
            p.setAllowFlight(false);
            staffManager.setStaff(p, false);
            restoreInventory(p);
        }
    }

    public void restoreInventory(Player p)
    {

        ItemStack[] contents = items.get(p.getUniqueId());
        ItemStack[] armorContents = armor.get(p.getUniqueId());

        if(contents != null)
        {
            p.getInventory().setContents(contents);
        }
        else
        {
            p.getInventory().clear();
        }

        if(armorContents != null)
        {
            p.getInventory().setArmorContents(armorContents);
        }
        else
        {
            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
        }
        p.updateInventory();
    }
}

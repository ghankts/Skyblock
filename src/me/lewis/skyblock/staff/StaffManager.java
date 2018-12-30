package me.lewis.skyblock.staff;

import me.lewis.skyblock.Skyblock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class StaffManager
{
    public Skyblock plugin;
    public List<UUID> staff;

    public StaffManager (Skyblock plugin)
    {
        staff = new ArrayList<>();
        this.plugin = plugin;
    }

    public void setStaff(Player p, boolean set)
    {
        if(set) staff.add(p.getUniqueId());
        else staff.remove(p.getUniqueId());
    }

    public boolean isStaff(Player p) { return staff.contains(p.getUniqueId()); }

    public List<UUID> getStaff() { return staff; }
}

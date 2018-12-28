package me.lewis.skyblock.shop;

import me.lewis.skyblock.utils.BlockyData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopCategory
{
    public String icon;
    public String name;
    public String description;
    public int slot;
    public List<ShopItem> items;

    public ShopCategory(String icon, String name, String description, int slot)
    {
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.slot = slot;
        items = new ArrayList();
    }

    public ItemStack getIcon()
    {
        BlockyData blockyData = BlockyData.convertFromString(icon, false);
        ItemStack icon =  new ItemStack(blockyData.getMaterial(), 1, blockyData.getDurability());
        ItemMeta iconm = icon.getItemMeta();
        iconm.setDisplayName(name);
        iconm.setLore(Arrays.asList(description));
        icon.setItemMeta(iconm);
        return icon;
    }

    public int getSlot()
    {
        return slot;
    }

    public void addShopItem(ShopItem item)
    {
        items.add(item);
    }

    public List<ShopItem> getItems()
    {
        return items;
    }
}

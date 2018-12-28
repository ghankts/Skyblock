package me.lewis.skyblock.shop;

import me.lewis.skyblock.utils.BlockyData;
import org.apache.commons.lang.WordUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ShopItem
{
    public String icon;
    public double buy;
    public double sell;

    public ShopItem(String icon, double buy, double sell)
    {
        this.icon = icon;
        this.buy = buy;
        this.sell = buy * sell;
    }

    public ItemStack getIcon()
    {
        BlockyData blockyData = BlockyData.convertFromString(icon, false);
        ItemStack icon =  new ItemStack(blockyData.getMaterial(), 1, blockyData.getDurability());
        ItemMeta iconm = icon.getItemMeta();
        iconm.setDisplayName(WordUtils.capitalize(blockyData.getMaterial().toString().replaceAll("_", " ")));
        iconm.setLore(Arrays.asList("Buy: $" + buy, "Sell: $" + sell));
        icon.setItemMeta(iconm);
        return icon;
    }

    public double getPrice()
    {
        return buy;
    }

    public double getSell()
    {
        return sell;
    }
}

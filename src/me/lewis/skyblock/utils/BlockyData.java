package me.lewis.skyblock.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class BlockyData
{
    public Material material;
    public short durability;
    public int[] coords;

    public BlockyData(Material material, int durability)
    {
        this.material = material;
        this.durability = (short) durability;
        coords = new int[3];
    }

    public void setMaterial(Material material)
    {
        this.material = material;
    }

    public Material getMaterial()
    {
        return material;
    }

    public void setDurability(int durability)
    {
        this.durability = (short) durability;
    }

    public short getDurability()
    {
        return durability;
    }

    public void setCoords(int x, int y, int z)
    {
        coords[0] = x;
        coords[1] = y;
        coords[2] = z;
    }

    public int[] getCoords()
    {
        return coords;
    }

    public static BlockyData fromBlock(Block block)
    {
        ItemStack item = block.getState().getData().toItemStack();
        return new BlockyData(item.getType(), item.getDurability());
    }

    public static String convertToString(BlockyData blockyData, boolean coords)
    {
        String string = blockyData.toString();
        if(coords) string += "|" + blockyData.getCoords()[0] + "," + blockyData.getCoords()[1] + "," + blockyData.getCoords()[2];
        return string;
    }

    public static BlockyData convertFromString(String string, boolean coords)
    {
        String block = coords ? string.split("\\|")[0] : string;
        Material material;
        int durability = 0;
        if(block.contains(":"))
        {
            material = Material.getMaterial(block.split(":")[0]);
            durability = Integer.parseInt(block.split(":")[1]);
        }
        else material = Material.getMaterial(block);
        BlockyData blockyData = new BlockyData(material, durability);
        if(coords)
        {
            int x = Integer.parseInt(string.split("\\|")[1].split(",")[0]);
            int y = Integer.parseInt(string.split("\\|")[1].split(",")[1]);
            int z = Integer.parseInt(string.split("\\|")[1].split(",")[2]);
            blockyData.setCoords(x, y, z);
        }
        return blockyData;
    }

    @Override
    public String toString()
    {
        return material.toString() + ":" + durability;
    }

    @Override
    public boolean equals(Object object)
    {
        if(object instanceof BlockyData)
        {
            BlockyData blockyData = (BlockyData) object;
            if(toString().equals(blockyData.toString())) return true;
            else return false;
        }
        return false;
    }
}

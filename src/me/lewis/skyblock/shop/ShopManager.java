package me.lewis.skyblock.shop;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.utils.BlockyData;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ShopManager
{
    public Skyblock plugin;
    public String name;
    public int size;
    public int rows;
    public Map<String, ShopCategory> categories;

    public ShopManager(Skyblock plugin)
    {
        this.plugin = plugin;
        categories = new HashMap();
    }

    public void registerConfig()
    {
        File everythingFile = new File(plugin.getDataFolder(), "everything.yml");
        if(!everythingFile.exists())
        {
            YamlConfiguration everythingYaml = YamlConfiguration.loadConfiguration(everythingFile);
            Map<Integer, String> everything = getEVERYTHING();
            for(int order = 0; order < everything.size(); order++) everythingYaml.set("everything." + order, everything.get(order));
            try
            {
                everythingYaml.save(everythingFile);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        File file = new File(plugin.getDataFolder(), "shop.yml");
        if(!file.exists())
        {
            YamlConfiguration fileYaml = YamlConfiguration.loadConfiguration(file);
            fileYaml.set("shop.name", "Shop");
            fileYaml.set("shop.size", 36);
            fileYaml.set("shop.rows", 3);
            fileYaml.set("shop.category.building.icon", new BlockyData(Material.DIAMOND, 0).toString());
            fileYaml.set("shop.category.building.description", "fuck the gays");
            fileYaml.set("shop.category.building.slot", 0);
            fileYaml.set("shop.blocks." + new BlockyData(Material.GRASS, 0).toString() + ".category", "building");
            fileYaml.set("shop.blocks." + new BlockyData(Material.GRASS, 0).toString() + ".buy", 10);
            fileYaml.set("shop.blocks." + new BlockyData(Material.GRASS, 0).toString() + ".sell", 0.6);
            try
            {
                fileYaml.save(file);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        YamlConfiguration fileYaml = YamlConfiguration.loadConfiguration(file);
        name = fileYaml.getString("shop.name");
        size = fileYaml.getInt("shop.size");
        rows = fileYaml.getInt("shop.rows");
        for(String categories : fileYaml.getConfigurationSection("shop.category").getKeys(false))
        {
            String icon = fileYaml.getString("shop.category." + categories + ".icon");
            String description = fileYaml.getString("shop.category." + categories + ".description");
            int slot = fileYaml.getInt("shop.category." + categories + ".slot");
            ShopCategory category = new ShopCategory(icon, categories, description, slot);
            this.categories.put(categories, category);
        }
    }

    public Map<Integer, String> getEVERYTHING()
    {
        Map<Integer, String> everything = new HashMap();
        int order = 0;
        for(Material material : Material.values())
        {
            everything.put(order, new BlockyData(material, 0).toString());
            order++;
        }
        return everything;
    }

    public String getName()
    {
        return name;
    }

    public int getSize()
    {
        return size;
    }

    public int getRows()
    {
        return rows;
    }

    public Map<String, ShopCategory> getCategories()
    {
        return categories;
    }
}

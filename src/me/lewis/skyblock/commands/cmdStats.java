package me.lewis.skyblock.commands;

import me.lewis.skyblock.Skyblock;
import me.lewis.skyblock.stats.StatsManager;
import net.minecraft.server.v1_7_R4.EnumMobType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class cmdStats implements CommandExecutor
{
    public Skyblock plugin;
    public cmdStats (Skyblock plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return false;
        }
        else
        {
            Inventory statsGUI = Bukkit.createInventory(null, 9, ChatColor.GOLD + sender.getName() + "'s Stats");

            Player p = (Player) sender;
            StatsManager statsManager = plugin.getStatsManager();
            List<String> lore = new ArrayList();

            //Player kills
            ItemStack kills = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta killsm = kills.getItemMeta();
            killsm.setDisplayName(ChatColor.GOLD + "Player Kills");
            lore.add(ChatColor.WHITE.toString() + statsManager.getStats(p).getKills());
            killsm.setLore(lore);
            kills.setItemMeta(killsm);
            statsGUI.setItem(0, kills);
            lore.clear();

            //ELO
            ItemStack elo = new ItemStack(Material.SKULL_ITEM);
            ItemMeta elom = elo.getItemMeta();
            elom.setDisplayName(ChatColor.GOLD + "ELO");
            lore.add(ChatColor.WHITE.toString() + statsManager.getStats(p).getElo());
            elom.setLore(lore);
            elo.setItemMeta(elom);
            statsGUI.setItem(2, elo);
            lore.clear();

            //BALANCE
            ItemStack balance = new ItemStack(Material.GOLD_INGOT);
            ItemMeta balancem = balance.getItemMeta();
            balancem.setDisplayName(ChatColor.GOLD + "Cash Money");
            lore.add(ChatColor.WHITE.toString() + "$" + statsManager.getStats(p).getBalance());
            balancem.setLore(lore);
            balance.setItemMeta(balancem);
            statsGUI.setItem(4, balance);
            lore.clear();

            //CREEPER KILLS
            ItemStack ckills = new ItemStack(Material.MONSTER_EGG, 1, (short)50);
            ItemMeta ckillsm = ckills.getItemMeta();
            ckillsm.setDisplayName(ChatColor.GOLD + "Creeper Kills");
            lore.add(ChatColor.WHITE.toString() + p.getStatistic(Statistic.KILL_ENTITY, EntityType.CREEPER));
            ckillsm.setLore(lore);
            ckills.setItemMeta(ckillsm);
            statsGUI.setItem(6, ckills);
            lore.clear();

            //ZOMBIE
            ItemStack zkills = new ItemStack(Material.MONSTER_EGG, 1, (short)54);
            ItemMeta zkillsm = ckills.getItemMeta();
            zkillsm.setDisplayName(ChatColor.GOLD + "Zombie Kills");
            lore.add(ChatColor.WHITE.toString() + p.getStatistic(Statistic.KILL_ENTITY, EntityType.ZOMBIE));
            zkillsm.setLore(lore);
            zkills.setItemMeta(zkillsm);
            statsGUI.setItem(8, zkills);
            lore.clear();

            p.openInventory(statsGUI);
        }
        return false;
    }
}

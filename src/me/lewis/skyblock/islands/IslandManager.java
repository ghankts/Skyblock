package me.lewis.skyblock.islands;

import me.lewis.skyblock.Skyblock;

import java.util.HashMap;
import java.util.UUID;

public class IslandManager
{
    public Skyblock plugin;

    public IslandManager(Skyblock plugin) {this.plugin = plugin;}

    public HashMap<UUID, Island> map = new HashMap<>();
}

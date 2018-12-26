package me.lewis.skyblock.sapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public class Sapi
{
    public Player p;
    public Scoreboard scoreboard;
    public Objective objective;
    public String borders;
    public Map<String, SapiEntry> entries;

    public Sapi(Player p, String title, String borders)
    {
        this.p = p;
        scoreboard = p.getScoreboard();
        objective = scoreboard.getObjective("sapi");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        setTitle(title);
        entries = new LinkedHashMap();
        setBorders(borders);
    }

    public void setTitle(String title)
    {
        objective.setDisplayName(title);
    }

    public void setBorders(String borders)
    {
        this.borders = borders;
        if(entryExists("header")) getEntry("header").update(borders);
        else createEntry("header", borders);
        if(entryExists("footer")) getEntry("footer").update(borders);
        else createEntry("footer", ChatColor.RESET + borders);
    }

    public Scoreboard getScoreboard()
    {
        return scoreboard;
    }

    public Objective getObjective()
    {
        return objective;
    }

    public void createEntry(String key, String entry)
    {
        SapiEntry sapiEntry = new SapiEntry(this, entry, generateEntryTeam(getNextTeamID()));
        entries.put(key, sapiEntry);
        updateEntries();
    }

    public void createUpdateEntry(String key, String entry, String update)
    {
        SapiEntry sapiEntry = new SapiEntry(this, entry, generateEntryTeam(getNextTeamID()), update);
        entries.put(key, sapiEntry);
        updateEntries();
    }

    public void updateEntry(String key, String entry)
    {
        SapiEntry sapiEntry = getEntry(key);
        sapiEntry.update(entry);
        updateEntries();
    }

    public void removeEntry(String key)
    {
        SapiEntry sapiEntry = getEntry(key);
        sapiEntry.remove();
        entries.remove(key);
        sapiEntry.setScore(sapiEntry.getScore().getScore());
    }

    public SapiEntry getEntry(String key)
    {
        return entries.get(key);
    }

    public boolean entryExists(String key)
    {
        return entries.containsKey(key);
    }

    public void updateEntries()
    {
        if(entryExists("header")) getEntry("header").setScore(entries.size());
        int next = entries.size() - 1;
        List<String> keys = new ArrayList(entries.keySet());
        keys.remove("header");
        keys.remove("footer");
        for(String key : keys)
        {
            SapiEntry entry = getEntry(key);
            fixDuplicate(key);
            entry.setScore(next);
            next--;
        }
        if(entryExists("footer")) getEntry("footer").setScore(1);
    }

    public int getNextScore()
    {
        return entries.size() - 1;
    }

    public String generateEntryTeam(int entry)
    {
        return "sapi-" + entry;
    }

    public int getNextTeamID()
    {
        return entries.size() + 1;
    }

    public void fixDuplicate(String key)
    {
        for(String keys : entries.keySet())
        {
            if(!key.equals(keys))
            {
                SapiEntry fix = getEntry(key);
                SapiEntry entry = getEntry(keys);
                if(fix.getFixedEntry().equalsIgnoreCase(entry.getFixedEntry()))
                {
                    fix.setEntry(ChatColor.RESET + fix.getEntry());
                    break;
                }
            }
        }
    }
}
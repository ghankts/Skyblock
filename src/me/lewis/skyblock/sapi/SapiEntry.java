package me.lewis.skyblock.sapi;

import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SapiEntry
{
    public Sapi sapi;
    public Scoreboard scoreboard;
    public Team team;
    public String entry;
    public String id;
    public String update;

    public SapiEntry(Sapi sapi, String entry, String id)
    {
        this.sapi = sapi;
        this.scoreboard = sapi.getScoreboard();
        this.entry = entry;
        this.id = id;
        update = null;
        apply();
    }

    public SapiEntry(Sapi sapi, String entry, String id, String update)
    {
        this.sapi = sapi;
        this.scoreboard = sapi.getScoreboard();
        this.entry = entry;
        this.id = id;
        this.update = update;
        apply();
    }

    public void setEntry(String entry)
    {
        this.entry = entry;
    }

    public String getEntry()
    {
        return entry;
    }

    public void apply()
    {
        createTeam();
        team.setPrefix(getFixedPrefix());
        if(isUpdateEntry()) team.setSuffix(getUpdate());
        else team.setSuffix(getFixedSuffix());
        team.addEntry(getFixedEntry());
    }

    public void update(String entry)
    {
        if(isUpdateEntry())
        {
            team.setSuffix(entry);
            return;
        }
        if(getEntry().equals(entry)) return;
        remove();
        setEntry(entry);
        apply();
    }

    public void remove()
    {
        sapi.getScoreboard().resetScores(getScore().getEntry());
        team.unregister();
    }

    public void setScore(int score)
    {
        getScore().setScore(score);
    }

    public Score getScore()
    {
        return sapi.getObjective().getScore(getFixedEntry());
    }

    public void createTeam()
    {
        team = sapi.getScoreboard().registerNewTeam(id);
    }

    public String getFixedPrefix()
    {
        String prefix = "";
        if(getEntry().length() > 16) prefix = getEntry().substring(0, 16);
        return prefix;
    }

    public String getFixedEntry()
    {
        String entry = getEntry();
        if(getEntry().length() > 16)
        {
            int limit = (getEntry().length() >= 32) ? 32 : getEntry().length();
            entry = getEntry().substring(16, limit);
        }
        return entry;
    }

    public String getFixedSuffix()
    {
        String suffix = "";
        if(getEntry().length() > 32) suffix = getEntry().substring(32, getEntry().length());
        return suffix;
    }

    public String getUpdate()
    {
        return update;
    }

    public boolean isUpdateEntry()
    {
        if(update != null) return true;
        else return false;
    }
}
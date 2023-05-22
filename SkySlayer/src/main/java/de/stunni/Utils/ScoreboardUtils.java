package de.stunni.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardUtils {


    public static void setScoreboard(Player p) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("SkySlayer", "dummy", "§6§lSky§e§lSlayer");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team empty = scoreboard.registerNewTeam("empty");
        empty.addEntry(" ");
        Score scoreEmpty = objective.getScore(ChatColor.RESET.toString());
        Score scoreEmpty2 = objective.getScore(ChatColor.RESET.toString() + ChatColor.RESET.toString());
        Score scoreEmpty3 = objective.getScore(ChatColor.RESET.toString() + ChatColor.RESET.toString() + ChatColor.RESET.toString());
        Score scoreEmpty4 = objective.getScore(ChatColor.RESET.toString() + ChatColor.RESET.toString() + ChatColor.RESET.toString() + ChatColor.RESET.toString());
        Score scoreEmpty5 = objective.getScore(ChatColor.RESET.toString() + ChatColor.RESET.toString() + ChatColor.RESET.toString() + ChatColor.RESET.toString() + ChatColor.RESET.toString());
        scoreEmpty.setScore(0);
        scoreEmpty2.setScore(3);
        scoreEmpty3.setScore(6);
        scoreEmpty4.setScore(9);
        scoreEmpty5.setScore(12);

        Team tokens = scoreboard.registerNewTeam("tokens");
        tokens.addEntry("§b§lTokens");
        Score scoreTokens1 = objective.getScore("§b§lTokens");
        Score scoreTokens = objective.getScore("§7{tokens}");
        scoreTokens1.setScore(8);
        scoreTokens.setScore(7);

        Team event = scoreboard.registerNewTeam("event");
        event.addEntry("§9§lEvent");
        Score scoreEvent = objective.getScore("§9§lEvent");
        Score scoreEvent1 = objective.getScore("§7Samstag 20:00");
        scoreEvent.setScore(5);
        scoreEvent1.setScore(4);

        Team online = scoreboard.registerNewTeam("online");
        online.addEntry("§c§lOnline");
        Score onlineScore = objective.getScore("§c§lOnline");
        Score onlineScore1 = objective.getScore("§7" + Bukkit.getOnlinePlayers().size());
        onlineScore.setScore(2);
        onlineScore1.setScore(1);

        Team name = scoreboard.registerNewTeam("name");
        name.addEntry("§a§lName");
        Score score1 = objective.getScore("§7" + p.getName());
        Score score2 = objective.getScore("§a§lName");
        score1.setScore(10);
        score2.setScore(11);
        p.setScoreboard(scoreboard);
    }

}

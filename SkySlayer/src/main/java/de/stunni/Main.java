package de.stunni;

import de.stunni.Utils.ScoreboardUtils;
import de.stunni.commands.*;
import de.stunni.listeners.AsyncChat;
import de.stunni.listeners.InventoryClickTroll;
import de.stunni.listeners.Join;
import de.stunni.listeners.KeepWeather;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static final Component header = MiniMessage.miniMessage().deserialize("\n<rainbow><bold>STUNNI TEST SERVER\n");
    public static final Component footer = MiniMessage.miniMessage().deserialize("\n<green>Du bist derzeit auf dem Test Server von <gradient:blue:red><bold>stunni\n");
    public static final String prefix = "<dark_gray>[<rainbow>STUNNI<dark_gray>]<green> ";

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        registerCommand("test", new TestCommand());
        registerCommand("gm", new Gamemode());
        registerCommand("giveitem", new ItemStackTest());
        registerCommand("vbucks", new FreeVBucks());
        registerCommand("repair", new Repair());
        registerCommand("help", new HelpCommand());

        pm.registerEvents(new Join(), this);
        pm.registerEvents(new KeepWeather(), this);
        pm.registerEvents(new AsyncChat(), this);
        pm.registerEvents(new InventoryClickTroll(), this);



        int maxPlayers = 69420;
        Bukkit.setMaxPlayers(maxPlayers);
        for(Player p : Bukkit.getOnlinePlayers()){
            Audience audience = p;
            audience.sendPlayerListHeaderAndFooter(header, footer);
        }
        for(Player p : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.setScoreboard(p);
        }
    }



    public void registerCommand(String command, CommandExecutor executor){
        getCommand(command).setExecutor(executor);
    }

    @Override
    public void onDisable() {
    }
}


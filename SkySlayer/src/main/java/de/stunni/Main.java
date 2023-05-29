package de.stunni;

import de.stunni.Utils.ScoreboardUtils;
import de.stunni.commands.*;
import de.stunni.listeners.*;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    public static Component header = MiniMessage.miniMessage().deserialize("\n<rainbow><bold>STUNNI TEST SERVER\n");
    public static Component footer = MiniMessage.miniMessage().deserialize("\n<green>Du bist derzeit auf dem Test Server von <gradient:blue:red><bold>stunni\n");
    public static String prefix = "<dark_gray>[<rainbow>STUNNI<dark_gray>]<green> ";
    public static Component argsError = MiniMessage.miniMessage().deserialize(prefix+"<color:red>Unzulässige Argumente bzw. Argumentenlänge");
    public static Component notPlayer = MiniMessage.miniMessage().deserialize(prefix+"<color:red>Du musst ein Spieler sein um diesen Command ausführen zu können");
    public static Component noPerms = MiniMessage.miniMessage().deserialize(prefix+"<color:red>Du hast keine Berechtigung für diesen Command");

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        registerCommand("test", new TestCommand());
        registerCommand("gm", new Gamemode());
        registerCommand("giveitem", new ItemStackTest());
        registerCommand("vbucks", new FreeVBucks());
        registerCommand("repair", new Repair());
        registerCommand("help", new HelpCommand_OUT());
        registerCommand("setshop", new SetShop());
        registerCommand("feed", new Feed());
        registerCommand("heal", new Heal());
        registerCommand("tp", new Teleport());
        registerCommand("tpall", new Teleport());
        registerCommand("tphere", new Teleport());
        registerCommand("editsign", new EditSign());
        registerCommand("werbung", new Werbung());

        pm.registerEvents(new Join(), this);
        pm.registerEvents(new KeepWeather(), this);
        pm.registerEvents(new AsyncChat(), this);
        pm.registerEvents(new InventoryClickTroll(), this);
        pm.registerEvents(new ShopEvent(), this);



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

    public static Main getInstance(){
        if(instance == null){
            instance = new Main();
        }
        return instance;
    }

    @Override
    public void onDisable() {
    }
}


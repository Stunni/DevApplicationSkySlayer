package de.stunni.commands;

import de.stunni.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Gamemode implements CommandExecutor {

    public Component message(GameMode gm){
        return MiniMessage.miniMessage().deserialize(Main.prefix+"<color:green>Du bist nun im <color:blue>"+gm);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            String helpMessage = "§cCommand Usage: §e/gm §a<gamemode(0,1,2,3)>";
            if (p.hasPermission("stunni.gamemode")) {
                if (args.length == 0) {
                    p.sendMessage(helpMessage);
                } else if (args.length == 1) {
                    switch (args[0]) {
                        case "0" -> {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(message(p.getGameMode()));
                        }
                        case "1" -> {
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(message(p.getGameMode()));
                        }
                        case "2" -> {
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(message(p.getGameMode()));
                        }
                        case "3" -> {
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(message(p.getGameMode()));
                        }
                        case "survival" -> {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(message(p.getGameMode()));
                        }
                        case "creative" -> {
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(message(p.getGameMode()));
                        }
                        case "adventure" -> {
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(message(p.getGameMode()));
                        }
                        case "spectator" -> {
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(message(p.getGameMode()));
                        }
                        default -> p.sendMessage(helpMessage);
                    }


                }
            } else p.sendMessage(helpMessage);
        }
        return false;
    }

}
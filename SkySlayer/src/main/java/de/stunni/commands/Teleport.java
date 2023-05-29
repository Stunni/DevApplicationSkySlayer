package de.stunni.commands;

import de.stunni.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Teleport implements CommandExecutor {
    public Component playerNull = MiniMessage.miniMessage().deserializeOrNull(Main.prefix + "<color:red>Dieser Spieler existiert nicht oder ist offline");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("stunni.tp")) {
                if (label.equalsIgnoreCase("tp")) {
                    if (args.length == 0)p.sendMessage(Main.argsError);
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            p.teleport(target.getLocation());
                            p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "Du wurdest zu " + args[0] + " teleportiert"));
                        } else p.sendMessage(playerNull);
                    } else if (args.length == 2) {
                        Player target1 = Bukkit.getPlayer(args[0]);
                        Player target2 = Bukkit.getPlayer(args[1]);
                        if (target1 != null && target2 != null) {
                            target1.teleport(target2.getLocation());
                            p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "Du hast " + args[0] + " zu " + args[1] + " teleportiert"));
                        } else p.sendMessage(playerNull);
                    } else p.sendMessage(Main.argsError);
                }else if(label.equalsIgnoreCase("tphere")){
                    if(args.length == 0){
                        p.sendMessage(Main.argsError);
                    }else if(args.length == 1){
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            target.teleport(p.getLocation());
                            p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "Du hast " + args[0] + " zu dir teleportiert"));
                        } else p.sendMessage(playerNull);
                    }else p.sendMessage(Main.argsError);
                }else if(label.equalsIgnoreCase("tpall")){
                    if(args.length == 0){
                        for(Player all : Bukkit.getOnlinePlayers()){
                            all.teleport(p.getLocation());
                            p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix+"Du hast alle Spieler zu dir teleportiert"));
                        }
                    }
                }
            }
        }
        return false;
    }
}

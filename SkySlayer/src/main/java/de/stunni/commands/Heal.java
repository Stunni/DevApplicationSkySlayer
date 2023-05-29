package de.stunni.commands;

import de.stunni.Main;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("stunni.heal")) {
                if (args.length == 0) {
                    if (p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {
                        if (p.getHealth() != 20) {

                            p.setHealth(20);
                            p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "Du wurdest geheilt"));

                        } else p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "<color:red>Du kannst momentan nicht geheilt werden. <dark_red>(schon voll)"));
                    } else p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "<color:red>Du kannst momentan nicht geheilt werden. <dark_red>(gamemode)"));
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target.getGameMode().equals(GameMode.SURVIVAL) || target.getGameMode().equals(GameMode.ADVENTURE)) {
                            if (target.getHealth() != 20) {

                                target.setHealth(20);
                                target.sendMessage(MiniMessage.miniMessage().deserialize("<color:green>Du wurdest geheilt"));
                                p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "Du hast " + args[0] + " geheilt"));

                            } else p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "<color:red>Dieser Spieler kann momentan nicht geheilt werden. <dark_red>(schon voll)"));
                        } else p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "<color:red>Dieser Spieler kann momentan nicht geheilt werden. <dark_red>(gamemode)"));
                    } else p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "<color:red>Dieser Spieler existiert nicht oder ist offline"));
                } else p.sendMessage(Main.argsError);
            } else p.sendMessage(Main.noPerms);
        } else sender.sendMessage(Main.notPlayer);
        return false;
    }
}

package de.stunni.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HelpCommand_OUT implements CommandExecutor {
    private final Component helpMessage = MiniMessage.miniMessage().deserialize("\n<dark_gray>[<rainbow>STUNNI PLUGIN<dark_gray>]\n" +
            "<color:gold>Help\n<gradient:yellow:gold>/test\n<gradient:yellow:green>/gm | /gamemode\n<gradient:yellow:gold>/vbucks\n<gradient:yellow:gold>/giveitem\n<gradient:yellow:gold>/repair\n<dark_gray>[<rainbow>STUNNI PLUGIN<dark_gray>]<green>\n");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) {
                System.out.println(1);
                p.sendMessage(helpMessage);

            }
        }
        return false;
    }
}

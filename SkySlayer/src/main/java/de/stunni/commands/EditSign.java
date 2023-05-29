package de.stunni.commands;

import de.stunni.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EditSign implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("stunni.editsign")) {
                Block targetBlock = p.getTargetBlock(null, 5);
                if (targetBlock.getState() instanceof Sign sign) {
                    if (args.length >= 1) {
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args.length >= 3) {
                                try {
                                    int line = Integer.parseInt(args[1]) - 1;

                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (int i = 2; i < args.length; i++) {
                                        stringBuilder.append(args[i]).append(" ");
                                    }

                                    Component newText = MiniMessage.miniMessage().deserialize(stringBuilder.toString().trim());
                                    String newTextString = PlainTextComponentSerializer.plainText().serialize(newText);
                                    if (!(newTextString.length() > 15)) {
                                        sign.line(line, newText);
                                        sign.update();
                                        p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "Du hast das Schild bearbeitet"));
                                    } else {
                                        p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "<color:red>Dein Text ist zu lang und passt nicht auf das Schild. Kürze ihn"));
                                        return true;
                                    }
                                }catch(NumberFormatException e){
                                    p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix+"<color:red>Die Line muss eine Zahl sein"));
                                }
                            }else p.sendMessage(Main.argsError);
                        } else if (args[0].equalsIgnoreCase("clear")) {
                            if (args.length == 1) {
                                for (int i = 0; i < 4; i++) {
                                    sign.line(i, Component.empty());
                                }
                                sign.update();
                                p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "Du hast das Schild gecleared"));
                            } else p.sendMessage(Main.argsError);
                        } else p.sendMessage(Main.argsError);
                    } else p.sendMessage(Main.argsError);
                } else {
                    p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "<color:red>Du musst ein Schild angucken"));
                    return true;
                }
            } else p.sendMessage(Main.noPerms);
        } else sender.sendMessage(Main.notPlayer);
        return false;
    }
}

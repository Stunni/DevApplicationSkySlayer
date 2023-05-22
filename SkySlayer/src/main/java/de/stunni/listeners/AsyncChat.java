package de.stunni.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AsyncChat implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncChatEvent e) {
        Component message = e.message();
        String messageString = PlainTextComponentSerializer.plainText().serialize(message);
        Component convertedMessage = MiniMessage.miniMessage().deserialize(messageString);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            String name = onlinePlayer.getName();
            Component formattedName = MiniMessage.miniMessage().deserialize("<color:dark_gray>@<color:aqua>" + name);
            convertedMessage = convertedMessage.replaceText(TextReplacementConfig.builder()
                    .match("\\b" + name + "\\b")
                    .replacement(formattedName)
                    .build());
        }

        e.message(convertedMessage);
    }
}

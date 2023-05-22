package de.stunni.listeners;

import de.stunni.Main;
import de.stunni.Utils.ScoreboardUtils;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String pName = p.getName();

        Component nameGradient = MiniMessage.miniMessage().deserialize("<gradient:red:blue>"+pName);
        p.displayName(nameGradient);
        p.playerListName(nameGradient);

        Component joinMessage = MiniMessage.miniMessage().deserialize("<gradient:yellow:red>Willkommen <name>", Placeholder.component("name", nameGradient));
        e.joinMessage(joinMessage);

        ScoreboardUtils.setScoreboard(p);

        Audience audience = p;
        audience.sendPlayerListHeaderAndFooter(Main.header, Main.footer);
    }
}

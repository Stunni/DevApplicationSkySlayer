package de.stunni.listeners;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class KeepWeather implements Listener {

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent e) {
        checkWeather();
    }

    public void checkWeather() {
        World w = Bukkit.getWorlds().get(0);
        if (!w.isClearWeather()) {
            w.setClearWeatherDuration(100000);
            Bukkit.broadcastMessage("§aDas Wetter wurde auf §eclear§a gesetzt");
        }
    }

}


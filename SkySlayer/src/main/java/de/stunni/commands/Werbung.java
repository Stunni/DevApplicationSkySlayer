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

import java.io.*;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Werbung implements CommandExecutor {
    private static final String folderName = "Stunni";
    private static final String filePath = "plugins" + File.separator + folderName + File.separator + "cooldowns.txt";
    private static final long werbungCooldown = 15;

    private Map<String, LocalDateTime> cooldowns;

    public Werbung() {
        cooldowns = new HashMap<>();
        loadCooldowns();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("stunni.werbung")) {
                if (args.length == 0) {
                    p.sendMessage(Main.argsError);
                }

                String playerName = p.getName();
                LocalDateTime lastUsageTime = cooldowns.get(playerName);

                if (lastUsageTime != null && !hasCooldownExpired(lastUsageTime)) {
                    p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix+"<color:red>Bitte warte noch bevor du den Command ausführst"));
                    return true;
                }

                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i < args.length; i++) {
                    stringBuilder.append(args[i]).append(" ");
                }

                Component werbungText = MiniMessage.miniMessage().deserialize(
                                "<color:dark_gray>)<st>-------</st><color:yellow><b> WERBUNG </color><color:dark_gray><st>-------</st>(\n\n" +
                                "<color:yellow> " + playerName + " <color:dark_gray>»  <color:gray>" + stringBuilder.toString().trim() +
                                "\n\n<color:dark_gray>)<st>-------</st><color:yellow><b> WERBUNG </color><color:dark_gray><st>-------</st>(");
                Bukkit.broadcast(werbungText);

                updateCooldown(playerName);
                saveCooldowns();

                return true;
            }
        }

        return false;
    }

    private boolean hasCooldownExpired(LocalDateTime lastUsageTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(lastUsageTime, currentTime);
        return duration.toMinutes() >= werbungCooldown;
    }

    private void updateCooldown(String playerName) {
        cooldowns.put(playerName, LocalDateTime.now());
    }

    private void loadCooldowns() {
        createPluginFolder();

        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    LocalDateTime lastUsageTime = LocalDateTime.parse(parts[1]);
                    cooldowns.put(playerName, lastUsageTime);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCooldowns() {
        createPluginFolder();

        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, LocalDateTime> entry : cooldowns.entrySet()) {
                String playerName = entry.getKey();
                LocalDateTime lastUsageTime = entry.getValue();
                String line = playerName + ":" + lastUsageTime.toString();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPluginFolder() {
        File pluginDir = new File("plugins/" + folderName);
        if (!pluginDir.exists()) {
            try {
                Files.createDirectories(pluginDir.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("folder gibts schon mensch");
            }
        }
    }
}


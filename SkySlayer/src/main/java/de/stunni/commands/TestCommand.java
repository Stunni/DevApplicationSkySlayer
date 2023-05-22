package de.stunni.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            Player p = (Player) s;
            if (p.isOp()) {
//                ItemStack is = new ItemStack(Material.DIAMOND_BLOCK);
//                ItemMeta meta = is.getItemMeta();
//                meta.setDisplayName("§b§ltest item");
//                is.setItemMeta(meta);
//                p.getInventory().addItem(is);
//                p.sendMessage("§aDu hast das Item erhalten"+test);
                Component test = MiniMessage.miniMessage().deserialize("<color:dark_gray>@<color:aqua>test");
                p.sendMessage(test);




//                Component nameGradient = MiniMessage.miniMessage().deserialize("<gradient:blue:red>"+name);
//                p.sendMessage(nameGradient);
//                p.displayName(nameGradient);
            }
        }
        return false;
    }
}

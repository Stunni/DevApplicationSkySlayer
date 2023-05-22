package de.stunni.commands;

import de.stunni.Main;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ItemStackTest implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {

            if (args.length < 3) {
                p.sendMessage("§cUsage: /giveitem <item> <name> <amount>");
                return true;
            }

            String itemName = args[0];
            StringBuilder nameBuilder = new StringBuilder();

            // Build the item name from the arguments
            for (int i = 1; i < args.length - 1; i++) {
                nameBuilder.append(args[i]).append(" ");
            }

            String itemDisplayName = args[1];

            int amount;
            try {
                amount = Integer.parseInt(args[args.length - 1]);
            } catch (NumberFormatException e) {
                p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix+"Deine Anzahl Angabe ist falsch"));
                return true;
            }

            Material material = Material.matchMaterial(itemName);

            if (material == null) {
                p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix+"<red>Das Item existiert nicht"));
                return true;
            }

            ItemStack itemStack = new ItemStack(material, amount);
            ItemMeta itemMeta = itemStack.getItemMeta();

            if (itemMeta != null) {
                itemMeta.displayName(MiniMessage.miniMessage().deserialize(itemDisplayName));
                itemStack.setItemMeta(itemMeta);
            }

            p.getInventory().addItem(itemStack);
            p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix+"Dir wurde <yellow>"+amount+"x<white><italic> "+itemName+"<reset><green> mit dem Namen <reset>"+ itemDisplayName +"<green> gegeben."));
        }
        return true;
    }

}

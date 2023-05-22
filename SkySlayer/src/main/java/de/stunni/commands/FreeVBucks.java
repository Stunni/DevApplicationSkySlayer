package de.stunni.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FreeVBucks implements CommandExecutor {
    public static Inventory inv;
    public static String test = "§bDu willst free V-Bucks?";
    public static String name = "Free V-Bucks";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            inv = Bukkit.createInventory(null, 4*9, name);
            List<String> lore = new ArrayList<>();
            ItemStack item = new ItemStack(Material.RAW_GOLD_BLOCK);
            ItemMeta meta = item.getItemMeta();
            Component itemName = MiniMessage.miniMessage().deserialize("<gradient:gold:red>Free V-Bucks");
            meta.displayName(itemName);
            meta.addEnchant(Enchantment.LUCK, 99999, true);
            meta.setUnbreakable(true);
            lore.add(" ");
            lore.add(test);
            lore.add("§aDann klicke auf dieses Item!");
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);

            for (int i = 0; i < inv.getSize(); i++) {
                inv.setItem(i, item);
            }
            p.openInventory(inv);
        } else if (args.length == 1) {
            List<String> lore = new ArrayList<>();
            String sizeRaw = args[0];
            String[] sizes = sizeRaw.split("\\*");
            String sizeRows1 = sizes[0];
            String sizeSlots1 = sizes[1];
            int sizeRows = Integer.parseInt(sizeRows1);
            int sizeSlots = Integer.parseInt(sizeSlots1);
            inv = Bukkit.createInventory(null, sizeRows * sizeSlots, name);
            p.sendMessage("§aDu hast ein Inventar erstellt, mit der §eGröße: " + sizeRows + "*" + sizeSlots + " §aund dem §eNamen: " + name);

            ItemStack item = new ItemStack(Material.RAW_GOLD_BLOCK);
            ItemMeta meta = item.getItemMeta();
            Component itemName = MiniMessage.miniMessage().deserialize("<gradient:gold:red>Free V-Bucks");
            meta.displayName(itemName);
            meta.addEnchant(Enchantment.LUCK, 99999, true);
            meta.setUnbreakable(true);
            lore.add(" ");
            lore.add(test);
            lore.add("§aDann klicke auf dieses Item!");
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);

            for (int i = 0; i < inv.getSize(); i++) {
                inv.setItem(i, item);
            }
            p.openInventory(inv);
        }
        return false;
    }
}

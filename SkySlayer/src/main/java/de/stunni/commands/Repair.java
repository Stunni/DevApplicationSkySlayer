package de.stunni.commands;

import de.stunni.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.Repairable;
import org.jetbrains.annotations.NotNull;

public class Repair implements CommandExecutor {
    private final Component notRepairable = MiniMessage.miniMessage().deserialize(Main.prefix + "<color:red>Das Item ist nicht reparierbar");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("stunni.repair")) {
                if (args.length == 0) {
                    if (!(p.getInventory().getItemInMainHand().getType() == Material.AIR)) {
                        ItemStack item = p.getInventory().getItemInMainHand();
                        Damageable damage = (Damageable) item.getItemMeta();
                        Repairable repairable = (Repairable) item.getItemMeta();
                        if (damage.getDamage() != 0 && !repairable.hasRepairCost()) {
                            item.editMeta(Damageable.class, e -> e.setDamage(0));
                            int slot = p.getInventory().getHeldItemSlot();
                            p.getInventory().setItem(slot, item);
                            p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "Das Item in deiner Hand wurde repariert"));
                        } else p.sendMessage(notRepairable);
                    } else p.sendMessage(notRepairable);
                }
            }
        }
        return false;
    }
}

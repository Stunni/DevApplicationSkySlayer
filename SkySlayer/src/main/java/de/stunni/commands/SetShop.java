package de.stunni.commands;

import de.stunni.Main;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;

public class SetShop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player p){
            if(p.hasPermission("stunni.setshop")){
                if(args.length == 0){
                    Location loc = p.getLocation();
                    Villager shop = (Villager) p.getWorld().spawnEntity(loc, EntityType.VILLAGER);
                    shop.setAI(false);
                    shop.setRemoveWhenFarAway(false);
                    shop.customName(MiniMessage.miniMessage().deserialize("<gradient:gold:red>SHOP"));
                    shop.setCustomNameVisible(true);
                    shop.setVillagerType(Villager.Type.PLAINS);
                    p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix+"Du hast erfolgreich einen Shop Villager an deiner Position gespawned."));
                }
            }
        }
        return false;
    }
}

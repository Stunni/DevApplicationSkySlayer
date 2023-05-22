package de.stunni.listeners;

import de.stunni.commands.FreeVBucks;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickTroll implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getInventory().equals(FreeVBucks.inv)) {
            e.setCancelled(true);
            if(e.getCurrentItem().getItemMeta().getLore().contains(FreeVBucks.test)){
                ItemStack dirt = new ItemStack(Material.DIRT);
                e.setCurrentItem(dirt);
                e.getWhoClicked().sendMessage("§bget scammed L");
            }
        }
    }

}

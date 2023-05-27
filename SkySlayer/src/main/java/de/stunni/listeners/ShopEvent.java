package de.stunni.listeners;

import de.stunni.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShopEvent implements Listener {

    public Component name = MiniMessage.miniMessage().deserialize("<rainbow>SKYSLAYER SHOP");
    public Inventory inventory = Bukkit.createInventory(null, 5 * 9, name);
    public ItemStack shop_url_item = new ItemStack(Material.CHEST);

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Villager villager) {
            Player p = e.getPlayer();

            if (villager.name().equals(MiniMessage.miniMessage().deserialize("<gradient:gold:red>SHOP"))) {
                p.openInventory(inventory);
                ItemStack background = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta backgroundItemMeta = background.getItemMeta();
                backgroundItemMeta.displayName(MiniMessage.miniMessage().deserialize(""));
                background.setItemMeta(backgroundItemMeta);


                ItemMeta shopUrlItemItemMeta = shop_url_item.getItemMeta();
                Component itemName = MiniMessage.miniMessage().deserialize("<bold><color:gold>Sky<color:yellow>Slayer</bold> <rainbow>Shop");
                shopUrlItemItemMeta.displayName(itemName.decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE));

                List<Component> lore = new ArrayList<>();
                lore.add(MiniMessage.miniMessage().deserialize("<color:green>Klicke hier um den <color:gold>Sky<color:yellow>Slayer<color:green> Shop zu öffnen"));

                List<Component> decoratedLore = new ArrayList<>();
                for (Component component : lore) {
                    decoratedLore.add(component.decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE));
                }

                shopUrlItemItemMeta.lore(decoratedLore);
                shop_url_item.setItemMeta(shopUrlItemItemMeta);
                for (int i = 0; i < 54; i++) {
                    if (i != 22) {
                        inventory.setItem(i, background);
                        inventory.setItem(22, shop_url_item);
                    }
                }




                p.sendMessage(MiniMessage.miniMessage().deserialize(Main.prefix + "Du hast den Shop geöffnet"));
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Villager villager) {
            if (villager.name().equals(MiniMessage.miniMessage().deserialize("<gradient:gold:red>SHOP"))) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
            if(e.getCurrentItem().equals(shop_url_item)){
                String shop_site = "https://shop.skyslayer.org";
                Component URL = MiniMessage.miniMessage().deserialize(Main.prefix+"<hover:show_text:'<yellow>shop.skyslayer.org'><color:green>Klicke hier um den Shop zu öffnen!").clickEvent(ClickEvent.openUrl(shop_site));
                p.sendMessage(URL);
            }
        }
    }

}

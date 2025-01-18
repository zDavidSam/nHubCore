package dev.nexus.operations.listeners;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.model.InventoryPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    private final HubCore plugin;

    public InventoryListener(HubCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked();
        InventoryPlayer inventoryPlayer = plugin.getInventoryManager().getInventoryPlayer(player);
        if(inventoryPlayer != null){
            event.setCancelled(true);
            if(event.getCurrentItem() != null && event.getClickedInventory() != null && event.getClickedInventory().equals(player.getOpenInventory().getTopInventory())){
                plugin.getInventoryManager().onInventoryClick(inventoryPlayer, event.getSlot(), event.getClick());
            }
        }
    }
    @EventHandler
    public void onInventoryClick2(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked();
        InventoryPlayer inventoryPlayer = plugin.getCosmeticsInvManager().getInventoryPlayer(player);
        if(inventoryPlayer != null){
            event.setCancelled(true);
            if(event.getCurrentItem() != null && event.getClickedInventory() != null && event.getClickedInventory().equals(player.getOpenInventory().getTopInventory())){
                plugin.getCosmeticsInvManager().onInventoryClick(inventoryPlayer, event.getSlot(), event.getClick());
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player)event.getPlayer();
        plugin.getInventoryManager().removePlayer(player);
        plugin.getCosmeticsInvManager().removePlayer(player);
    }
}

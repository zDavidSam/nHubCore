package dev.nexus.operations.managers.cosmetics;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.model.InventoryPlayer;
import dev.nexus.operations.model.InventorySection;
import dev.nexus.operations.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CosmeticsInvManager {
    private ArrayList<InventoryPlayer> players;
    private HubCore plugin;


    public CosmeticsInvManager(HubCore plugin) {
        this.plugin = plugin;
        this.players = new ArrayList<>();
    }
    public InventoryPlayer getInventoryPlayer(Player player) {
        for (InventoryPlayer inventoryPlayer : players) {
            if (inventoryPlayer.getPlayer().equals(player)) {
                return inventoryPlayer;
            }
        }
        return null;
    }

    public void removePlayer(Player player) {
        players.removeIf(inventoryPlayer -> inventoryPlayer.getPlayer().equals(player));
    }

    public void openMenuCosmetics(InventoryPlayer inventoryPlayer){
        inventoryPlayer.setInventorySection(InventorySection.Menu_Cosmetics);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 27, MessageUtils.getColoredMessage("&eCosmetics Selector"));
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        int[] position = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        for (int pos : position) {
            inv.setItem(pos, item);
        }

        item = new ItemStack(Material.BLAZE_ROD);
        meta = item.getItemMeta();
        meta.setDisplayName((MessageUtils.getColoredMessage("&aParticle Menu")));
        item.setItemMeta(meta);
        inv.setItem(11, item);

        item = new ItemStack(Material.GREEN_BANNER);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&eMenu Banners"));
        inv.setItem(14, item);

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void openParticleMenu(InventoryPlayer inventoryPlayer){
        inventoryPlayer.setInventorySection(InventorySection.Menu_Particle);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 27, MessageUtils.getColoredMessage("&eParticle Selector"));
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        int[] position = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        for (int pos : position) {
            inv.setItem(pos, item);
        }

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void openBannerMenu(InventoryPlayer inventoryPlayer){
        inventoryPlayer.setInventorySection(InventorySection.Menu_Banners);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 27, MessageUtils.getColoredMessage("&eBanner Menu"));

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void onInventoryClick(InventoryPlayer inventoryPlayer, int slot, ClickType clickType) {
        Player player = inventoryPlayer.getPlayer();
        InventorySection section = inventoryPlayer.getInventorySection();
        if (section.equals(InventorySection.Menu_Cosmetics)) {
            if (slot == 11) {
                openParticleMenu(inventoryPlayer);
            } else if (slot == 14) {
                openBannerMenu(inventoryPlayer);

            }
        }
    }
}

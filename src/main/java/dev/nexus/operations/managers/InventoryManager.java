package dev.nexus.operations.managers;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.model.InventoryPlayer;
import dev.nexus.operations.model.InventorySection;
import dev.nexus.operations.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryManager {
    private final ArrayList<InventoryPlayer> players;
    private final HubCore plugin;
    private final Map<Integer, String> serverSlots;
    private final FileConfiguration selectorConfig;

    public InventoryManager(HubCore plugin) {
        this.plugin = plugin;
        this.players = new ArrayList<>();
        this.serverSlots = new HashMap<>();
        File configFile = new File(plugin.getDataFolder(), "providers/server-selector.yml");
        this.selectorConfig = YamlConfiguration.loadConfiguration(configFile);
    }

    public InventoryPlayer getInventoryPlayer(Player player) {
        return players.stream()
                .filter(ip -> ip.getPlayer().equals(player))
                .findFirst()
                .orElse(null);
    }

    public void removePlayer(Player player) {
        players.removeIf(ip -> ip.getPlayer().equals(player));
    }

    public void openMenuSelector(InventoryPlayer inventoryPlayer) {
        Player player = inventoryPlayer.getPlayer();
        inventoryPlayer.setInventorySection(InventorySection.Menu_Selector);

        Inventory inv = Bukkit.createInventory(null, 27, MessageUtils.getColoredMessage("&cServer Selector"));

        // Fill background
        ItemStack filler = createItem(Material.GRAY_STAINED_GLASS_PANE, " ", List.of());
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, filler);
        }

        // Add server items from configuration
        var serversSection = selectorConfig.getConfigurationSection("servers");
        if (serversSection != null) {
            serverSlots.clear();
            for (String key : serversSection.getKeys(false)) {
                var server = serversSection.getConfigurationSection(key);
                if (server != null) {
                    int slot = server.getInt("slot", -1);
                    if (slot >= 0 && slot < inv.getSize()) {
                        Material material = Material.valueOf(server.getString("material", "GRASS_BLOCK"));
                        String name = server.getString("name", key);
                        List<String> lore = server.getStringList("lore");

                        inv.setItem(slot, createItem(material, name, lore));
                        serverSlots.put(slot, server.getString("server-name", key));
                    }
                }
            }
        }

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    private ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage(name));
        meta.setLore(lore.stream()
                .map(MessageUtils::getColoredMessage)
                .toList());
        item.setItemMeta(meta);
        return item;
    }

    public void onInventoryClick(InventoryPlayer inventoryPlayer, int slot, ClickType clickType) {
        if (inventoryPlayer.getInventorySection() == InventorySection.Menu_Selector) {
            String server = serverSlots.get(slot);
            if (server != null) {
                connectToServer(inventoryPlayer.getPlayer(), server);
            }
        }
    }

    private void connectToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);

            out.writeUTF("Connect");
            out.writeUTF(server);

            player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            player.sendMessage(MessageUtils.getColoredMessage("&aConnecting to " + server + "..."));
        } catch (Exception e) {
            player.sendMessage(MessageUtils.getColoredMessage("&cError connecting to server!"));
            e.printStackTrace();
        }
    }
}
package dev.nexus.operations.proxy;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class menuItemsSelector {

    private final HubCore plugin;

    public menuItemsSelector(HubCore plugin) {
        this.plugin = plugin;
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
}

package dev.nexus.operations.model;

import org.bukkit.entity.Player;

public class InventoryPlayer {
    private Player player;
    private InventorySection inventorySection;

    public InventoryPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public InventorySection getInventorySection() {
        return inventorySection;
    }

    public void setInventorySection(InventorySection inventorySection) {
        this.inventorySection = inventorySection;
    }
}

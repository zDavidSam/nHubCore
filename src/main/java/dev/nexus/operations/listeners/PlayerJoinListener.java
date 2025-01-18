package dev.nexus.operations.listeners;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.utils.SoundManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final SoundManager soundManager;

    public PlayerJoinListener(HubCore plugin) {
        this.soundManager = new SoundManager(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        soundManager.playJoinSound(player);
    }
}
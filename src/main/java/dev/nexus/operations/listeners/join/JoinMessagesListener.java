package dev.nexus.operations.listeners.join;

import dev.nexus.operations.HubCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessagesListener implements Listener {
    private final HubCore plugin;
    private final JoinMessageManager joinMessageManager;
    private final JoinEffectManager joinEffectManager;

    public JoinMessagesListener(HubCore plugin) {
        this.plugin = plugin;
        this.joinMessageManager = new JoinMessageManager(plugin);
        this.joinEffectManager = new JoinEffectManager(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Cancel default join message
        event.setJoinMessage(null);

        // Send custom join message
        joinMessageManager.sendJoinMessage(player);

        // Apply join effects
        joinEffectManager.applyEffects(player);
    }
}
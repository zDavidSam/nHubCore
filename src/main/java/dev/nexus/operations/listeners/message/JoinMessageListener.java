package dev.nexus.operations.listeners.message;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.utils.MessageFormatter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessageListener implements Listener {
    private final MessageFormatter messageFormatter;

    public JoinMessageListener(HubCore plugin) {
        this.messageFormatter = new MessageFormatter(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        String joinMessage = messageFormatter.formatJoinMessage(player);
        player.getServer().broadcastMessage(joinMessage);
    }
}
package dev.nexus.operations.listeners.message;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.utils.MessageFormatter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveMessageListener implements Listener {
    private final MessageFormatter messageFormatter;

    public LeaveMessageListener(HubCore plugin) {
        this.messageFormatter = new MessageFormatter(plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);
        String leaveMessage = messageFormatter.formatLeaveMessage(player);
        player.getServer().broadcastMessage(leaveMessage);
    }
}
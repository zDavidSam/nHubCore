package dev.nexus.operations.listeners.join;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.utils.MessageUtils;
import org.bukkit.entity.Player;
import java.util.List;

public class JoinMessageManager {
    private final HubCore plugin;
    private final MessageUtils messageUtils;

    public JoinMessageManager(HubCore plugin) {
        this.plugin = plugin;
        this.messageUtils = new MessageUtils();
    }

    public void sendJoinMessage(Player player) {
        if (!plugin.getConfig().getBoolean("join-message.enabled", true)) {
            return;
        }

        List<String> messages = plugin.getConfig().getStringList("join-message.lines");

        for (String message : messages) {
            String formattedMessage = MessageUtils.getColoredMessage(message)
                    .replace("%player%", player.getName());
            player.sendMessage(formattedMessage);
        }
    }
}
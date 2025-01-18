package dev.nexus.operations.utils;

import dev.nexus.operations.HubCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageFormatter {
    private final HubCore plugin;

    public MessageFormatter(HubCore plugin) {
        this.plugin = plugin;
    }

    public String formatJoinMessage(Player player) {
        return formatMessage(String.format(plugin.getMainConfigManager().getJoin(), player.getName()));
    }

    public String formatLeaveMessage(Player player) {
        return formatMessage(String.format(plugin.getMainConfigManager().getQuit(), player.getName()));
    }

    public String formatMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
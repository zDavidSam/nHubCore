package dev.nexus.operations.utils;

import org.bukkit.ChatColor;

public class MessageUtils {

    public static String getColoredMessage(String message){

        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public String stripColor(String message) {
        return ChatColor.stripColor(message);
    }
}
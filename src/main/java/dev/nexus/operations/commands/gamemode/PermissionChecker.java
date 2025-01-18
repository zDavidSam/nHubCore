package dev.nexus.operations.commands.gamemode;

import org.bukkit.entity.Player;

public class PermissionChecker {

    private static final String PERMISSION_PREFIX = "nexus.gamemode.";

    public static boolean hasGameModePermission(Player player, String gameMode) {
        return player.hasPermission(PERMISSION_PREFIX + gameMode) ||
                player.hasPermission(PERMISSION_PREFIX + "*");
    }
}
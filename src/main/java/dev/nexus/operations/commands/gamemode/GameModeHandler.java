package dev.nexus.operations.commands.gamemode;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeHandler {

    public boolean setCreative(Player player) {
        if (!PermissionChecker.hasGameModePermission(player, "creative")) {
            player.sendMessage("§cYou don't have permission to use creative mode!");
            return true;
        }

        player.setGameMode(GameMode.CREATIVE);
        player.sendMessage("§aGamemode set to §eCreative§a!");
        return true;
    }

    public boolean setSurvival(Player player) {
        if (!PermissionChecker.hasGameModePermission(player, "survival")) {
            player.sendMessage("§cYou don't have permission to use survival mode!");
            return true;
        }

        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage("§aGamemode set to §eSurvival§a!");
        return true;
    }

    public boolean setAdventure(Player player) {
        if (!PermissionChecker.hasGameModePermission(player, "adventure")) {
            player.sendMessage("§cYou don't have permission to use adventure mode!");
            return true;
        }

        player.setGameMode(GameMode.ADVENTURE);
        player.sendMessage("§aGamemode set to §eAdventure§a!");
        return true;
    }

    public boolean setSpectator(Player player) {
        if (!PermissionChecker.hasGameModePermission(player, "spectator")) {
            player.sendMessage("§cYou don't have permission to use spectator mode!");
            return true;
        }

        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage("§aGamemode set to §eSpectator§a!");
        return true;
    }
}
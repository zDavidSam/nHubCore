package dev.nexus.operations.listeners.jump;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.config.MainConfigManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.HashMap;
import java.util.UUID;

public class DoubleJump implements Listener {

    private final HubCore plugin;
    private final MainConfigManager configManager;
    private final HashMap<Player, Boolean> hasDoubleJumped = new HashMap<>();

    public DoubleJump(HubCore plugin) {
        this.plugin = plugin;
        this.configManager = plugin.getMainConfigManager();
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) return;
        if (!event.isFlying()) return; // Solo se activa si el jugador intenta volar

        // Verifica si el jugador está en el aire
        if (player.getWorld().getBlockAt(player.getLocation().subtract(0, 2, 0)).getType() == Material.AIR) {
            event.setCancelled(true);
            return;
        }

        // Manejo del doble salto
        if (!hasDoubleJumped.getOrDefault(player, false)) {
            hasDoubleJumped.put(player, true); // Marca que el jugador ha usado su doble salto

            // Ejecuta el salto
            player.setVelocity(player.getLocation().getDirection().multiply(configManager.getLaunchPower()).setY(configManager.getLaunchPowerY()));
            player.playSound(player.getLocation(), configManager.getSound(), 1.0f, 1.0f);
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // Reinicia el estado de doble salto si el jugador está en el suelo
        if (player.isOnGround()) {
            hasDoubleJumped.remove(player);
        }
    }
}
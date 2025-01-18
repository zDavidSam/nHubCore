package dev.nexus.operations.utils;

import dev.nexus.operations.HubCore;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {
    private final HubCore plugin;

    public SoundManager(HubCore plugin) {
        this.plugin = plugin;
    }

    public void playJoinSound(Player player) {
        try {
            String soundName = plugin.getMainConfigManager().getJoinSound();
            float volume = plugin.getMainConfigManager().getJoinSoundVolume();
            float pitch = plugin.getMainConfigManager().getJoinSoundPitch();

            Sound sound = Sound.valueOf(soundName);

            // Play sound to the joining player
            player.playSound(player.getLocation(), sound, volume, pitch);

            // Play sound to all other players
            player.getWorld().getPlayers().stream()
                    .filter(p -> !p.equals(player))
                    .forEach(p -> p.playSound(player.getLocation(), sound, volume, pitch));

        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Invalid sound name in config: " + plugin.getMainConfigManager().getJoinSound());
        }
    }
}
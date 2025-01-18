package dev.nexus.operations.listeners.join;

import dev.nexus.operations.HubCore;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.List;

public class JoinEffectManager {
    private final HubCore plugin;

    public JoinEffectManager(HubCore plugin) {
        this.plugin = plugin;
    }

    public void applyEffects(Player player) {
        if (!plugin.getConfig().getBoolean("effects.enabled", true)) {
            return;
        }

        List<String> effects = plugin.getConfig().getStringList("effects.list");

        for (String effectStr : effects) {
            try {
                applyEffect(player, effectStr);
            } catch (Exception e) {
                plugin.getLogger().warning("Failed to apply effect '" + effectStr + "': " + e.getMessage());
            }
        }
    }

    private void applyEffect(Player player, String effectStr) {
        // Validate input
        if (effectStr == null || effectStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Effect string cannot be empty");
        }

        // Split and validate parts
        String[] parts = effectStr.trim().split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Effect must include both name and level");
        }

        // Parse effect name
        String effectName = parts[0].toUpperCase();
        PotionEffectType effectType = PotionEffectType.getByName(effectName);
        if (effectType == null) {
            throw new IllegalArgumentException("Invalid effect type: " + effectName);
        }

        // Parse effect level
        int level;
        try {
            level = Integer.parseInt(parts[1]);
            if (level < 1) {
                throw new IllegalArgumentException("Effect level must be 1 or greater");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid effect level: " + parts[1]);
        }

        // Apply the effect (level - 1 for amplifier since Minecraft uses 0-based levels)
        PotionEffect effect = new PotionEffect(
                effectType,
                Integer.MAX_VALUE,  // Duration (infinite)
                level - 1,          // Amplifier
                true,              // Ambient
                false              // Hide particles
        );

        player.addPotionEffect(effect);
    }
}
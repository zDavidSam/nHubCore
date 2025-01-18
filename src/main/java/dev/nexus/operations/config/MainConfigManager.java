package dev.nexus.operations.config;

import dev.nexus.operations.HubCore;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public class MainConfigManager {

    private CustomConfig configFile;

    private HubCore plugin;

    private String joinMessage;
    private String quitMessage;
    private String joinSound;
    private float joinSoundVolume;
    private float joinSoundPitch;
    private boolean enabled;
    private double launchPower;
    private double launchPowerY;
    private int cooldown;
    private Sound sound;

    public MainConfigManager(HubCore plugin){
        this.plugin = plugin;
        configFile = new CustomConfig("config.yml", null, plugin);
        configFile.registerConfig();
        loadConfig();
    }

    private void loadConfig() {
        FileConfiguration config = configFile.getConfig();

        // Set defaults if they don't exist
        config.addDefault("message-custom.custom-join-message", "&8[&a+&8] &7%s");
        config.addDefault("message-custom.custom-quit-message", "&8[&c-&8] &7%s");
        config.addDefault("join.sound", "ENTITY_CAT_AMBIENT");
        config.addDefault("join.volume", 1.0);
        config.addDefault("join.pitch", 1.0);
        config.options().copyDefaults(true);
        configFile.saveConfig();

        // Load values
        joinMessage = config.getString("message-custom.custom-join-message");
        quitMessage = config.getString("message-custom.custom-quit-message");
        joinSound = config.getString("join.sound");
        joinSoundVolume = (float) config.getDouble("join.volume");
        joinSoundPitch = (float) config.getDouble("join.pitch");


    }

    public void reloadConfig(){
        configFile.reloadConfig();
        loadConfig();
    }

    public String getJoin() {
        return joinMessage;
    }

    public String getQuit() {
        return quitMessage;
    }

    public String getJoinSound() {
        return joinSound;
    }

    public float getJoinSoundVolume() {
        return joinSoundVolume;
    }

    public float getJoinSoundPitch() {
        return joinSoundPitch;
    }

    public double getLaunchPower() {
        return launchPower;
    }

    public double getLaunchPowerY() {
        return launchPowerY;
    }

    public Sound getSound() {
        return sound;
    }


}

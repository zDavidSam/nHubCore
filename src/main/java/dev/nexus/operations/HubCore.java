package dev.nexus.operations;

import dev.nexus.operations.commands.EssentialsCommand.FlyCommand;
import dev.nexus.operations.commands.EssentialsCommand.GamemodesCommand;
import dev.nexus.operations.commands.Inventory.ServerSelectorCommand;
import dev.nexus.operations.commands.Inventory.cosmetics.CosmeticsCommand;
import dev.nexus.operations.commands.PluginCommand;
import dev.nexus.operations.config.MainConfigManager;
import dev.nexus.operations.listeners.InventoryListener;
import dev.nexus.operations.listeners.PlayerJoinListener;
import dev.nexus.operations.listeners.join.JoinMessagesListener;
import dev.nexus.operations.listeners.message.JoinMessageListener;
import dev.nexus.operations.listeners.message.LeaveMessageListener;
import dev.nexus.operations.managers.InventoryManager;
import dev.nexus.operations.managers.cosmetics.CosmeticsInvManager;
import dev.nexus.operations.utils.MessageFormatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class HubCore extends JavaPlugin {

    private MessageFormatter messageFormatter;
    private MainConfigManager mainConfigManager;
    private InventoryManager inventoryManager;
    private CosmeticsInvManager cosmeticsInvManager;
    public String prefix = "&7[&bnHubCore&7]";
    private String version = getDescription().getVersion();

    public void onEnable(){
        registerManagers();
        registerEvents();
        registerProxy();
        registerCommands();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPlugin enable"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCreated by Nexus Studio"));
    }

    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlugin disable"));
    }

    public void registerCommands(){
        this.getCommand("fly").setExecutor(new FlyCommand(this));
        this.getCommand("gmc").setExecutor(new GamemodesCommand());
        this.getCommand("gms").setExecutor(new GamemodesCommand());
        this.getCommand("gma").setExecutor(new GamemodesCommand());
        this.getCommand("gmsp").setExecutor(new GamemodesCommand());
        this.getCommand("nhubcore").setExecutor(new PluginCommand(this));
        this.getCommand("selector").setExecutor(new ServerSelectorCommand(this));
        this.getCommand("cosmetics").setExecutor(new CosmeticsCommand(this));
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new JoinMessageListener(this), this);
        getServer().getPluginManager().registerEvents(new LeaveMessageListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinMessagesListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
    }

    public void registerManagers(){
        mainConfigManager = new MainConfigManager(this);
        inventoryManager = new InventoryManager(this);
        cosmeticsInvManager = new CosmeticsInvManager(this);

    }

    public void registerProxy(){
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    public MessageFormatter getMessageFormatter() {
        return messageFormatter;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public CosmeticsInvManager getCosmeticsInvManager() {
        return cosmeticsInvManager;
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }
}

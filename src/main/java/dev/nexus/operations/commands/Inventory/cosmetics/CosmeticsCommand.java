package dev.nexus.operations.commands.Inventory.cosmetics;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.model.InventoryPlayer;
import dev.nexus.operations.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CosmeticsCommand implements CommandExecutor {
    private final HubCore plugin;

    public CosmeticsCommand(HubCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(MessageUtils.getColoredMessage(MessageUtils.getColoredMessage("&cNo console")));
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0){
            plugin.getCosmeticsInvManager().openMenuCosmetics(new InventoryPlayer(player));
            return true;
        }

        return true;
    }
}

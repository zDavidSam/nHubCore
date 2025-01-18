package dev.nexus.operations.commands;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.model.InventoryPlayer;
import dev.nexus.operations.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginCommand implements CommandExecutor {

    private final HubCore plugin;

    public PluginCommand(HubCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            if(args[0].equalsIgnoreCase("reload")){
                subCommandReload(sender);
                return true;
            }
            sender.sendMessage(MessageUtils.getColoredMessage("&cComando incorrecto"));
        }

        if(args.length == 0){
            sender.sendMessage(MessageUtils.getColoredMessage("&aSi funca"));
            return true;
        }

        Player player = (Player) sender;
        if(args[0].equalsIgnoreCase("reload")){
            subCommandReload(sender);
        }
        return true;
    }

    public void subCommandReload(CommandSender sender){
        if(!sender.hasPermission("hubcore.reload")){
            sender.sendMessage(MessageUtils.getColoredMessage("&cNo tienes permisos para ejecutar este comando"));
            return;
        }

        plugin.getMainConfigManager().reloadConfig();
        sender.sendMessage(MessageUtils.getColoredMessage("&aPlugin recargado"));
    }
}

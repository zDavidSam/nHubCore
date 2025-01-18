package dev.nexus.operations.commands.EssentialsCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.nexus.operations.HubCore;
import dev.nexus.operations.utils.MessageUtils;

public class FlyCommand implements CommandExecutor {

    private final HubCore plugin;

    public FlyCommand(HubCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cThis command must be executed from the player"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("HubCore.use.fly")) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cNo tienes permisos para usar este comando."));
            return true;
        }

        if (args.length == 0) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.sendMessage(MessageUtils.getColoredMessage("&cFly Disable"));
            } else {
                player.setAllowFlight(true);
                player.sendMessage(MessageUtils.getColoredMessage("&eFly Enable"));
            }
            return true;
        }

        if (args.length == 1) {
            if (!player.hasPermission("HubCore.fly.others")) {
                player.sendMessage(MessageUtils.getColoredMessage("&cNo tienes permisos para activar/desactivar el vuelo de otros jugadores."));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(MessageUtils.getColoredMessage("&cEl jugador especificado no está en línea."));
                return true;
            }

            if (target.getAllowFlight()) {
                target.setAllowFlight(false);
                target.sendMessage(MessageUtils.getColoredMessage("&cEl vuelo ha sido desactivado por " + player.getName()));
                player.sendMessage(MessageUtils.getColoredMessage("&cHas desactivado el vuelo de " + target.getName()));
            } else {
                target.setAllowFlight(true);
                target.sendMessage(MessageUtils.getColoredMessage("&aEl vuelo ha sido activado por " + player.getName()));
                player.sendMessage(MessageUtils.getColoredMessage("&aHas activado el vuelo de " + target.getName()));
            }
            return true;
        }

        player.sendMessage(MessageUtils.getColoredMessage("&cUso incorrecto del comando. Usa: /fly [jugador]"));
        return false;
    }

}

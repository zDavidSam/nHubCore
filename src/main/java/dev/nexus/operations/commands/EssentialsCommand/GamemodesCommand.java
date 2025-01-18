package dev.nexus.operations.commands.EssentialsCommand;

import dev.nexus.operations.commands.gamemode.GameModeHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodesCommand implements CommandExecutor {
    private final GameModeHandler gameModeHandler;

    public GamemodesCommand() {
        this.gameModeHandler = new GameModeHandler();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be executed by players!");
            return true;
        }

        Player player = (Player) sender;
        String cmdName = command.getName().toLowerCase();

        switch (cmdName) {
            case "gmc":
                return gameModeHandler.setCreative(player);
            case "gms":
                return gameModeHandler.setSurvival(player);
            case "gma":
                return gameModeHandler.setAdventure(player);
            case "gmsp":
                return gameModeHandler.setSpectator(player);
            default:
                return false;
        }
    }
}
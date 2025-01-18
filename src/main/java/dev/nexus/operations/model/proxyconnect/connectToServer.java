package dev.nexus.operations.model.proxyconnect;

import dev.nexus.operations.HubCore;
import dev.nexus.operations.utils.MessageUtils;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class connectToServer {
    private final HubCore plugin;

    public connectToServer(HubCore plugin) {
        this.plugin = plugin;
    }

    private void connectToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);

            out.writeUTF("Connect");
            out.writeUTF(server);

            player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            player.sendMessage(MessageUtils.getColoredMessage("&aConnecting to " + server + "..."));

        } catch (Exception e) {
            player.sendMessage(MessageUtils.getColoredMessage("&cError connecting to server!"));
            e.printStackTrace();
        }
    }
}

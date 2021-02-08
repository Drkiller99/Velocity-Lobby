package me.drkiller99.command;

/**
 * @Project: lobby_command
 * @Author: Drkiller99
 * @Created: 2020/05/19
 * @Website: verenitymc.net
 * @Time: unknown for the time.
 */

import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.proxy.Player;
import net.kyori.text.format.TextColor;
import net.kyori.text.TextComponent;
import me.drkiller99.main.main;
import java.util.Optional;

public class LobbyCommand implements Command {

    private final ProxyServer server;

    public LobbyCommand(ProxyServer server) {
        this.server = server;
    }
    private Optional<RegisteredServer> toConnect() {
        return server.getServer(main.lobby_name);
    }

    @Override
    public void execute(CommandSource source, String[] args) {
        final Player player = (Player) source;
        if (toConnect().isPresent()) {
            if (toConnect().equals(main.lobby_name)) {
                return;
            } else {
                player.sendMessage(TextComponent.of("Sending you to the " + main.lobby_name + ".").color(TextColor.RED));
                player.createConnectionRequest(toConnect().get()).fireAndForget();
                return;
            }
        } else {
            player.sendMessage(TextComponent.of("Error: Invalid lobby name, current config name: " + main.lobby_name + ".").color(TextColor.RED));
            return;
        }
    }
}
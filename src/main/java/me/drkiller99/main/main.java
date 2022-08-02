package me.drkiller99.main;

import com.moandjiezana.toml.Toml;

import com.google.inject.Inject;

import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.plugin.Plugin;

import lombok.Getter;
import me.drkiller99.command.LobbyCommand;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;

/**
 * @Project: lobby_command
 * @Author: Drkiller99
 * @Created: 2020/05/19
 * @Website: verenitymc.net
 * @Time: unknown for the time.
 */

@Plugin
        (
          id = "lobby_command",
          name = "Lobby",
          version = "1.0",
          description = "This command will send players on the network to the default lobby.",
          authors = "Drkiller99",
          url = "verenitymc.net"
        )

public class main {

   @Getter public static String lobby_name;

    @Inject
    private main(ProxyServer s, CommandManager cmd, Logger log, @DataDirectory Path folder) {
        long load = System.currentTimeMillis();
        registerCommands(s, cmd, log);
        registerToml(log, folder);
        log.info("Successfully enabled. " + (System.currentTimeMillis() - load) + ("ms"));
    }

    private void registerCommands(ProxyServer s, CommandManager cmd, Logger log) {
        cmd.register(new LobbyCommand(s), "hub", "lobby", "logout");
        log.info("Commands...");
    }

    private void registerToml(Logger log, @DataDirectory Path folder) {
        final Toml toml = loadConfig(folder);
        if (toml == null) {
            log.warn("Error: Configuration file did not load. Disabling plugin now.");
        } else {
            lobby_name = toml.getString("lobby-name");
            log.info("Configuration...");
        }
    }

    private Toml loadConfig(Path path) {
        final File folder = path.toFile();
        final File file = new File(folder, "config.toml");
        if (!(file.getParentFile().exists())) {
            file.getParentFile().mkdirs();
        }
        if (!(file.exists())) {
            try (InputStream input = getClass().getResourceAsStream("/" + file.getName())) {
                if (input != null) {
                    Files.copy(input, file.toPath());
                } else {
                    file.createNewFile();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return null;
            }
        }
        return new Toml().read(file);
    }
}

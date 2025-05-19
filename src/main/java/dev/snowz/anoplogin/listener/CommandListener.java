package dev.snowz.anoplogin.listener;

import dev.snowz.anoplogin.LoginManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public final class CommandListener implements Listener {

    @EventHandler
    public void onCommand(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        if (!player.isOp()) return;
        if (LoginManager.isLoggedIn(player)) return;
        if (event.getMessage().split(" ")[0].equalsIgnoreCase("/login")) return;

        event.setCancelled(true);
        player.sendMessage("§c§l(!) §cYou must login before you can run commands.");
    }

    @EventHandler
    public void preventOpCommand(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage();
        final String[] args = message.split(" ");

        if (args[0].equalsIgnoreCase("/op") || args[0].equalsIgnoreCase("/deop")) {
            event.setCancelled(true);
            player.sendMessage("§c§l(!) §cThis command can only be ran from console.");
        }
    }

}

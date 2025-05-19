package dev.snowz.anoplogin.listener;

import dev.snowz.anoplogin.LoginManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public final class MovementListener implements Listener {

    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (!player.isOp()) return;
        if (LoginManager.isLoggedIn(player)) return;

        final Location from = event.getFrom();
        final Location to = event.getTo();

        if (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()) {
            event.setCancelled(true);
            player.sendMessage("§c§l(!) §cYou must login before you can move.");
        }
    }

}

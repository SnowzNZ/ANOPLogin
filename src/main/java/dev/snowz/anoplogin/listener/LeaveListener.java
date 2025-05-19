package dev.snowz.anoplogin.listener;

import dev.snowz.anoplogin.LoginManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public final class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(final PlayerQuitEvent event) {
        LoginManager.logout(event.getPlayer());
    }
}

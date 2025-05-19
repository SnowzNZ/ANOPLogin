package dev.snowz.anoplogin.listener;

import dev.snowz.anoplogin.LoginManager;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class ChatListener implements Listener {

    @EventHandler
    public void onChat(final AsyncChatEvent event) {
        final Player player = event.getPlayer();
        if (!player.isOp()) return;
        if (LoginManager.isLoggedIn(player)) return;

        event.setCancelled(true);
        player.sendMessage("§c§l(!) §cYou must login before you can chat.");
    }

}

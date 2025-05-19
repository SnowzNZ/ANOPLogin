package dev.snowz.anoplogin.command;

import dev.snowz.anoplogin.LoginManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class LogoutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command command,
        @NotNull final String label,
        @NotNull final String @NotNull [] args
    ) {
        if (!(sender instanceof final Player player)) {
            sender.sendMessage("§c§l(!) §cThis command can only be ran by players.");
            return true;
        }

        LoginManager.logout(player);
        player.sendMessage("§a§l(!) §aYou have successfully logged out.");
        return true;
    }
}

package dev.snowz.anoplogin.command;

import dev.snowz.anoplogin.LoginManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class LoginCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command command,
        @NotNull final String label,
        @NotNull final String @NotNull [] args
    ) {

        if (args.length == 1 && sender instanceof final Player player) {
            final String password = args[0];

            if (LoginManager.isLoggedIn(player)) {
                player.sendMessage("§c§l(!) §cYou are already logged in.");
                return true;
            }

            if (LoginManager.getPassword(player).equals(password)) {
                player.sendMessage("§a§l(!) §aYou have successfully logged in.");
                LoginManager.loggedInPlayers.add(player.getUniqueId());
            } else {
                player.sendMessage("§c§l(!) §cInvalid password.");
            }

            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§c§l(!) §cUsage: /login <set|remove> [player] [password]");
            return true;
        }

        final String action = args[0].toLowerCase();

        if (sender instanceof Player) {
            sender.sendMessage("§c§l(!) §cThis command can only be ran from console.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§c§l(!) §cUsage: /login " + action + " <player>" + (action.equals("set") ? " <password>" : ""));
            return true;
        }

        final String playerName = args[1];
        final Player player = sender.getServer().getPlayer(playerName);
        if (player == null) {
            sender.sendMessage("§c§l(!) §cPlayer " + playerName + " not found.");
            return true;
        }

        switch (action) {
            case "set" -> {
                if (args.length < 3) {
                    sender.sendMessage("§c§l(!) §cUsage: /login set <player> <password>");
                    return true;
                }
                final String password = args[2];

                LoginManager.setPassword(player, password);
                sender.sendMessage("§a§l(!) §aSet password for " + playerName + " to " + password);
            }
            case "remove" -> {
                LoginManager.removePlayer(player);
                sender.sendMessage("§a§l(!) §aRemoved password for " + playerName);
            }
        }

        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(
        @NotNull final CommandSender sender,
        @NotNull final Command command,
        @NotNull final String label,
        @NotNull final String @NotNull [] args
    ) {
        if (sender instanceof Player) {
            return List.of();
        }

        if (args.length == 1) {
            return List.of("set", "remove");
        }

        if (args.length == 2) {
            return Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getName)
                .filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase()))
                .toList();
        }

        return List.of();
    }

}

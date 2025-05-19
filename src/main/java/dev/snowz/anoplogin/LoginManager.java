package dev.snowz.anoplogin;

import dev.snowz.anoplogin.data.Data;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class LoginManager {

    public static Set<UUID> loggedInPlayers = new HashSet<>();

    private static final FileConfiguration data = Data.getData();

    public static boolean isLoggedIn(final Player player) {
        return loggedInPlayers.contains(player.getUniqueId());
    }

    public static String getPassword(final Player player) {
        return data.getString("players." + player.getUniqueId() + ".password");
    }

    public static void setPassword(final Player player, final String password) {
        data.set("players." + player.getUniqueId() + ".password", password);
        Data.save();
    }

    public static void removePlayer(final Player player) {
        data.set("players." + player.getUniqueId(), null);
        Data.save();
    }

    public static void logout(final Player player) {
        loggedInPlayers.remove(player.getUniqueId());
    }
}

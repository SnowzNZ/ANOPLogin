package dev.snowz.anoplogin;

import dev.snowz.anoplogin.command.LoginCommand;
import dev.snowz.anoplogin.command.LogoutCommand;
import dev.snowz.anoplogin.data.Data;
import dev.snowz.anoplogin.listener.ChatListener;
import dev.snowz.anoplogin.listener.CommandListener;
import dev.snowz.anoplogin.listener.LeaveListener;
import dev.snowz.anoplogin.listener.MovementListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class ANOPLogin extends JavaPlugin {

    private static ANOPLogin instance;

    public static ANOPLogin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Data
        Data.load();

        // Login Command
        final PluginCommand loginCommand = getCommand("login");
        assert loginCommand != null;

        loginCommand.setExecutor(new LoginCommand());
        loginCommand.setTabCompleter(new LoginCommand());

        // Logout Command
        final PluginCommand logoutCommand = getCommand("logout");
        assert logoutCommand != null;

        logoutCommand.setExecutor(new LogoutCommand());

        // Listeners
        List.of(
            new ChatListener(),
            new CommandListener(),
            new LeaveListener(),
            new MovementListener()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    @Override
    public void onDisable() {
        Data.save();
    }

}

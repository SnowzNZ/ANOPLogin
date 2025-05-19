package dev.snowz.anoplogin.data;

import dev.snowz.anoplogin.ANOPLogin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public final class Data {

    private static final ANOPLogin plugin = ANOPLogin.getInstance();

    private static File dataFile;
    private static FileConfiguration data;

    public static FileConfiguration getData() {
        return data;
    }

    public static void load() {
        dataFile = new File(plugin.getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            plugin.saveResource("data.yml", false);
        }

        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public static void save() {
        try {
            data.save(dataFile);
        } catch (final IOException e) {
            plugin.getLogger().severe("Failed to save data: " + e.getMessage());
        }
    }

}
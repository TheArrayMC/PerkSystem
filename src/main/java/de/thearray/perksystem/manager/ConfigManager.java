package de.thearray.perksystem.manager;

import de.thearray.perksystem.enums.PerkType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ConfigManager {

    private static File file = new File("plugins//PerkSystem/Players.yml");
    private static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static boolean isPlayerRegistered(UUID uuid) {
        return config.contains(String.valueOf(uuid));
    }

    public static void registerPlayer(UUID uuid) {
        config.set(String.valueOf(uuid), uuid);
        config.set(String.valueOf(uuid) + ".Name", Bukkit.getPlayer(uuid).getName());
        config.set(String.valueOf(uuid) + ".FAST_BRAKE", false);
        config.set(String.valueOf(uuid) + ".NO_HUNGER", false);
        config.set(String.valueOf(uuid) + ".SPEED", false);
        config.set(String.valueOf(uuid) + ".KEEP_INVENTORY", false);
        saveFile();
    }

    public static void setPerk(UUID uuid, String name, boolean value) {
        config.set(String.valueOf(uuid) + "." + PerkType.valueOf(name), value);
        saveFile();
    }

}

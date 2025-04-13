package de.thearray.perksystem;

import de.thearray.perksystem.command.PerksCommand;
import de.thearray.perksystem.listener.ConnectionListener;
import de.thearray.perksystem.listener.PerkListener;
import de.thearray.perksystem.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PerkSystem extends JavaPlugin {

    private static PerkSystem instance;
    private String prefix = "§8[§6System§8] §7";
    private String noPerm = prefix + "Dazu hast du keine Rechte.";

    public static PerkSystem getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void register() {
        ConfigManager.saveFile();

        getCommand("perks").setExecutor(new PerksCommand());

        getServer().getPluginManager().registerEvents(new ConnectionListener(),this);
        getServer().getPluginManager().registerEvents(new PerkListener(),this);
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNoPerm() {
        return noPerm;
    }
}

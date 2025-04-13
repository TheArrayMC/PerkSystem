package de.thearray.perksystem.listener;

import de.thearray.perksystem.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!ConfigManager.isPlayerRegistered(player.getUniqueId())) ConfigManager.registerPlayer(player.getUniqueId());

        if (ConfigManager.getConfig().getBoolean(player.getUniqueId() + ".FAST_BRAKE"))
            player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, Integer.MAX_VALUE, 2));
        if (ConfigManager.getConfig().getBoolean(player.getUniqueId() + ".SPEED"))
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

}

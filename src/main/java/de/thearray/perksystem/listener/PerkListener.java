package de.thearray.perksystem.listener;

import de.thearray.perksystem.PerkSystem;
import de.thearray.perksystem.enums.PerkType;
import de.thearray.perksystem.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PerkListener implements Listener {

    private Map<UUID, Collection<PotionEffect>> savedEffects = new HashMap<>();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null || event.getCurrentItem() == null) return;

        if (!event.getView().getTitle().equalsIgnoreCase("§8» §3Perks")) return;

        event.setCancelled(true);

        ItemStack currentItem = event.getCurrentItem();
        ItemMeta meta = currentItem.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        String displayName = meta.getDisplayName();

        for (PerkType perk : PerkType.values()) {
            if (displayName.equalsIgnoreCase(perk.getDisplayName())) {
                handlePerkClick(player, perk);
                player.closeInventory();
                break;
            }
        }
    }

    @EventHandler
    public void onFoodLeven(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();

        if (ConfigManager.getConfig().getBoolean(player.getUniqueId() + ".NO_HUNGER")) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();
        savedEffects.put(player.getUniqueId(), player.getActivePotionEffects());

        if (!ConfigManager.getConfig().getBoolean(player.getUniqueId() + ".KEEP_INVENTORY")) {
            return;
        }
        event.setKeepInventory(true);
        event.setKeepLevel(true);
        event.setShouldDropExperience(false);
        event.getDrops().clear();

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (!savedEffects.containsKey(player.getUniqueId())) {
            return;
        }
        Collection<PotionEffect> effects = savedEffects.get(player.getUniqueId());

        Bukkit.getScheduler().runTaskLater(PerkSystem.getInstance(), () -> {
            for (PotionEffect effect : effects) {
                player.addPotionEffect(effect);
            }
            savedEffects.remove(player.getUniqueId());
        }, 1L);
    }

    private void handlePerkClick(Player player, PerkType perk) {
        String path = player.getUniqueId() + "." + perk.name();
        boolean isEnabled = ConfigManager.getConfig().getBoolean(path);

        if (isEnabled) {
            ConfigManager.setPerk(player.getUniqueId(), perk.name(), false);
            player.sendMessage(PerkSystem.getInstance().getPrefix() + "Dein Perk " + perk.getColor() + perk.getName() + " §7wurde §cdeaktiviert§8.");
            removePerkEffect(player, perk);
        } else {
            ConfigManager.setPerk(player.getUniqueId(), perk.name(), true);
            player.sendMessage(PerkSystem.getInstance().getPrefix() + "Dein Perk " + perk.getColor() + perk.getName() + " §7wurde §aaktiviert§8.");
            applyPerkEffect(player, perk);
        }
    }

    private void applyPerkEffect(Player player, PerkType perk) {
        switch (perk) {
            case FAST_BRAKE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, Integer.MAX_VALUE, 2));
                break;
            case SPEED:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
                break;
        }
    }

    private void removePerkEffect(Player player, PerkType perk) {
        switch (perk) {
            case FAST_BRAKE:
                player.removePotionEffect(PotionEffectType.HASTE);
                break;
            case SPEED:
                player.removePotionEffect(PotionEffectType.SPEED);
                break;
        }
    }

}

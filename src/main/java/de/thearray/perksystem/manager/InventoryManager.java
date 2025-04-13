package de.thearray.perksystem.manager;

import de.thearray.perksystem.PerkSystem;
import de.thearray.perksystem.enums.PerkType;
import de.thearray.perksystem.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryManager {

    public static void openPerkInventory(Player player) {
        Inventory perkInventory = Bukkit.createInventory(null, 9, "§8» §3Perks");

        perkInventory.setItem(1, ConfigManager.getConfig().getBoolean(player.getUniqueId() + ".FAST_BRAKE") ? new ItemBuilder(Material.GOLDEN_PICKAXE)
                .addLoreLine("§8» §aAktiviert")
                .setName(PerkType.FAST_BRAKE.getDisplayName()).toItemStack() : new ItemBuilder(Material.GOLDEN_PICKAXE)
                .addLoreLine("§8» §cDeaktiviert")
                .setName(PerkType.FAST_BRAKE.getDisplayName()).toItemStack());
        perkInventory.setItem(3, ConfigManager.getConfig().getBoolean(player.getUniqueId() + ".KEEP_INVENTORY") ? new ItemBuilder(Material.CHEST)
                .addLoreLine("§8» §aAktiviert")
                .setName(PerkType.KEEP_INVENTORY.getDisplayName()).toItemStack() : new ItemBuilder(Material.CHEST)
                .addLoreLine("§8» §cDeaktiviert")
                .setName(PerkType.KEEP_INVENTORY.getDisplayName()).toItemStack());
        perkInventory.setItem(5, ConfigManager.getConfig().getBoolean(player.getUniqueId() + ".NO_HUNGER") ? new ItemBuilder(Material.COOKED_BEEF)
                .addLoreLine("§8» §aAktiviert")
                .setName(PerkType.NO_HUNGER.getDisplayName()).toItemStack() : new ItemBuilder(Material.COOKED_BEEF)
                .addLoreLine("§8» §cDeaktiviert")
                .setName(PerkType.NO_HUNGER.getDisplayName()).toItemStack());
        perkInventory.setItem(7, ConfigManager.getConfig().getBoolean(player.getUniqueId() + ".SPEED") ? new ItemBuilder(Material.FEATHER)
                .addLoreLine("§8» §aAktiviert")
                .setName(PerkType.SPEED.getDisplayName()).toItemStack() : new ItemBuilder(Material.FEATHER)
                .addLoreLine("§8» §cDeaktiviert")
                .setName(PerkType.SPEED.getDisplayName()).toItemStack());

        player.openInventory(perkInventory);
    }

}

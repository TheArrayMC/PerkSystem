package de.thearray.perksystem.command;

import de.thearray.perksystem.manager.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PerksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            InventoryManager.openPerkInventory(player);
        }

        return false;
    }
}

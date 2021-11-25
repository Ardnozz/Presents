package cz.ardno.presents.commands;

import cz.ardno.presents.commands.system.PresentPlayerCommand;
import cz.ardno.presents.utilities.CustomCraftingUtility;
import org.bukkit.entity.Player;

public class PresentOpenCraftingCommand extends PresentPlayerCommand {

    @Override
    public void execute(Player player, String[] args) {
        player.openInventory(CustomCraftingUtility.getInventory(player.getName()));
    }
}

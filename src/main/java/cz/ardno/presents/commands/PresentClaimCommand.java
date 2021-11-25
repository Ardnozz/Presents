package cz.ardno.presents.commands;

import cz.ardno.presents.commands.system.PresentPlayerCommand;
import cz.ardno.presents.utilities.ConfigUtility;
import cz.ardno.presents.utilities.CraftingItems;
import cz.ardno.presents.utilities.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PresentClaimCommand extends PresentPlayerCommand {

    @Override
    public void execute(Player player, String[] arguments) {
        String path = player.getName() + "-player";
        String presentsResponse = ConfigUtility.config.getString(path);
        if (presentsResponse != null && !presentsResponse.equals("null")) {
            String[] presentsIds = presentsResponse.split(", ");
            for (String presentsCustomModelData : presentsIds) {
                ItemStack item = CraftingItems.getPresentWithCustomModelData(presentsCustomModelData);
                player.getLocation().getWorld().dropItem(player.getLocation(), item);
            }
            ConfigUtility.config.set(path, "null");
            player.sendMessage(MessageUtility.CMD_CLAIMPRESENTS_SUCCESSFULLY.getMessage());
            return;
        }
        player.sendMessage(MessageUtility.CMD_CLAIMPRESENTS_ANY_PRESENTS.getMessage());
    }
}

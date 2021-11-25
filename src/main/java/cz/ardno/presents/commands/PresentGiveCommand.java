package cz.ardno.presents.commands;

import cz.ardno.presents.commands.system.PresentPlayerCommand;
import cz.ardno.presents.utilities.ConfigUtility;
import cz.ardno.presents.utilities.CraftingItems;
import cz.ardno.presents.utilities.MessageUtility;
import cz.ardno.presents.utilities.PresentsCompareUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PresentGiveCommand extends PresentPlayerCommand {

    @Override
    public void execute(Player player, String[] arguments) {
        if (arguments.length >= 1) {
            if (arguments[0].equals(player.getName())) {
                player.sendMessage(MessageUtility.CMD_PRESENTGIVE_SEND_THEMSELVES.getMessage());
                return;
            }
            Player player2 = Bukkit.getPlayer(arguments[0]);
            if (player2 != null) {
                ItemStack heldItem = player.getInventory().getItemInMainHand();
                if (heldItem.getType().equals(Material.PLAYER_HEAD) && !heldItem.getItemMeta().hasLore() && heldItem.getItemMeta().hasDisplayName() && heldItem.getItemMeta().getDisplayName().contains("Present")) {
                    if (CraftingItems.presents.stream().anyMatch((present) -> PresentsCompareUtil.compareIgnoreCustomModelData(heldItem, present))) {
                        String playerPresents = ConfigUtility.config.getString(arguments[0] + "-player");
                        String path = arguments[0] + "-player";
                        int customModelData = heldItem.getItemMeta().getCustomModelData();

                        if (playerPresents != null && !playerPresents.equals("null")) {
                            ConfigUtility.config.set(path, ConfigUtility.config.getString(path) + ", " + customModelData + "-" + player.getName());
                        } else {
                            ConfigUtility.config.set(path, customModelData + "-" + player.getName());
                        }

                        heldItem.setAmount(0);
                        player.sendMessage(MessageUtility.CMD_GIVEPRESENT_SUCCESSFULLY_SENDED.getMessage());
                        player2.sendMessage(MessageUtility.PRESENT_GIVEN.getMessage());
                        return;
                    }
                }
                player.sendMessage(MessageUtility.CMD_PRESENTGIVE_HOLDING_WRONG_ITEM.getMessage());
                return;
            }
            player.sendMessage(MessageUtility.CMD_PRESENTGIVE_ARGUMENT_OFFLINE_PLAYER.getMessage());
            return;
        }
        player.sendMessage(String.format(MessageUtility.CMD_ARGUMENT_NEEDED.getMessage(), "Player"));
    }
}

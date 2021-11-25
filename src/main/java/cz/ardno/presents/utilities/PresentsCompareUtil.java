package cz.ardno.presents.utilities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PresentsCompareUtil {

    public static boolean compareIgnoreCustomModelData(ItemStack item1, ItemStack item2) {
        if (item1.getType() == Material.PLAYER_HEAD && item2.getType() == Material.PLAYER_HEAD) {
            if (item1.getAmount() == item1.getAmount()) {
                if (item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName())) {
                    return true;
                }
            }
        }
        return false;
    }
}

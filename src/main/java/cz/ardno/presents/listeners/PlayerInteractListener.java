package cz.ardno.presents.listeners;

import cz.ardno.presents.utilities.CraftingItems;
import cz.ardno.presents.utilities.PresentsCompareUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) return;

        ItemStack heldItem = event.getPlayer().getInventory().getItemInMainHand();

        if (CraftingItems.presents.stream().anyMatch((present) -> PresentsCompareUtil.compareIgnoreCustomModelData(heldItem, present))) {
            event.setCancelled(true);
            if (heldItem.getItemMeta().hasLore()) {
                CraftingItems.openPresent(event.getPlayer(), heldItem.getItemMeta().getCustomModelData());
                return;
            }
            CraftingItems.openPresentAsGUI(event.getPlayer(), heldItem.getItemMeta().getCustomModelData());
        }
    }
}

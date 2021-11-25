package cz.ardno.presents.listeners;

import cz.ardno.presents.utilities.CustomCraftingRecipes;
import cz.ardno.presents.utilities.ItemsInRecipe;
import cz.ardno.presents.utilities.MessageUtility;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class PlayerInventoryClickListener implements Listener {

    @EventHandler
    public void onPlayerClickInInventory(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {
            if (event.getClickedInventory().getType() == InventoryType.CHEST) {
                if (event.getView().getTitle().equals("Present Crafting") && event.getClickedInventory().getItem(8) != null) {
                    ItemStack modelDataItem = event.getClickedInventory().getItem(8);
                    if (modelDataItem.hasItemMeta() && modelDataItem.getItemMeta().hasCustomModelData()) {
                        if (modelDataItem.getItemMeta().getCustomModelData() == 56) {
                            if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
                                event.setCancelled(true);
                                return;
                            }

                            if ((event.getSlot() == 24 || event.getSlot() == 25 || event.getSlot() == 33 || event.getSlot() == 34)) {
                                if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) {
                                    event.setCancelled(true);
                                    return;
                                }
                            }

                            /*if (event.getSlot() == 24) {
                                if (event.getClickedInventory().getItem(24).getType() != Material.AIR) {
                                    event.getClickedInventory().setItem(24, CustomCraftingUtility.light_gray_stained_glass_pane);
                                }
                            }*/

                            if (event.getSlot() == 23 || event.getSlot() == 32) {
                                event.setCancelled(true);
                                ItemsInRecipe<ItemStack> itemsInCrafting = new ItemsInRecipe<>();
                                for (int row = 1; row <= 4; row++) {
                                    for (int slot = 0; slot <= 3; slot++) {
                                        int slotFinal = row * 9 + 1 + slot;
                                    /*if (event.getSlot() == slotFinal && event.getCursor().getType() != Material.AIR) {
                                        itemsInCrafting.add(event.getCursor());
                                        continue;
                                    }
                                    if (event.getClickedInventory().getItem(event.getSlot()) != null) {
                                        itemsInCrafting.add(null);
                                        continue;
                                    }*/
                                        itemsInCrafting.add(event.getClickedInventory().getItem(slotFinal));
                                    }
                                }
                                for (CustomCraftingRecipes ccr : CustomCraftingRecipes.values()) {
                                    if (itemsInCrafting.equals(ccr.getCraftingSlots())) {
                                        ItemStack result = ccr.getResult().clone();
                                        ItemStack item24 = event.getClickedInventory().getItem(24);
                                        for (int row = 1; row <= 4; row++) {
                                            for (int slot = 0; slot <= 3; slot++) {
                                                //if ((result.isSimilar(item24) && result.getAmount() != 64) || item24 == null || item24.getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) {
                                                if (item24 == null) result.setAmount(1);
                                                ItemStack item = event.getClickedInventory().getItem(row * 9 + 1 + slot);
                                                if (item != null) item.setAmount(item.getAmount() - 1);
                                                event.getClickedInventory().setItem(row * 9 + 1 + slot, item);
                                                //}
                                            }
                                        }

                                        if (item24 != null) {
                                            if (item24.getType() != Material.LIGHT_GRAY_STAINED_GLASS_PANE) {
                                                if (result.isSimilar(item24)) {
                                                    if (item24.getAmount() != 64) {
                                                        result.setAmount(item24.getAmount() + 1);
                                                        event.getClickedInventory().setItem(24, result);
                                                    } else {
                                                        event.getWhoClicked().sendMessage(MessageUtility.FULL_CRAFTING.getMessage());
                                                        //result.setAmount(1);
                                                        event.getWhoClicked().getLocation().getWorld().dropItem(event.getWhoClicked().getLocation(), result);
                                                    }
                                                } else {
                                                    event.getWhoClicked().sendMessage(MessageUtility.FULL_CRAFTING.getMessage());
                                                    //result.setAmount(1);
                                                    event.getWhoClicked().getLocation().getWorld().dropItem(event.getWhoClicked().getLocation(), result);
                                                }
                                            } else {
                                                event.getClickedInventory().setItem(24, result);
                                            }
                                            return;
                                        }
                                        event.getClickedInventory().setItem(24, result);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (event.getClickedInventory().getType() == InventoryType.ANVIL) {
                if (event.getSlotType() == InventoryType.SlotType.RESULT &&
                        event.getCurrentItem() != null &&
                        event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                    event.setCancelled(true);
                    event.getWhoClicked().getLocation().getWorld().playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_LAND, SoundCategory.MASTER, 1, 1);
                    event.getWhoClicked().sendMessage(MessageUtility.CANNOT_RENAME_HEAD.getMessage());
                }
            }
        }
    }
}

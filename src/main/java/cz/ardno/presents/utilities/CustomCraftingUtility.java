package cz.ardno.presents.utilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class CustomCraftingUtility {

    private static final ItemStack black_stained_glass_pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private static final ItemStack lime_stained_glass_pane = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
    public static final ItemStack light_gray_stained_glass_pane = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
    private static final ItemStack bsgp_md = black_stained_glass_pane;

    public static final Map<String, Inventory> savedInventories = new HashMap<>();

    public static Inventory getInventory(String nickname) {
        if (savedInventories.containsKey(nickname)) {
            Inventory inventory = savedInventories.get(nickname);
            if (inventory.getItem(24) == null) inventory.setItem(24, light_gray_stained_glass_pane);
            return inventory;
        }

        Inventory inventory = Bukkit.createInventory(null, 54, "Present Crafting");

        for (int index = 0; index < 8; index++) inventory.setItem(index, black_stained_glass_pane);
        for (int index = 45; index < 54; index++) inventory.setItem(index, black_stained_glass_pane);
        for (int index = 9; index < 37; index += 9) inventory.setItem(index, black_stained_glass_pane);
        for (int index = 17; index < 45; index += 9) inventory.setItem(index, black_stained_glass_pane);
        for (int index = 14; index < 17; index++) inventory.setItem(index, black_stained_glass_pane);
        for (int index = 41; index < 44; index++) inventory.setItem(index, black_stained_glass_pane);
        for (int index = 23; index < 33; index += 9) inventory.setItem(index, lime_stained_glass_pane);
        for (int index = 24; index < 26; index++) inventory.setItem(index, light_gray_stained_glass_pane);
        for (int index = 33; index < 35; index++) inventory.setItem(index, light_gray_stained_glass_pane);

        inventory.setItem(8, bsgp_md);

        savedInventories.put(nickname, inventory);

        return inventory;
    }


    static {
        ItemMeta bsgp_mdm = bsgp_md.getItemMeta();

        bsgp_mdm.setCustomModelData(56);
        bsgp_md.setItemMeta(bsgp_mdm);

        ItemMeta lmgp_im = lime_stained_glass_pane.getItemMeta();
        lmgp_im.setDisplayName("§2Craft");
        lime_stained_glass_pane.setItemMeta(lmgp_im);

        ItemMeta lgsgp_im = light_gray_stained_glass_pane.getItemMeta();
        lgsgp_im.setDisplayName("§fResult");
        light_gray_stained_glass_pane.setItemMeta(lgsgp_im);
    }
}

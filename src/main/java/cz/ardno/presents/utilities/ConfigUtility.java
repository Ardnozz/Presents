package cz.ardno.presents.utilities;

import cz.ardno.presents.Presents;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.logging.Level;

public class ConfigUtility {

    public static FileConfiguration config;
    public static Map<Integer, Inventory> presentsContents = new HashMap<>();
    public static List<Integer> unsavedCustomModelData = new ArrayList<>();

    public ConfigUtility(FileConfiguration config) {
        ConfigUtility.config = config;
        Presents.instance.getLogger().log(Level.INFO, "Including config into local variable...");
        String presentsCustomModelData = config.getString("presentsCustomModelData");
        if (!Objects.equals(presentsCustomModelData, "")) {
            for (String customModelData : config.getString("presentsCustomModelData").split(", ")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "Present");
                List<ItemStack> items = (List<ItemStack>) ConfigUtility.config.getList(customModelData);
                if (items != null) {
                    for (int i = 0; i < items.size(); i++) {
                        inventory.setItem(i, items.get(i));
                    }

                    ConfigUtility.presentsContents.put(Integer.parseInt(customModelData), inventory);
                }
            }
        }
    }
}

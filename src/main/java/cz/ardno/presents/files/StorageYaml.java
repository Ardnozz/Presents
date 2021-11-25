package cz.ardno.presents.files;

import cz.ardno.presents.Presents;
import cz.ardno.presents.enumerators.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;

public class StorageYaml {

    public static Map<Integer, Inventory> presentsContents = new HashMap<>();
    public static List<Integer> unsavedCustomModelData = new ArrayList<>();

    private YamlConfiguration config;
    private File configFile;

    public StorageYaml() {
        saveDefaultConfig();
        Presents.instance.getLogger().log(Level.INFO, "Including config into local variable...");
        String presentsCustomModelData = this.getConfig().getString("presentsCustomModelData");
        if (!Objects.equals(presentsCustomModelData, "")) {
            for (String customModelData : presentsCustomModelData.split(", ")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "Present");
                List<ItemStack> items = (List<ItemStack>) this.getConfig().getList(customModelData);
                if (items != null) {
                    for (int i = 0; i < items.size(); i++) {
                        inventory.setItem(i, items.get(i));
                    }

                    StorageYaml.presentsContents.put(Integer.parseInt(customModelData), inventory);
                }
            }
        }
    }

    public void reloadConfig() {
        if (this.configFile == null) this.configFile = new File(Presents.instance.getDataFolder(), "storage.yml");

        this.config = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = Presents.instance.getResource("storage.yml");
        if (defaultStream == null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.config.setDefaults(defaultConfig);
        }
    }

    public YamlConfiguration getConfig() {
        if (this.config == null) reloadConfig();
        return config;
    }

    public void saveConfig() {
        if (this.config == null || this.configFile == null) return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            Presents.instance.getLogger().log(Level.SEVERE, MessageUtility.CANNOT_SAVE_CONFIG.getMessage());
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null) this.configFile = new File(Presents.instance.getDataFolder(), "storage.yml");

        if (!this.configFile.exists()) {
            Presents.instance.saveResource("storage.yml", false);
        }
    }
}

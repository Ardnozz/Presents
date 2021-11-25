package cz.ardno.presents;

import cz.ardno.presents.commands.PresentClaimCommand;
import cz.ardno.presents.commands.PresentGiveCommand;
import cz.ardno.presents.commands.PresentOpenCraftingCommand;
import cz.ardno.presents.files.MessagesYaml;
import cz.ardno.presents.files.StorageYaml;
import cz.ardno.presents.listeners.PlayerInteractListener;
import cz.ardno.presents.listeners.PlayerInventoryClickListener;
import cz.ardno.presents.threads.PresentsContentConfigThread;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class Presents extends JavaPlugin {

    public static Presents instance;
    private static PresentsContentConfigThread presentsContentConfigThread;
    public static StorageYaml storageYaml;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        this.getCommand("opencrafting").setExecutor(new PresentOpenCraftingCommand());
        this.getCommand("givepresent").setExecutor(new PresentGiveCommand());
        this.getCommand("claimpresents").setExecutor(new PresentClaimCommand());

        this.getServer().getPluginManager().registerEvents(new PlayerInventoryClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);

        storageYaml = new StorageYaml();
        new MessagesYaml();

        presentsContentConfigThread = new PresentsContentConfigThread();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        presentsContentConfigThread.shutdown();
        StorageYaml.unsavedCustomModelData.forEach((customModelData) -> {
            Inventory inventory = StorageYaml.presentsContents.get(customModelData);
            storageYaml.getConfig().set(String.valueOf(customModelData), (inventory == null) ? "null" : inventory.getContents());
        });
        storageYaml.saveConfig();
        StorageYaml.unsavedCustomModelData.clear();
        StorageYaml.presentsContents.clear();
    }
}

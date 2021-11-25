package cz.ardno.presents;

import cz.ardno.presents.commands.PresentClaimCommand;
import cz.ardno.presents.commands.PresentGiveCommand;
import cz.ardno.presents.commands.PresentOpenCraftingCommand;
import cz.ardno.presents.listeners.PlayerInteractListener;
import cz.ardno.presents.listeners.PlayerInventoryClickListener;
import cz.ardno.presents.threads.PresentsContentConfigThread;
import cz.ardno.presents.utilities.ConfigUtility;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class Presents extends JavaPlugin {

    public static Presents instance;
    private static PresentsContentConfigThread presentsContentConfigThread;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        this.getCommand("opencrafting").setExecutor(new PresentOpenCraftingCommand());
        this.getCommand("givepresent").setExecutor(new PresentGiveCommand());
        this.getCommand("claimpresents").setExecutor(new PresentClaimCommand());

        this.getServer().getPluginManager().registerEvents(new PlayerInventoryClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);

        this.saveDefaultConfig();
        new ConfigUtility(this.getConfig());

        presentsContentConfigThread = new PresentsContentConfigThread();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        presentsContentConfigThread.shutdown();
        ConfigUtility.unsavedCustomModelData.forEach((customModelData) -> {
            Inventory inventory = ConfigUtility.presentsContents.get(customModelData);
            ConfigUtility.config.set(String.valueOf(customModelData), (inventory == null) ? "null" : inventory.getContents());
        });
        this.saveConfig();
        ConfigUtility.unsavedCustomModelData.clear();
        ConfigUtility.presentsContents.clear();
    }
}

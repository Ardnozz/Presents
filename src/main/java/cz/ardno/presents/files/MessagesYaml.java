package cz.ardno.presents.files;

import cz.ardno.presents.Presents;
import cz.ardno.presents.enumerators.MessageUtility;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

public class MessagesYaml {

    private YamlConfiguration config;
    private File configFile;

    public MessagesYaml() {
        saveDefaultConfig();
        if (this.getConfig().getString("language").equalsIgnoreCase("own")) {
            this.getConfig().getKeys(false).forEach((key) -> {
                if (key.equals("language")) return;
                try {
                    Field field = MessageUtility.class.getField(key.toUpperCase());
                    Method method = MessageUtility.class.getDeclaredMethod("setMessage", String.class);

                    method.invoke(field.get(null), this.getConfig().getString(key));
                } catch (NoSuchFieldException e) {
                    Presents.instance.getLogger().log(Level.SEVERE, MessageUtility.CANNOT_FIND_MESSAGE_BY_KEY.getMessage());
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                    Presents.instance.getLogger().log(Level.SEVERE, MessageUtility.UNEXPECTED_ERROR.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }

    private void reloadConfig() {
        if (this.configFile == null) this.configFile = new File(Presents.instance.getDataFolder(), "messages.yml");

        this.config = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = Presents.instance.getResource("messages.yml");
        if (defaultStream == null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.config.setDefaults(defaultConfig);
        }
    }

    private YamlConfiguration getConfig() {
        if (this.config == null) reloadConfig();
        return config;
    }

    private void saveConfig() {
        if (this.config == null || this.configFile == null) return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            Presents.instance.getLogger().log(Level.SEVERE, MessageUtility.CANNOT_SAVE_CONFIG.getMessage());
        }
    }

    private void saveDefaultConfig() {
        if (this.configFile == null) this.configFile = new File(Presents.instance.getDataFolder(), "messages.yml");

        if (!this.configFile.exists()) {
            Presents.instance.saveResource("messages.yml", false);
        }
    }
}

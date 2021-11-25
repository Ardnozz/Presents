package cz.ardno.presents.threads;

import cz.ardno.presents.Presents;
import cz.ardno.presents.utilities.ConfigUtility;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class PresentsContentConfigThread {

    private final ScheduledExecutorService sec = new ScheduledThreadPoolExecutor(1);
    private final ScheduledFuture<?> thread;

    public PresentsContentConfigThread() {
        thread = sec.schedule(() -> {
            Presents.instance.getLogger().log(Level.INFO, "Automatically saving buffer into config...");
            while (true) {
                ConfigUtility.unsavedCustomModelData.forEach((customModelData) -> {
                    ConfigUtility.config.set(String.valueOf(customModelData), ConfigUtility.presentsContents.get(customModelData).getContents());
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
                Presents.instance.saveConfig();
                ConfigUtility.unsavedCustomModelData.clear();
                Thread.sleep(1000 * 60 * 10);
            }
        }, 60 * 10, TimeUnit.SECONDS);
    }

    public void shutdown() {
        Presents.instance.getLogger().log(Level.INFO, "Shutdowning automatic saver for the config buffer...");
        thread.cancel(true);
        sec.shutdown();
    }
}

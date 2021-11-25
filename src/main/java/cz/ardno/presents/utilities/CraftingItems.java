package cz.ardno.presents.utilities;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import cz.ardno.presents.Presents;
import cz.ardno.presents.enumerators.MessageUtility;
import cz.ardno.presents.files.StorageYaml;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class CraftingItems {

    public static final ItemStack cobblestone = new ItemStack(Material.COBBLESTONE);
    public static final ItemStack stone = new ItemStack(Material.STONE);
    public static final ItemStack diamond = new ItemStack(Material.DIAMOND);
    public static final ItemStack emerald = new ItemStack(Material.EMERALD);
    public static final ItemStack paper = new ItemStack(Material.PAPER);
    public static final ItemStack shulker_shell = new ItemStack(Material.SHULKER_SHELL);
    public static final ItemStack wool = new ItemStack(Material.WHITE_WOOL);
    public static final ItemStack air = new ItemStack(Material.AIR);

    public static final ItemStack black_dye = new ItemStack(Material.BLACK_DYE);
    public static final ItemStack blue_dye = new ItemStack(Material.BLUE_DYE);
    public static final ItemStack cyan_dye = new ItemStack(Material.CYAN_DYE);
    public static final ItemStack gray_dye = new ItemStack(Material.GRAY_DYE);
    public static final ItemStack green_dye = new ItemStack(Material.GREEN_DYE);
    public static final ItemStack purple_dye = new ItemStack(Material.PURPLE_DYE);
    public static final ItemStack red_dye = new ItemStack(Material.RED_DYE);
    public static final ItemStack yellow_dye = new ItemStack(Material.YELLOW_DYE);
    public static final ItemStack lime_dye = new ItemStack(Material.LIME_DYE);
    public static final ItemStack light_gray_dye = new ItemStack(Material.LIGHT_GRAY_DYE);
    public static final ItemStack pink_dye = new ItemStack(Material.PINK_DYE);
    public static final ItemStack white_dye = new ItemStack(Material.WHITE_DYE);

    public static final ItemStack black_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack blue_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack cyan_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack gray_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack green_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack purple_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack red_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack yellow_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack lime_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack light_gray_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack pink_gem = new ItemStack(Material.FIREWORK_STAR);
    public static final ItemStack white_gem = new ItemStack(Material.FIREWORK_STAR);

    public static final ItemStack wrapping_paper = new ItemStack(Material.PAPER);

    private static final ItemStack black_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack blue_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack cyan_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack gray_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack green_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack purple_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack red_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack yellow_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack lime_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack light_gray_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack pink_present = new ItemStack(Material.PLAYER_HEAD);
    private static final ItemStack white_present = new ItemStack(Material.PLAYER_HEAD);

    public static final List<ItemStack> presents = Arrays.asList(black_present, blue_present, cyan_present, gray_present, green_present, purple_present, red_present, yellow_present, lime_present, light_gray_present, pink_present, white_present);

    public static ItemStack getPresent(PresentsColors colors) {
        try {
            Field field = CraftingItems.class.getDeclaredField(colors.name().toLowerCase() + "_present");
            field.setAccessible(true);

            ItemStack present = (ItemStack) field.get(null);
            ItemMeta present_im = present.getItemMeta();

            int customModelData = 0;
            boolean con = true;

            while (con) {
                customModelData = ThreadLocalRandom.current().nextInt(100000) + 1;
                if (!Presents.storageYaml.getConfig().getString("presentsCustomModelData").contains(customModelData + colors.getColorId()) || (Presents.storageYaml.getConfig().getString(customModelData + colors.getColorId()) != null && Presents.storageYaml.getConfig().getString(customModelData + colors.getColorId()).equals("null"))) {
                    Inventory presentContent = Bukkit.createInventory(null, 27, "Present");
                    Presents.storageYaml.getConfig().set(customModelData + colors.getColorId(), presentContent.getContents());
                    Presents.storageYaml.getConfig().set("presentsCustomModelData", Presents.storageYaml.getConfig().getString("presentsCustomModelData") + customModelData + colors.getColorId() + ", ");
                    StorageYaml.presentsContents.put(Integer.parseInt(customModelData + colors.getColorId()), presentContent);
                    StorageYaml.unsavedCustomModelData.add(Integer.parseInt(customModelData + colors.getColorId()));
                    con = false;
                }
            }

            present_im.setCustomModelData(Integer.parseInt(customModelData + colors.getColorId()));
            Presents.instance.getLogger().log(Level.INFO, String.format("Created present with custom model data %s.", customModelData + colors.getColorId()));
            present.setItemMeta(present_im);

            return (ItemStack) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //Default present
        return red_present;
    }

    public static ItemStack getPresentWithCustomModelData(String customModelData) {
        String[] presentInfo = customModelData.split("-");
        PresentsColors color = PresentsColors.getColorById(presentInfo[0].substring(Math.max(presentInfo[0].length() - 2, 0)));
        try {
            Field field = CraftingItems.class.getDeclaredField(color.name().toLowerCase() + "_present");
            field.setAccessible(true);

            ItemStack present = ((ItemStack) field.get(null)).clone();
            ItemMeta present_im = present.getItemMeta();

            List<String> lore = new ArrayList<>();
            lore.add("§7From " + presentInfo[1] + " to you <3");
            present_im.setLore(lore);
            present_im.setCustomModelData(Integer.parseInt(presentInfo[0]));

            present.setItemMeta(present_im);

            return present;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void openPresentAsGUI(Player player, int customModelData) {
        Inventory inventory = StorageYaml.presentsContents.get(customModelData);
        if (inventory == null) {
            PlayerInventory playersInventory = player.getInventory();
            String customModelId = String.valueOf(customModelData);
            playersInventory.setItem(playersInventory.getHeldItemSlot(), CraftingItems.getPresent(PresentsColors.getColorById(customModelId.substring(Math.max(customModelId.length() - 2, 0)))));
            player.sendMessage(MessageUtility.PRESENT_DOESNT_EXIST.getMessage());
            return;
        }
        player.openInventory(inventory);
        StorageYaml.unsavedCustomModelData.add(customModelData);
        return;
    }

    public static void openPresent(Player player, int customModelData) {
        Inventory inventory = StorageYaml.presentsContents.get(customModelData);
        if (inventory == null) {
            PlayerInventory playersInventory = player.getInventory();
            String customModelId = String.valueOf(customModelData);
            playersInventory.setItem(playersInventory.getHeldItemSlot(), CraftingItems.getPresent(PresentsColors.getColorById(customModelId.substring(Math.max(customModelId.length() - 2, 0)))));
            player.sendMessage(MessageUtility.PRESENT_DOESNT_EXIST.getMessage());
            return;
        }
        for (ItemStack item : inventory.getContents()) {
            if (item == null) continue;
            player.getLocation().getWorld().dropItem(player.getLocation(), item);
        }

        player.getInventory().getItemInMainHand().setAmount(0);

        Presents.storageYaml.getConfig().set(String.valueOf(customModelData), "null");
        StorageYaml.presentsContents.remove(customModelData);
        StorageYaml.unsavedCustomModelData.remove((Object) customModelData);
        return;
    }

    static {
        try {
            ItemMeta wrapping_paper_im = wrapping_paper.getItemMeta();
            wrapping_paper_im.setDisplayName("§fWrapping Paper");
            wrapping_paper.setItemMeta(wrapping_paper_im);

            //region Presents
            for (PresentsColors pscs : PresentsColors.values()) {
                try {
                    Field field = CraftingItems.class.getDeclaredField(pscs.name().toLowerCase() + "_present");
                    SkullMeta present_sm = (SkullMeta) ((ItemStack) field.get(null)).getItemMeta();
                    GameProfile present_sm_profile = new GameProfile(UUID.randomUUID(), null);

                    present_sm_profile.getProperties().put("textures", new Property("textures", pscs.getTextureValue()));

                    try {
                        Method method = present_sm.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                        method.setAccessible(true);
                        method.invoke(present_sm, present_sm_profile);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    String[] colorsNames = pscs.name().toLowerCase().split("_");
                    String correctColorName = "";

                    for (String colorName : colorsNames) {
                        correctColorName += colorName.substring(0, 1).toUpperCase() + colorName.substring(1) + " ";
                    }

                    correctColorName = correctColorName.trim();
                    present_sm.setDisplayName(ChatColor.of(pscs.getCustomColorModifier()) + correctColorName + " Present");

                    ((ItemStack) field.get(null)).setItemMeta(present_sm);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //endregion

            //region Firework Stars Colored Names //TODO optimalizace
            ItemMeta black_gem_im = black_gem.getItemMeta();
            black_gem_im.setDisplayName("§0Black Gem");
            black_gem.setItemMeta(black_gem_im);

            ItemMeta blue_gem_im = blue_gem.getItemMeta();
            blue_gem_im.setDisplayName("§1Blue Gem");
            blue_gem.setItemMeta(blue_gem_im);

            ItemMeta cyan_gem_im = cyan_gem.getItemMeta();
            cyan_gem_im.setDisplayName("§3Cyan Gem");
            cyan_gem.setItemMeta(cyan_gem_im);

            ItemMeta gray_gem_im = gray_gem.getItemMeta();
            gray_gem_im.setDisplayName("§8Gray Gem");
            gray_gem.setItemMeta(gray_gem_im);

            ItemMeta green_gem_im = green_gem.getItemMeta();
            green_gem_im.setDisplayName("§2Green Gem");
            green_gem.setItemMeta(green_gem_im);

            ItemMeta purple_gem_im = purple_gem.getItemMeta();
            purple_gem_im.setDisplayName("§5Purple Gem");
            purple_gem.setItemMeta(purple_gem_im);

            ItemMeta red_gem_im = red_gem.getItemMeta();
            red_gem_im.setDisplayName("§4Red Gem");
            red_gem.setItemMeta(red_gem_im);

            ItemMeta yellow_gem_im = yellow_gem.getItemMeta();
            yellow_gem_im.setDisplayName("§eYellow Gem");
            yellow_gem.setItemMeta(yellow_gem_im);

            ItemMeta lime_gem_im = lime_gem.getItemMeta();
            lime_gem_im.setDisplayName("§aLime Gem");
            lime_gem.setItemMeta(lime_gem_im);

            ItemMeta light_gray_gem_im = light_gray_gem.getItemMeta();
            light_gray_gem_im.setDisplayName("§7Light Gray Gem");
            light_gray_gem.setItemMeta(light_gray_gem_im);

            ItemMeta pink_gem_im = pink_gem.getItemMeta();
            pink_gem_im.setDisplayName("§dPink Gem");
            pink_gem.setItemMeta(pink_gem_im);

            ItemMeta white_gem_im = white_gem.getItemMeta();
            white_gem_im.setDisplayName("§fWhite Gem");
            white_gem.setItemMeta(white_gem_im);
            //endregion

            //region Firework Stars Color Settings //TODO optimalizace
            FireworkEffectMeta black_gem_fem = (FireworkEffectMeta) black_gem.getItemMeta();
            black_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.BLACK.getCustomIntColorModifier())).build());
            black_gem.setItemMeta(black_gem_fem);

            FireworkEffectMeta blue_gem_fem = (FireworkEffectMeta) blue_gem.getItemMeta();
            blue_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.BLUE.getCustomIntColorModifier())).build());
            blue_gem.setItemMeta(blue_gem_fem);

            FireworkEffectMeta cyan_gem_fem = (FireworkEffectMeta) cyan_gem.getItemMeta();
            cyan_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.CYAN.getCustomIntColorModifier())).build());
            cyan_gem.setItemMeta(cyan_gem_fem);

            FireworkEffectMeta gray_gem_fem = (FireworkEffectMeta) gray_gem.getItemMeta();
            gray_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.GRAY.getCustomIntColorModifier())).build());
            gray_gem.setItemMeta(gray_gem_fem);

            FireworkEffectMeta green_gem_fem = (FireworkEffectMeta) green_gem.getItemMeta();
            green_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.GREEN.getCustomIntColorModifier())).build());
            green_gem.setItemMeta(green_gem_fem);

            FireworkEffectMeta purple_gem_fem = (FireworkEffectMeta) purple_gem.getItemMeta();
            purple_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.PURPLE.getCustomIntColorModifier())).build());
            purple_gem.setItemMeta(purple_gem_fem);

            FireworkEffectMeta red_gem_fem = (FireworkEffectMeta) red_gem.getItemMeta();
            red_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.RED.getCustomIntColorModifier())).build());
            red_gem.setItemMeta(red_gem_fem);

            FireworkEffectMeta yellow_gem_fem = (FireworkEffectMeta) yellow_gem.getItemMeta();
            yellow_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.YELLOW.getCustomIntColorModifier())).build());
            yellow_gem.setItemMeta(yellow_gem_fem);

            FireworkEffectMeta lime_gem_fem = (FireworkEffectMeta) lime_gem.getItemMeta();
            lime_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.LIME.getCustomIntColorModifier())).build());
            lime_gem.setItemMeta(lime_gem_fem);

            FireworkEffectMeta light_gray_gem_fem = (FireworkEffectMeta) light_gray_gem.getItemMeta();
            light_gray_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.LIGHT_GRAY.getCustomIntColorModifier())).build());
            light_gray_gem.setItemMeta(light_gray_gem_fem);

            FireworkEffectMeta pink_gem_fem = (FireworkEffectMeta) pink_gem.getItemMeta();
            pink_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.PINK.getCustomIntColorModifier())).build());
            pink_gem.setItemMeta(pink_gem_fem);

            FireworkEffectMeta white_gem_fem = (FireworkEffectMeta) white_gem.getItemMeta();
            white_gem_fem.setEffect(FireworkEffect.builder().withColor(Color.fromRGB(PresentsColors.WHITE.getCustomIntColorModifier())).build());
            white_gem.setItemMeta(white_gem_fem);
            //endregion
        } catch (Exception exx) {
            exx.printStackTrace();
        }


    }

}

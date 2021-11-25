package cz.ardno.presents.utilities;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum PresentsColors {

    BLACK("#1E1B1B", "00", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDA3ZDMyMjQxOWNhMGNjMTlmZmFiNzBkMTVhMTU0NTQ4MTM0YWEwMjEwNjdmN2NjMTBkYjUxN2EwMjc4Mzc4MiJ9fX0"),
    BLUE("#253192", "01", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE2YTEyMzY1NDFjZjQ5NjBiMzdhMWU5MTdhYjYzZWY0OTk0YmM1MTg2OWZmZGZiZmYwNzRkYTQ1NzNhNzdhYSJ9fX0"),
    CYAN("#287697", "02", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODJiYmU4ZWFjNGFiMzA3MjQyMzZlYTY1M2E1YTlkYTVjZWFhZjVmZmQ5ZjA5OTQ4MTBmNzlhYWUxNTJmMTMyOSJ9fX0"),
    GRAY("#434343", "03", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGM5YjI5NGJhYzY5NmE3MmE4YjRmYzg5Y2M2ZDIwOTVlZTM3YTc3NzFiZjJjMzhkOWFkNTMyZGIyNDMzNDk0ZSJ9fX0"),
    GREEN("#00A800", "04", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDc4OTIyYjI1MDczMTQ3NzU4Zjk3MTM3NGIzOTViODcxMmVmYzFjZDhkZWMwYTNhYjBjZWI4NWI1OTQ4MzIwNSJ9fX0"),
    PURPLE("#7B2FBE", "05", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWEzNGMwMzE5ZWZjMjVmYTNlMGY2YjhiOTRhODY5NjY1MTBhMzJlMWQ3NzZiYWNlZWQ3ZDBiMmVkMzQxNDEwOCJ9fX0"),
    RED("#B3312C", "06", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQwYWZiNGEyNjk5OWE1MDUwM2RhMTA3N2YxMmJjMGNkZjFlZjQ5NWNmMGYxYWNiY2NiNjA5OTgwNTUxYzEzYyJ9fX0"),
    YELLOW("#DECF2A", "07", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWU5OGMwZTBkMzYxZDJlYTdhY2I2NzJkNGY3ZjhlZTJiZGFiMTNjZjRlMzgyOGE3ZGQ4NGNkY2NmYTZmNTA4MCJ9fX0"),
    LIME("#41CD34", "08", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjFkNWYyNWZlYjBkZDI3OTM1Y2ZhNWUyMmJjYWIyYzY0OTI3NDI1ZTc2NTY0MWM4NDNmY2U0N2I1YmVmZTI1OCJ9fX0"),
    LIGHT_GRAY("#ABABAB", "09", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg4MTJjNmUxNDA0MzcwMjQ0ZTJmZGMzOTZhYWVlODQ2ODgyNzkxODM1ZmMxNWZhN2Y2ZTU5MzkxN2MxMmZjZSJ9fX0"),
    PINK("#D88198", "10", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWExNWMyNGI3ZGJkNWE2ZmE0MWVkMTc5Zjc5ZjUyOTVmZTI2ZmEzNWFjNjk4NzYxNTM3MGYxZTcxNGM1MzllZCJ9fX0"),
    WHITE("#F0F0F0", "11", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGMzNGRhZWYxZmFlOTE1NTZjN2RlOTU2NDAzM2RmMjUxYzNiM2Y5ZWMxZjcyNDBjZjA0OTQyNTliMWFiYTA3NyJ9fX0");

    private final String textureValue;
    private final String colorId;
    private final String customColorModifier;

    PresentsColors(String customColorModifier, String colorId, String textureValue) {
        this.customColorModifier = customColorModifier;
        this.colorId = colorId;
        this.textureValue = textureValue;
    }

    public String getTextureValue() {
        return textureValue;
    }

    public String getColorId() {
        return colorId;
    }

    public String getCustomColorModifier() {
        return customColorModifier;
    }

    public int getCustomIntColorModifier() {
        String hexString = "0x" + customColorModifier.replace("#", "").toLowerCase();
        return Integer.decode(hexString);
    }

    public static PresentsColors getColorById(String id) {
        return Arrays.stream(PresentsColors.values()).filter((color) -> color.colorId.equals(id)).collect(Collectors.toList()).get(0);
    }
}

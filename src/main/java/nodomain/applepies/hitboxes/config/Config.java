package nodomain.applepies.hitboxes.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {

    private static Configuration config;

    public static boolean hitboxState;
    public static boolean rememberState;
    public static boolean showSelf;
    public static double width;

    public static boolean playerShowHitbox;
    public static boolean playerShowLook;
    public static boolean playerShowEye;
    public static int playerColor;
    public static int playerColorMode;

    public static boolean projectileShowHitbox;
    public static boolean projectileShowLook;
    public static int projectileColor;
    public static int projectileColorMode;
    public static boolean projectileGrounded;
    public static boolean projectileAttached;

    public static boolean mobShowHitbox;
    public static boolean mobShowLook;
    public static boolean mobShowEye;
    public static int mobColor;


    public static boolean otherShowHitbox;
    public static boolean otherShowLook;
    public static int otherColor;

    public static void loadConfig(File file) {
        config = new Configuration(file);
        config.load();

        hitboxState = config.get("general", "hitboxState", true).getBoolean();
        rememberState = config.get("general", "rememberState", true).getBoolean();
        showSelf = config.get("general", "showSelf", true).getBoolean();
        width = config.get("general", "width", 2.0).getDouble();

        playerColor = config.get("player", "color", 0xFFFFFF).getInt();
        playerShowHitbox = config.get("player", "showHitbox", true).getBoolean();
        playerShowLook = config.get("player", "showLook", true).getBoolean();
        playerShowEye = config.get("player", "showEye", true).getBoolean();
        playerColorMode = config.get("player", "colorMode", 0).getInt();

        projectileColor = config.get("projectile", "color", 0xFFFFFF).getInt();
        projectileShowHitbox = config.get("projectile", "showHitbox", true).getBoolean();
        projectileShowLook = config.get("projectile", "showLook", true).getBoolean();
        projectileColorMode = config.get("projectile", "colorMode", 0).getInt();
        projectileGrounded = config.get("projectile", "grounded", true).getBoolean();
        projectileAttached = config.get("projectile", "attached", true).getBoolean();

        mobColor = config.get("mob", "color", 0xFFFFFF).getInt();
        mobShowHitbox = config.get("mob", "showHitbox", true).getBoolean();
        mobShowLook = config.get("mob", "showLook", true).getBoolean();
        mobShowEye = config.get("mob", "showEye", true).getBoolean();

        otherColor = config.get("other", "color", 0xFFFFFF).getInt();
        otherShowHitbox = config.get("other", "showHitbox", true).getBoolean();
        otherShowLook = config.get("other", "showLook", true).getBoolean();
    }

    public static void saveConfig() {
        config.get("general", "hitboxState", true).setValue(hitboxState);
        config.get("general", "rememberState", true).setValue(rememberState);
        config.get("general", "showSelf", true).setValue(showSelf);
        config.get("general", "width", 2.0).setValue(width);

        config.get("player", "showHitbox", true).setValue(playerShowHitbox);
        config.get("player", "showLook", true).setValue(playerShowLook);
        config.get("player", "showEye", true).setValue(playerShowEye);
        config.get("player", "color", 16777215).setValue(playerColor);
        config.get("player", "colorMode", 0).setValue(playerColorMode);

        config.get("projectile", "showHitbox", true).setValue(projectileShowHitbox);
        config.get("projectile", "showLook", true).setValue(projectileShowLook);
        config.get("projectile", "color", 16777215).setValue(projectileColor);
        config.get("projectile", "colorMode", 0).setValue(projectileColorMode);
        config.get("projectile", "grounded", true).setValue(projectileGrounded);
        config.get("projectile", "attached", true).setValue(projectileAttached);

        config.get("mob", "showHitbox", true).setValue(mobShowHitbox);
        config.get("mob", "showLook", true).setValue(mobShowLook);
        config.get("mob", "showEye", true).setValue(mobShowEye);
        config.get("mob", "color", 16777215).setValue(mobColor);

        config.get("other", "showHitbox", true).setValue(otherShowHitbox);
        config.get("other", "showLook", true).setValue(otherShowLook);
        config.get("other", "color", 16777215).setValue(otherColor);

        if (config.hasChanged()) {
            config.save();
        }
    }
}
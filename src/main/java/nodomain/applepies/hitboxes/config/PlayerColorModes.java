package nodomain.applepies.hitboxes.config;

public enum PlayerColorModes {
    DEFAULT("§bDefault"),
    NAMETAG("§bNametag"),
    RED_GREEN("§bRed & Green"),
    PING("§bPing");

    public final String str;

    PlayerColorModes(String str) {
        this.str = str;
    }
}
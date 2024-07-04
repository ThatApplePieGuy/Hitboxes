package nodomain.applepies.hitboxes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;

public final class Util {

    public static final Minecraft mc = Minecraft.getMinecraft();

    public static String enabled(boolean enabled) {
        return (enabled) ? "§aOn" : "§cOff";
    }

    public static int recolor(int i, int component, int value) {
        int r = i / 65536;
        int g = i / 256 % 256;
        int b = i % 256;

        switch (component) {
            case 0:
                r = value;
                break;
            case 1:
                g = value;
                break;
            case 2:
                b = value;
        }

        return r * 256 * 256 + g * 256 + b;
    }

    public static boolean canRenderNametag(EntityPlayer player) {
        Minecraft mc = Minecraft.getMinecraft();

        Team team = player.getTeam();
        Team ownTeam = mc.thePlayer.getTeam();

        if (team != null) {
            Team.EnumVisible teamVisibility = team.getNameTagVisibility();
            switch (teamVisibility) {
                case ALWAYS: {break;}
                case NEVER: {return false;}
                case HIDE_FOR_OTHER_TEAMS: {if (!(ownTeam == null || team.isSameTeam(ownTeam))) return false;}
                case HIDE_FOR_OWN_TEAM: {if (!(ownTeam == null || !team.isSameTeam(ownTeam))) return false;}
            }
        }

        double distance = player.getDistanceSqToEntity(mc.thePlayer);
        double nametagDistance = player.isSneaking() ? 32 * 32 : 64 * 64;
        return distance < nametagDistance;
    }

    public static int getNametagHex(EntityPlayer player, int color) {
        ScorePlayerTeam scorePlayerTeam = (ScorePlayerTeam) (player).getTeam();
        if (scorePlayerTeam != null) {
            String format = FontRenderer.getFormatFromString(scorePlayerTeam.getColorPrefix());
            if (format.length() >= 2) color = mc.fontRendererObj.getColorCode(format.charAt(1));
        }

        return color;
    }

    public static int getPingHex(EntityPlayer player, int color) {
        NetHandlerPlayClient netHandlerPlayClient = mc.thePlayer.sendQueue;
        NetworkPlayerInfo npi = netHandlerPlayClient.getPlayerInfo(player.getUniqueID());
        if (npi != null) {
            int ping = npi.getResponseTime();
            if (ping > 200) { // dark red
                color = 0xAA0000;
            } else if (ping > 150) { // red
                color = 0xFF5555;
            } else if (ping > 100) { // yellow
                color = 0xFFFF55;
            } else if (ping > 50) { // dark green
                color = 0x00AA00;
            } else if (ping > 0) { // green
                color = 0x55FF55;
            }
        }

        return color;
    }

}

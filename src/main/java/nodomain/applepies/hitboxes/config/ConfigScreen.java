package nodomain.applepies.hitboxes.config;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiSlider;

import static nodomain.applepies.hitboxes.Util.*;

public class ConfigScreen extends GuiScreen {
    GuiButton rememberState, showSelf;
    GuiButton playerShowHitbox, playerShowLook, playerShowEye, playerColorMode;
    GuiButton projectileShowHitbox, projectileShowLook, projectileGrounded, projectileAttached, projectileColorMode;
    GuiButton mobShowHitbox, mobShowLook, mobShowEye;
    GuiButton otherShowHitbox, otherShowLook;

    @Override
    public void initGui() {
        super.initGui();

        int i = 0, row;

        int[] col = new int[5];
        for (int j = 0; j < 4; j++) {
            col[j] = (j+1) * width / 5 - 60;
        }

        // general
        rememberState = new GuiButton(i++, width - 122, height - 22, 120, 20, "Remember F3+B: " + enabled(Config.rememberState));
        showSelf = new GuiButton(i++, width - 244, height - 22, 120, 20, "Show Self: " + enabled(Config.showSelf));
        buttonList.add(rememberState);
        buttonList.add(showSelf);

        buttonList.add(new GuiSlider(i++, width - 122, height - 44, 120, 20, "Width: ", "", 0.1, 3.0, Config.width, true, true,
                slider -> Config.width =  Math.floor(slider.getValue() * 10) / 10));

        // player
        row = height / 2 - 78;

        buttonList.add(new GuiSlider(i++, col[0], row += 24, 120, 20, "Red: ", "", 0, 255, Config.playerColor / 65536, false, true,
                slider -> Config.playerColor = recolor(Config.playerColor, 0, slider.getValueInt())));
        buttonList.add(new GuiSlider(i++, col[0], row += 24, 120, 20, "Green: ", "", 0, 255, Config.playerColor / 256 % 256, false, true,
                slider -> Config.playerColor = recolor(Config.playerColor, 1, slider.getValueInt())));
        buttonList.add(new GuiSlider(i++, col[0], row += 24, 120, 20, "Blue: ", "", 0, 255, Config.playerColor % 256, false, true,
                slider -> Config.playerColor = recolor(Config.playerColor, 2, slider.getValueInt())));

        playerShowHitbox = new GuiButton(i++, col[0], row += 24, 120, 20, "Hitbox: " + enabled(Config.playerShowHitbox));
        playerShowLook = new GuiButton(i++, col[0], row += 24, 120, 20, "Look Vector: " + enabled(Config.playerShowLook));
        playerShowEye = new GuiButton(i++, col[0], row += 24, 120, 20, "Eye Height: " + enabled(Config.playerShowEye));
        playerColorMode = new GuiButton(i++, col[0], row += 24, 120, 20, "Color: " + PlayerColorModes.values()[Config.playerColorMode].str);

        buttonList.add(playerShowHitbox);
        buttonList.add(playerShowLook);
        buttonList.add(playerShowEye);
        buttonList.add(playerColorMode);

        // projectile
        row = height / 2 - 78;

        buttonList.add(new GuiSlider(i++, col[1], row += 24, 120, 20, "Red: ", "", 0, 255, Config.projectileColor / 65536, false, true,
                slider -> Config.projectileColor = recolor(Config.projectileColor, 0, slider.getValueInt())));
        buttonList.add(new GuiSlider(i++, col[1], row += 24, 120, 20, "Green: ", "", 0, 255, Config.projectileColor / 256 % 256, false, true,
                slider -> Config.projectileColor = recolor(Config.projectileColor, 1, slider.getValueInt())));
        buttonList.add(new GuiSlider(i++, col[1], row += 24, 120, 20, "Blue: ", "", 0, 255, Config.projectileColor % 256, false, true,
                slider -> Config.projectileColor = recolor(Config.projectileColor, 2, slider.getValueInt())));

        projectileShowHitbox = new GuiButton(i++, col[1], row += 24, 120, 20, "Hitbox: " + enabled(Config.projectileShowHitbox));
        projectileShowLook = new GuiButton(i++, col[1], row += 24, 120, 20, "Look Vector: " + enabled(Config.projectileShowLook));
        projectileGrounded = new GuiButton(i++, col[1], row += 24, 120, 20, "Grounded Arrows: " + enabled(Config.projectileGrounded));
        projectileAttached = new GuiButton(i++, col[1], row += 24, 120, 20, "Attached Arrows: " + enabled(Config.projectileAttached));
        projectileColorMode = new GuiButton(i++, col[1], row += 24, 120, 20, "Color: " + ProjectileColorModes.values()[Config.projectileColorMode].str);

        buttonList.add(projectileShowHitbox);
        buttonList.add(projectileShowLook);
        buttonList.add(projectileGrounded);
        buttonList.add(projectileAttached);
        buttonList.add(projectileColorMode);

        // mob
        row = height / 2 - 78;

        buttonList.add(new GuiSlider(i++, col[2], row += 24, 120, 20, "Red: ", "", 0, 255, Config.mobColor / 65536, false, true,
                slider -> Config.mobColor = recolor(Config.mobColor, 0, slider.getValueInt())));
        buttonList.add(new GuiSlider(i++, col[2], row += 24, 120, 20, "Green: ", "", 0, 255, Config.mobColor / 256 % 256, false, true,
                slider -> Config.mobColor = recolor(Config.mobColor, 1, slider.getValueInt())));
        buttonList.add(new GuiSlider(i++, col[2], row += 24, 120, 20, "Blue: ", "", 0, 255, Config.mobColor % 256, false, true,
                slider -> Config.mobColor = recolor(Config.mobColor, 2, slider.getValueInt())));

        mobShowHitbox = new GuiButton(i++, col[2], row += 24, 120, 20, "Hitbox: " + enabled(Config.mobShowHitbox));
        mobShowLook = new GuiButton(i++, col[2], row += 24, 120, 20, "Look Vector: " + enabled(Config.mobShowLook));
        mobShowEye = new GuiButton(i++, col[2], row += 24, 120, 20, "Eye Height: " + enabled(Config.mobShowEye));

        buttonList.add(mobShowHitbox);
        buttonList.add(mobShowLook);
        buttonList.add(mobShowEye);

        // other
        row = height / 2 - 78;

        buttonList.add(new GuiSlider(i++, col[3], row += 24, 120, 20, "Red: ", "", 0, 255, Config.otherColor / 65536, false, true,
                slider -> Config.otherColor = recolor(Config.otherColor, 0, slider.getValueInt())));
        buttonList.add(new GuiSlider(i++, col[3], row += 24, 120, 20, "Green: ", "", 0, 255, Config.otherColor / 256 % 256, false, true,
                slider -> Config.otherColor = recolor(Config.otherColor, 1, slider.getValueInt())));
        buttonList.add(new GuiSlider(i++, col[3], row += 24, 120, 20, "Blue: ", "", 0, 255, Config.otherColor % 256, false, true,
                slider -> Config.otherColor = recolor(Config.otherColor, 2, slider.getValueInt())));

        otherShowHitbox = new GuiButton(i++, col[3], row += 24, 120, 20, "Hitbox: " + enabled(Config.otherShowHitbox));
        otherShowLook = new GuiButton(i++, col[3], row += 24, 120, 20, "Look Vector: " + enabled(Config.otherShowLook));

        buttonList.add(otherShowHitbox);
        buttonList.add(otherShowLook);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        int ord;

        if (button == rememberState) {
            Config.rememberState = !Config.rememberState;
            button.displayString = "Remember F3+B: " + enabled(Config.rememberState);

        } else if (button == showSelf) {
            Config.showSelf = !Config.showSelf;
            button.displayString = "Show Self: " + enabled(Config.showSelf);

        } else if (button == playerShowHitbox) {
            Config.playerShowHitbox = !Config.playerShowHitbox;
            button.displayString = "Hitbox: " + enabled(Config.playerShowHitbox);

        } else if (button == playerShowLook) {
            Config.playerShowLook = !Config.playerShowLook;
            button.displayString = "Look Vector: " + enabled(Config.playerShowLook);

        } else if (button == playerShowEye) {
            Config.playerShowEye = !Config.playerShowEye;
            button.displayString = "Eye Height: " + enabled(Config.playerShowEye);

        } else if (button == playerColorMode) {
            ord = ++Config.playerColorMode % PlayerColorModes.values().length;
            Config.playerColorMode = PlayerColorModes.values()[ord].ordinal();
            button.displayString = "Color: " + PlayerColorModes.values()[ord].str;

        } else if (button == projectileShowHitbox) {
            Config.projectileShowHitbox = !Config.projectileShowHitbox;
            button.displayString = "Hitbox: " + enabled(Config.projectileShowHitbox);

        } else if (button == projectileShowLook) {
            Config.projectileShowLook = !Config.projectileShowLook;
            button.displayString = "Look Vector: " + enabled(Config.projectileShowHitbox);

        } else if (button == projectileGrounded) {
            Config.projectileGrounded = !Config.projectileGrounded;
            button.displayString = "Grounded Arrows: " + enabled(Config.projectileGrounded);

        } else if (button == projectileAttached) {
            Config.projectileAttached = !Config.projectileAttached;
            button.displayString = "Attached Arrows: " + enabled(Config.projectileAttached);

        } else if (button == projectileColorMode) {
            ord = ++Config.projectileColorMode % ProjectileColorModes.values().length;
            Config.projectileColorMode = ProjectileColorModes.values()[ord].ordinal();
            button.displayString = "Color: " + ProjectileColorModes.values()[ord].str;

        } else if (button == mobShowHitbox) {
            Config.mobShowHitbox = !Config.mobShowHitbox;
            button.displayString = "Hitbox: " + enabled(Config.mobShowHitbox);

        } else if (button == mobShowLook) {
            Config.mobShowLook = !Config.mobShowLook;
            button.displayString = "Look Vector: " + enabled(Config.mobShowLook);

        } else if (button == mobShowEye) {
            Config.mobShowEye = !Config.mobShowEye;
            button.displayString = "Eye Height: " + enabled(Config.mobShowEye);

        } else if (button == otherShowHitbox) {
            Config.otherShowHitbox = !Config.otherShowHitbox;
            button.displayString = "Hitbox: " + enabled(Config.otherShowHitbox);

        } else if (button == otherShowLook) {
            Config.otherShowLook = !Config.otherShowLook;
            button.displayString = "Look Vector: " + enabled(Config.otherShowLook);
        }

        Config.saveConfig();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        int[] col = new int[5];
        for (int j = 0; j < 4; j++) {
            col[j] = (j+1) * width / 5;
        }

        int row = height / 2 - 84;

        drawCenteredString(fontRendererObj, "Players", col[0], row - 20, 0xFFFFFF);
        drawCenteredString(fontRendererObj, "Projectiles", col[1], row - 20, 0xFFFFFF);
        drawCenteredString(fontRendererObj, "Mobs", col[2], row - 20, 0xFFFFFF);
        drawCenteredString(fontRendererObj, "Others", col[3], row - 20, 0xFFFFFF);

        mc.getTextureManager().bindTexture(new ResourceLocation("textures/entity/steve.png"));
        drawModalRectWithCustomSizedTexture(col[0] - 8, row - 8, 16, 16, 16, 16, 128, 128);
        mc.getTextureManager().bindTexture(new ResourceLocation("textures/items/arrow.png"));
        drawModalRectWithCustomSizedTexture(col[1] - 8, row - 8, 0, 0, 16, 16, 16, 16);
        mc.getTextureManager().bindTexture(new ResourceLocation("textures/entity/creeper/creeper.png"));
        drawModalRectWithCustomSizedTexture(col[2] - 8, row - 8, 16, 16, 16, 16, 128, 64);
        mc.getTextureManager().bindTexture(new ResourceLocation("textures/items/minecart_normal.png"));
        drawModalRectWithCustomSizedTexture(col[3] - 8, row - 8, 0, 0, 16, 16, 16, 16);

        drawRect(col[0] - 58, row + 12, col[0] + 58, row + 26, 0xFF000000 | Config.playerColor);
        drawRect(col[1] - 58, row + 12, col[1] + 58, row + 26, 0xFF000000 | Config.projectileColor);
        drawRect(col[2] - 58, row + 12, col[2] + 58, row + 26, 0xFF000000 | Config.mobColor);
        drawRect(col[3] - 58, row + 12, col[3] + 58, row + 26, 0xFF000000 | Config.otherColor);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        Config.saveConfig();
    }
}
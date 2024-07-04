package nodomain.applepies.hitboxes.config;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import nodomain.applepies.hitboxes.HitboxesMod;

public class ConfigCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "hitboxes";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        HitboxesMod.nextScreen = new ConfigScreen();
        Config.saveConfig();
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
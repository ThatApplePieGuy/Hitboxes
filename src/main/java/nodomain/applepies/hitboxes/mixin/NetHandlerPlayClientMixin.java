package nodomain.applepies.hitboxes.mixin;

import nodomain.applepies.hitboxes.ProjectileAccessor;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static nodomain.applepies.hitboxes.Util.mc;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {

    @Inject(method = "handleSpawnObject(Lnet/minecraft/network/play/server/S0EPacketSpawnObject;)V", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void hitboxes$projectileOwner(S0EPacketSpawnObject packetIn, CallbackInfo ci, double d0, double d1, double d2, Entity entity) {
        if (entity instanceof EntityThrowable || entity instanceof EntityArrow || entity instanceof EntityFishHook) {
            EntityPlayer shooter = mc.theWorld.getClosestPlayer(entity.posX, entity.posY - 1, entity.posZ, 4); // TODO: correct height
            if (shooter != null) ((ProjectileAccessor) entity).hitboxes$setOwner(shooter.getUniqueID());
        }
    }
}

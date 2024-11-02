package nodomain.applepies.hitboxes.mixin;

import net.minecraft.entity.projectile.*;
import nodomain.applepies.hitboxes.ProjectileAccessor;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import nodomain.applepies.hitboxes.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {

    @Inject(method = "handleSpawnObject(Lnet/minecraft/network/play/server/S0EPacketSpawnObject;)V", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void hitboxes$projectileOwner(S0EPacketSpawnObject packetIn, CallbackInfo ci, double d0, double d1, double d2, Entity entity) {
        if (!(entity instanceof ProjectileAccessor)) return;

        Entity owner = null;

        if (entity instanceof EntityArrow) {
            owner = ((EntityArrow) entity).shootingEntity;
        } else if (entity instanceof EntityFishHook) {
            owner = ((EntityFishHook) entity).angler;
        } else if (entity instanceof EntityThrowable || entity instanceof EntityFireball) {
            owner = Util.getNearestLivingEntity(entity);
        }

        if (owner != null) {
            ((ProjectileAccessor) entity).hitboxes$setOwner(owner.getUniqueID());
        }
    }
}

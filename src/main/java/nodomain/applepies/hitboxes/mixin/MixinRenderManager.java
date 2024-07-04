package nodomain.applepies.hitboxes.mixin;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;
import nodomain.applepies.hitboxes.ProjectileAccessor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import nodomain.applepies.hitboxes.config.Config;
import nodomain.applepies.hitboxes.config.PlayerColorModes;
import nodomain.applepies.hitboxes.config.ProjectileColorModes;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static nodomain.applepies.hitboxes.Util.*;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;

@Mixin(RenderManager.class)
public class MixinRenderManager {
    @Shadow
    public World worldObj;

    @Inject(method = "setDebugBoundingBox(Z)V", at = @At("HEAD"))
    private void hitboxes$rememberState(boolean debugBoundingBoxIn, CallbackInfo ci) {
        Config.hitboxState = debugBoundingBoxIn;
        Config.saveConfig();
    }

    @Redirect(method = "doRenderEntity(Lnet/minecraft/entity/Entity;DDDFFZ)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;renderDebugBoundingBox(Lnet/minecraft/entity/Entity;DDDFF)V"))
    private void hitboxes$hitboxLogic(RenderManager instance, Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        GlStateManager.depthMask(false);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GL11.glLineWidth((float) Config.width);

        if (entity instanceof EntityPlayer) { // player
            if (entity != mc.thePlayer || Config.showSelf) {
                if (Config.playerShowHitbox) hitboxes$drawHitbox(entity, x, y, z);
                if (Config.playerShowLook) hitboxes$drawLookVector(entity, x, y, z, partialTicks);
                if (Config.playerShowEye) hitboxes$drawEyeHeight(entity, x, y, z);
            }

        } else if (entity instanceof ProjectileAccessor) { // projectile
            if (!(entity instanceof EntityArrow && entity.motionX == 0 && entity.motionY == 0 && entity.motionZ == 0 && !Config.projectileAttached)) {
                if (!(entity instanceof EntityArrow && ((ArrowStateAccessor) entity).getInGround()) || Config.projectileGrounded) {
                    if (Config.projectileShowHitbox) hitboxes$drawHitbox(entity, x, y, z);
                    if (Config.projectileShowLook) hitboxes$drawLookVector(entity, x, y, z, partialTicks);
                }
            }

        } else if (entity instanceof EntityLiving) { // mob
            if (Config.mobShowHitbox) hitboxes$drawHitbox(entity, x, y, z);
            if (Config.mobShowLook) hitboxes$drawLookVector(entity, x, y, z, partialTicks);
            if (Config.mobShowEye) hitboxes$drawEyeHeight(entity, x, y, z);

        } else { // other
            if (Config.otherShowHitbox) hitboxes$drawHitbox(entity, x, y, z);
            if (Config.otherShowLook) hitboxes$drawLookVector(entity, x, y, z, partialTicks);
        }

        GL11.glLineWidth(1);
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
    }

    @Unique
    private void hitboxes$drawHitbox(Entity entity, double x, double y, double z) {
        int color;

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            color = hitboxes$getPlayerColor(player);

        } else if (entity instanceof ProjectileAccessor) {
            color = Config.projectileColor;
            if (Config.projectileColorMode == ProjectileColorModes.SHOOTER.ordinal()) {
                ProjectileAccessor pa = (ProjectileAccessor) entity;
                if (pa.hitboxes$hasColor()) {
                    color = pa.hitboxes$getColor();
                } else if (pa.hitboxes$hasOwner()) {
                    EntityPlayer owner = worldObj.getPlayerEntityByUUID(pa.hitboxes$getOwner());
                    if (owner != null) {
                        color = hitboxes$getPlayerColor(owner);
                        pa.hitboxes$setColor(color);
                    }
                }
            }

        } else if (entity instanceof EntityLiving) {
            color = Config.mobColor;

        } else {
            color = Config.otherColor;
        }

        AxisAlignedBB aabb0 = entity.getEntityBoundingBox();
        AxisAlignedBB aabb = new AxisAlignedBB(aabb0.minX - entity.posX + x, aabb0.minY - entity.posY + y, aabb0.minZ - entity.posZ + z, aabb0.maxX - entity.posX + x, aabb0.maxY - entity.posY + y, aabb0.maxZ - entity.posZ + z);

        int r = color / 65536;
        int g = color / 256 % 256;
        int b = color % 256;
        RenderGlobal.drawOutlinedBoundingBox(aabb, r, g, b, 255);
    }

    @Unique
    private void hitboxes$drawLookVector(Entity entity, double x, double y, double z, float partialTicks) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer wr = tessellator.getWorldRenderer();
        Vec3 vec3 = entity.getLook(partialTicks);

        wr.begin(GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        wr.pos(x, y + (double) entity.getEyeHeight(), z).color(0, 0, 255, 255).endVertex();
        wr.pos(x + vec3.xCoord * 2.0, y + (double) entity.getEyeHeight() + vec3.yCoord * 2.0, z + vec3.zCoord * 2.0).color(0, 0, 255, 255).endVertex();
        tessellator.draw();
    }

    @Unique
    private void hitboxes$drawEyeHeight(Entity entity, double x, double y, double z) {
        float f = entity.width / 2.0f;
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x - (double) f, y + (double) entity.getEyeHeight() - (double) 0.01f, z - (double) f, x + (double) f, y + (double) entity.getEyeHeight() + (double) 0.01f, z + (double) f), 255, 0, 0, 255);
    }

    @Unique
    private int hitboxes$getPlayerColor(EntityPlayer player) {
        int color = Config.playerColor;

        if (Config.playerColorMode == PlayerColorModes.NAMETAG.ordinal()) {
            if (canRenderNametag(player)) color = getNametagHex(player, color);

        } else if (Config.playerColorMode == PlayerColorModes.RED_GREEN.ordinal()) {
            if (canRenderNametag(player)) {
                int ownColor = getNametagHex(mc.thePlayer, color);
                int otherColor = getNametagHex(player, color);
                color = (otherColor == ownColor) ? 0x55FF55 : 0xFF5555;
            }

        } else if (Config.playerColorMode == PlayerColorModes.PING.ordinal()) {
            color = getPingHex(player, color);
        }

        return color;
    }
}
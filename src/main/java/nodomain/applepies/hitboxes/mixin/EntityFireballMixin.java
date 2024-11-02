package nodomain.applepies.hitboxes.mixin;

import net.minecraft.entity.projectile.EntityFireball;
import nodomain.applepies.hitboxes.ProjectileAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.UUID;

@Mixin(EntityFireball.class)
public class EntityFireballMixin implements ProjectileAccessor {
    @Unique boolean hitboxes$hasOwner;
    @Unique boolean hitboxes$hasColor;
    @Unique UUID hitboxes$owner;
    @Unique int hitboxes$color;

    @Override
    public boolean hitboxes$hasOwner() {
        return hitboxes$hasOwner;
    }

    @Override
    public void hitboxes$setOwner(UUID owner) {
        this.hitboxes$owner = owner;
        hitboxes$hasOwner = true;
    }

    @Override
    public UUID hitboxes$getOwner() {
        return hitboxes$owner;
    }

    @Override
    public boolean hitboxes$hasColor() {
        return hitboxes$hasColor;
    }

    @Override
    public void hitboxes$setColor(int color) {
        this.hitboxes$color = color;
        hitboxes$hasColor = true;
    }

    @Override
    public int hitboxes$getColor() {
        return hitboxes$color;
    }
}
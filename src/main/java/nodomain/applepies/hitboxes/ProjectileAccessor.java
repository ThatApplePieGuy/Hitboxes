package nodomain.applepies.hitboxes;

import java.util.UUID;

public interface ProjectileAccessor {
    boolean hitboxes$hasOwner();
    void hitboxes$setOwner(UUID owner);
    UUID hitboxes$getOwner();
    boolean hitboxes$hasColor();
    void hitboxes$setColor(int color);
    int hitboxes$getColor();
}
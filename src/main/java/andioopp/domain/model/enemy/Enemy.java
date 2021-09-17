package andioopp.domain.model.enemy;

import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;
import andioopp.domain.model.Health;
import andioopp.domain.model.Updateable;
import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;

public abstract class Enemy implements Updateable {

    private final Health health;
    private final Transform transform;
    private String sprite;

    protected Enemy(String spritePath, Transform transform, Health health) {
        this.sprite = spritePath;
        this.transform = transform;
        this.health = health;
    }

    public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(sprite);
    }

    public Health getHealth() {
        return health;
    }

    public Vector3f getPosition() {
        return getTransform().getPosition();
    }

    protected Transform getTransform() {
        return transform;
    }

    protected void setSprite(String sprite) {
        this.sprite = sprite;
    }
}

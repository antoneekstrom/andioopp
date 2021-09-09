package andioopp.common;

import andioopp.gfx.Sprite;

public class Entity<S extends Sprite<?>> {

    private Transform transform;
    private S sprite;

    public Entity(Transform transform, S sprite) {
        this.transform = transform;
        this.sprite = sprite;
    }

    public Entity(S sprite) {
        this(new ConcreteTransform(), sprite);
    }

    public void setSprite(S sprite) {
        this.sprite = sprite;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Transform getTransform() {
        return transform;
    }

    public S getSprite() {
        return sprite;
    }
}

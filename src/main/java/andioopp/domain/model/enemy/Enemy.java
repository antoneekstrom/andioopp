package andioopp.domain.model.enemy;

import andioopp.common.time.Time;
import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;
import andioopp.domain.model.Updateable;
import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;

public abstract class Enemy implements Updateable {
    private int hp;
    private int speed;

    private Transform transform;
    private String sprite;

    public Enemy(Transform transform, String spritePath) {
        this.transform = transform;
        this.sprite = spritePath;
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(0.0005f, 0, 0));
    }

    public Transform getTransform() {
        return transform;
    }

    public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(sprite);
    }

    protected void setSprite(String sprite) {
        this.sprite = sprite;
    }

    protected void setTransform(Transform transform) {
        this.transform = transform;
    }
}

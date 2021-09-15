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

    public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(sprite);
    }

    public float getLaneProgress() {
        return getTransform().getPosition().getX();
    }

    private void setLaneProgress(float progress) {
        getTransform().setPosition(getTransform().getPosition().setX(progress));
    }

    private Transform getTransform() {
        return transform;
    }

    private void setSprite(String sprite) {
        this.sprite = sprite;
    }

    private void setTransform(Transform transform) {
        this.transform = transform;
    }
}

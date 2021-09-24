package andioopp.model.enemy;

import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.Health;
import andioopp.model.Updateable;
import andioopp.common.gfx.Sprite;
import andioopp.common.gfx.SpriteFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Enemy implements Updateable {

    private final Health health;
    private final Transform transform;
    private String sprite;

    //Enums
    public ArrayList<FilterRequirement> requirements = new ArrayList<>();
    public ArrayList<FilterImmunity> immunity = new ArrayList<>();

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

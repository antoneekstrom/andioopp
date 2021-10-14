package andioopp.model.tower.attack.projectiles;

import andioopp.common.transform.*;
import andioopp.model.damage.DamageSource;
import andioopp.model.damage.DamageType;
import andioopp.model.interfaces.Updateable;
import andioopp.model.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * A projectile.
 * Usually part of an attack from a tower, an example being a fireball.
 */
public abstract class Projectile implements Updateable, DamageSource {

    public final String spritePath = "fireball.png";
    private final Transform transform;
    private final DamageSource damageSource;

    public final ArrayList<Enemy> alreadyInteractedWith = new ArrayList<>();

    public Projectile(Vector3f position, DamageSource damageSource) {
        this.transform = ConcreteTransform.getFactory().createWithPosition(position);
        this.damageSource = damageSource;
    }

    @Override
    public List<DamageType> getTypes() {
        return damageSource.getTypes();
    }

    public String getSpritePath() {
        return spritePath;
    }

    public Vector3f getPosition() {
        return getTransform().getPosition();
    }

    public Transform getTransform() {
        return transform;
    }
}

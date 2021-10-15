package andioopp.model.domain.tower.attack.projectiles;

import andioopp.common.math.Vector3f;
import andioopp.common.math.transform.ConcreteTransform;
import andioopp.common.math.transform.Transform;
import andioopp.model.domain.damage.DamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.interfaces.Updateable;
import andioopp.model.domain.enemy.Enemy;

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

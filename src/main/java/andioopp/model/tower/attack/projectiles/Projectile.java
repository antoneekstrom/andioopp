package andioopp.model.tower.attack.projectiles;

import andioopp.common.transform.*;
import andioopp.model.damage.DamageSourceType;
import andioopp.model.damage.DamageTargetType;
import andioopp.model.Updateable;
import andioopp.model.enemy.Enemy;

import java.util.ArrayList;

/**
 * A projectile.
 * Usually part of an attack from a tower, i.e. a fireball.
 */
public abstract class Projectile implements Updateable {

    public final String spritePath = "fireball.png";

    private final Transform transform;

    public ArrayList<DamageTargetType> damageTargetTypes;
    public ArrayList<DamageSourceType> immunities;

    public ArrayList<Enemy> alreadyInteractedWith = new ArrayList<>();

    public Projectile(Vector3f position, ArrayList<DamageTargetType> damageTargetTypes, ArrayList<DamageSourceType> immunities) {
        this.transform = ConcreteTransform.getFactory().createWithPosition(position);
        this.damageTargetTypes = damageTargetTypes;
        this.immunities = immunities;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public abstract boolean shouldRemove();

    public Vector3f getPosition() {
        return getTransform().getPosition();
    }

    public Transform getTransform() {
        return transform;
    }
}

package andioopp.model.tower.attack.projectiles;

import andioopp.common.transform.*;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.Updateable;
import andioopp.model.enemy.Enemy;

import java.util.ArrayList;

/**
 * A projectile.
 * Usually part of an attack from a tower, i.e. a fireball.
 */
public abstract class Projectile implements Updateable {

    private final Transform transform;

    public ArrayList<FilterRequirement> requirements;
    public ArrayList<FilterImmunity> immunity;

    public ArrayList<Enemy> alreadyInteractedWith = new ArrayList<>();

    public Projectile(Vector3f position, ArrayList<FilterRequirement> requirements, ArrayList<FilterImmunity> immunity) {
        this.transform = ConcreteTransform.getFactory().createWithPosition(position);
        this.requirements = requirements;
        this.immunity = immunity;
    }

    public abstract boolean shouldRemove();

    public Vector3f getPosition() {
        return getTransform().getPosition();
    }

    public Transform getTransform() {
        return transform;
    }
}

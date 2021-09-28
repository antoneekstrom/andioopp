package andioopp.model.tower.attack.projectiles;

import andioopp.common.transform.*;
import andioopp.model.Updateable;

public abstract class Projectile implements Updateable {

    private final Transform transform;

    public Projectile(Vector3f position) {
        this.transform = ConcreteTransform.getFactory().createWithPosition(position);
    }

    public Vector3f getPosition() {
        return getTransform().getPosition();
    }

    public Transform getTransform() {
        return transform;
    }

}

package andioopp.model.tower.attack.projectiles;

import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;

public class FireballProjectile extends Projectile {
    public FireballProjectile(Vector3f position) {
        super(position);
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(0.02f,0,0));
    }
}

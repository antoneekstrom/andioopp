package andioopp.model.tower.attack.projectiles;

import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;
import andioopp.model.damage.DamageSource;

public class Fireball extends Projectile {

    public Fireball(Vector3f position, DamageSource damageSource) {
        super(position, damageSource);
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(time.getDeltaSeconds()));
    }
}

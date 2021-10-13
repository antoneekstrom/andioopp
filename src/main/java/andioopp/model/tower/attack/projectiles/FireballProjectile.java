package andioopp.model.tower.attack.projectiles;

import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;
import andioopp.model.damage.DamageSource;

public class FireballProjectile extends Projectile {

    private final Vector3f origin;
    private float timeAlive = 0;

    public FireballProjectile(Vector3f position, DamageSource damageSource) {
        super(position, damageSource);
        this.origin = position;
    }

    @Override
    public void update(Time time) {
        getTransform().setPosition(computePosition());
        timeAlive += time.getDeltaSeconds();
    }

    private Vector3f computePosition() {
        Vector3f delta = new Vector3f(getDistanceTraveled(), getHeightOffset());
        return origin.add(delta);
    }

    private float getDistanceTraveled() {
        return timeAlive * 0.5f;
    }

    private float getHeightOffset() {
        float amplitude = 0.45f - Math.min(0.20f, (timeAlive / 15f) * 0.45f);
        float period = 1f;
        return (float) -Math.abs(Math.cos(timeAlive * period)) * amplitude + 0.1f;
    }
}

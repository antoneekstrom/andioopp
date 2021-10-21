package andioopp.model.domain.tower.attack.projectiles;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.time.Time;
import andioopp.model.domain.damage.DamageSource;

/**
 * A projectile in form of a Fireball.
 */
public class FireballProjectile extends Projectile {

    private static final String SPRITE = "fireball.png";
    private final static Dimension SIZE = new Dimension(0.5f, 0.5f);

    private final Vector3f origin;
    private float timeAlive = 0;

    public FireballProjectile(Vector3f position, DamageSource damageSource) {
        super(SPRITE, new ImmutableRectangle(position, SIZE), damageSource);
        this.origin = position;
    }

    @Override
    public void update(Time time) {
        getRectangle().setPosition(computePosition());
        timeAlive += time.getDeltaTime();
    }

    private Vector3f computePosition() {
        Vector3f delta = new Vector3f(getDistanceTraveled(), getHeightOffset());
        return origin.add(delta);
    }

    private float getDistanceTraveled() {
        return timeAlive * 0.5f;
    }

    private float getHeightOffset() {
        float amplitude = 0.3f;
        float period = 1f;
        return (float) -Math.abs(Math.cos(timeAlive * period)) * amplitude + 0.1f;
    }
}

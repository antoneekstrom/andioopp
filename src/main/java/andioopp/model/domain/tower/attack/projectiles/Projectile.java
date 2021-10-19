package andioopp.model.domain.tower.attack.projectiles;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.MutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.time.Time;
import andioopp.model.domain.damage.DamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.stats.Health;
import andioopp.model.util.ModelCoordinate;

import java.util.List;

/**
 * A projectile.
 * Usually part of an attack from a tower, an example being a fireball.
 */
public abstract class Projectile implements DamageSource {

    private final String sprite;
    private final MutableRectangle rectangle;
    private final DamageSource damageSource;
    private final Health health;

    public final List<Enemy> alreadyInteractedWith;

    public Projectile(String sprite, Rectangle rectangle, DamageSource damageSource, Health health) {
        this.sprite = sprite;
        this.rectangle = new MutableRectangle(rectangle);
        this.damageSource = damageSource;
        this.health = health;
        this.alreadyInteractedWith = new ArrayListFactory().create();
    }

    public abstract void update(Time time);

    @Override
    public List<DamageType> getTypes() {
        return damageSource.getTypes();
    }

    public String getSprite() {
        return sprite;
    }

    public ModelCoordinate getPosition() {
        return new ModelCoordinate(rectangle.getPosition());
    }

    public Dimension getSize() {
        return rectangle.getSize();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Health getHealth() {
        return health;
    }
}

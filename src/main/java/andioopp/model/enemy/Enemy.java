package andioopp.model.enemy;

import andioopp.common.graphics.SpriteFactory;
import andioopp.common.time.Time;
import andioopp.common.math.transform.Transform;
import andioopp.common.math.Vector3f;
import andioopp.model.damage.DamageFilter;
import andioopp.model.damage.DamageSource;
import andioopp.model.stats.Health;
import andioopp.model.interfaces.Updateable;
import andioopp.common.graphics.Sprite;

/**
 * An enemy.
 */
public abstract class Enemy implements Updateable, DamageFilter {

    private final Health health;
    private final Transform transform;
    private final String spritePath;
    private final float attackCooldown;
    private final float speed;
    private final int loot;

    private boolean towerAhead = false;
    private float timeOfLastAttack;

    private final DamageFilter damageFilter;

    protected Enemy(String spritePath, Transform transform, Health health, float speed, float attackCooldown, DamageFilter damageFilter, int loot) {
        this.spritePath = spritePath;
        this.transform = transform;
        this.health = health;
        this.speed = speed; // Negative speed since enemies come from the left
        this.attackCooldown = attackCooldown;
        this.damageFilter = damageFilter;
        this.loot = loot;
    }

    @Override
    public void update(Time time) {
        move();
    }

    private void move() {
        // Enemy should stop moving if there is a tower in front of it
        if (!towerAhead) {
            getTransform().translate(new Vector3f(-speed, 0, 0));
        }
    }

    @Override
    public boolean canBeDamagedBy(DamageSource src) {
        return damageFilter.canBeDamagedBy(src);
    }

    /**
     * Sets the time of the enemy's latest attack.
     * It is used to calculate when its next attack can be performed.
     * @param time the current time of the attack
     */
    public void setTimeOfLastAttack(Time time) {
        timeOfLastAttack = time.getElapsedSeconds();
    }

    /**
     * Calculates if the tower is able to attack depending on when it attacked the last time.
     * @param time the current time.
     * @return true if the tower is able to attack.
     */
    public boolean canAttack(Time time) {
        float deltaTime = time.getElapsedSeconds() - timeOfLastAttack;
        return (deltaTime > attackCooldown);
    }

    public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(spritePath);
    }

    public Health getHealth() {
        return health;
    }

    public Vector3f getPosition() {
        return getTransform().getPosition();
    }

    protected Transform getTransform() {
        return transform;
    }

    public boolean isDead() {
        return getHealth().isZero();
    }

    public void setTowerAhead(boolean state) {
        towerAhead = state;
    }

    public int getLoot() {
        return loot;
    }
}

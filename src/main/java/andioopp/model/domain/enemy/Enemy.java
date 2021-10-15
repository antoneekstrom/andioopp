package andioopp.model.domain.enemy;

import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.SpriteFactory;
import andioopp.common.math.Dimension;
import andioopp.common.math.Rectangle;
import andioopp.common.time.Time;
import andioopp.model.domain.money.Money;
import andioopp.model.util.ModelCoordinate;
import andioopp.model.domain.damage.DamageFilter;
import andioopp.model.domain.damage.DamageSource;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.interfaces.Updateable;

/**
 * An enemy.
 */
public abstract class Enemy implements DamageFilter {

    private final Health health;
    private final String spritePath;
    private final float attackCooldown;
    private final float speed;
    private final Money reward;

    private boolean towerAhead = false;
    private float timeOfLastAttack;
    private final Rectangle<ModelCoordinate> rectangle;

    private final DamageFilter damageFilter;

    protected Enemy(String spritePath, Health health, Rectangle<ModelCoordinate> rectangle, float speed, float attackCooldown, DamageFilter damageFilter, Money reward) {
        this.spritePath = spritePath;
        this.health = health;
        this.speed = speed; // Negative speed since enemies come from the left
        this.attackCooldown = attackCooldown;
        this.damageFilter = damageFilter;
        this.reward = reward;
        this.rectangle = rectangle;
    }

   /*@Override
    public void update(Time time) {
        move();
    }

    */

    /*
    private void move() {
        // Enemy should stop moving if there is a tower in front of it
        if (!towerAhead) {
            getTransform().translate(new Vector3f(-speed, 0, 0));
        }
    }

     */

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


    public ModelCoordinate getPosition() {
        return rectangle.getPosition();
    }

    public Dimension<ModelCoordinate> getSize() { return rectangle.getSize(); }

    public void move() {
        rectangle.getPosition().add(new ModelCoordinate(speed));
    }

    public boolean isDead() {
        return getHealth().isZero();
    }

    public void setTowerAhead(boolean state) {
        towerAhead = state;
    }

    public boolean isTowerAhead() {return towerAhead;}

    public Money getReward() {
        return reward;
    }
}

package andioopp.model.enemy;

import andioopp.common.graphics.SpriteFactory;
import andioopp.common.time.Time;
import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.stats.Health;
import andioopp.model.Updateable;
import andioopp.common.graphics.Sprite;

import java.util.ArrayList;

/**
 * An enemy.
 */
public abstract class Enemy implements Updateable {

    private final Health health;
    private final Transform transform;
    private final float speed;
    private final float attackCooldown;
    private String sprite;

    /**
     * List of enums. Each enemy individually adds enums of "requirements" to kill that enemy.
     */
    public ArrayList<FilterRequirement> requirements = new ArrayList<>();
    /**
     * List of enums. Each enemy individually adds enums of "immunities".
     * Towers can´t kill a enemy if its immune to it´s attack
     */
    public ArrayList<FilterImmunity> immunity = new ArrayList<>();

    protected Enemy(String spritePath, Transform transform, Health health, float speed, float attackCooldown) {
        this.sprite = spritePath;
        this.transform = transform;
        this.health = health;
        this.speed = speed; //NEGATIVE SPEED SINCE ENEMIES COME IN FROM THE LEFT
        this.attackCooldown = attackCooldown;

    }

    private boolean towerAhead = false;

    protected void move() {
        if (towerAhead) { //Enemy should stop moving if there is a tower infront of it. Can ofcourse be overriden.
            return;
        } else {
            getTransform().translate(new Vector3f(-speed, 0, 0));
        }
    }

    private float timeOfLastAttack;

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
        return spriteFactory.get(sprite);
    }

    public Health getHealth() {
        return health;
    }

    public Vector3f getPosition() {
        return getTransform().getPosition();
    }

    public void Damage() {
        getHealth().decrease(1);
    }

    protected Transform getTransform() {
        return transform;
    }

    protected void setSprite(String sprite) {
        this.sprite = sprite;
    }

    protected boolean isDead() {
        return getHealth().isZero();
    }

    public boolean isTowerAhead() {
        return towerAhead;
    }

    public void setTowerAhead(boolean state) {
        towerAhead = state;
    }
}

package andioopp.model.domain.tower;

import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.attack.attacks.Attack;
import andioopp.model.util.ModelCoordinate;

import java.util.List;

/**
 * A tower.
 */
public abstract class Tower {

    private final Health health;
    private final String sprite;
    private final List<Attack> attacks;
    private final String name;

    private final ModelCoordinate position;

    public Tower(ModelCoordinate position, String spritePath, String name, Health health) {
        this.sprite = spritePath;
        this.position = position;
        this.name = name;
        this.health = health;
        this.attacks = createAttacks();
    }

    /**
     * Places a list of attacks in the towers list of attacks.
     *
     * @return a list of attacks.
     */
    protected abstract List<Attack> createAttacks();

    /**
     * Returns the towers attacks
     *
     * @return a list of attacks
     */
    public List<Attack> getAttacks() {
        return attacks;
    }

    /**
     * Returns Sprite of Tower.
     */
    public String getSprite() {
        return sprite;
    }

    /**
     * Returns the towers health
     *
     * @return health
     */
    public Health getHealth() {
        return health;
    }

    /**
     * Returns the towers name
     *
     * @return a string of the name of the tower
     */
    public String getName() {
        return name;
    }

    /**
     * Returns position.
     */
    public ModelCoordinate getPosition() {
        return getPosition();
    }
}

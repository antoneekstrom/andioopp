package andioopp.model.domain.tower;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.util.ModelCoordinate;

import java.util.List;

/**
 * A tower.
 */
public abstract class Tower {

    private final Money cost;
    private final Health health;
    private final String sprite;
    private final List<Attack> attacks;
    private final String name;

    private final ModelCoordinate position;

    public Tower(ModelCoordinate position, String spritePath, String name, Money cost, Health health) {
        this.sprite = spritePath;
        this.position = position;
        this.name = name;
        this.cost = cost;
        this.health = health;
        this.attacks = createAttacks();
    }

    protected abstract List<Attack> createAttacks();

    /**
     * Returns List of Attacks of a Tower.
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
     * Returns Health of Tower.
     */
    public Health getHealth() {
        return health;
    }

    /**
     * Returns Cost of Tower.
     */
    public Money getCost() {
        return cost;
    }

    /**
     * Returns name of Tower.
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

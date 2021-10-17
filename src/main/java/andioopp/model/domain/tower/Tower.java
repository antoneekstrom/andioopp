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

    private static final Dimension SIZE = new Dimension(0.7f, 0.7f);

    private final Money cost;
    private final Health health;
    private final String sprite;
    private final List<Attack> attacks;
    private final String name;

    private final Rectangle rectangle;

    public Tower(ModelCoordinate position, String spritePath, String name, Money cost, Health health) {
        this.sprite = spritePath;
        this.rectangle = new ImmutableRectangle(position, SIZE);
        this.name = name;
        this.cost = cost;
        this.health = health;
        this.attacks = createAttacks();
    }

    protected abstract List<Attack> createAttacks();

    public List<Attack> getAttacks() {
        return attacks;
    }

    public String getSprite() {
        return sprite;
    }

    public Health getHealth() {
        return health;
    }

    public Money getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public ModelCoordinate getPosition() {
        return new ModelCoordinate(rectangle.getPosition());
    }

    public Dimension getSize() {
        return rectangle.getSize();
    }
}

package andioopp.model.domain.tower;

import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.attack.Attack;
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

    public String getName() {
        return name;
    }

    public ModelCoordinate getPosition() {
        return getPosition();
    }
}

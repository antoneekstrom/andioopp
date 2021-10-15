package andioopp.model.domain.tower;

import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.SpriteFactory;
import andioopp.common.math.Dimension;
import andioopp.common.math.Rectangle;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.stats.Health;
import andioopp.model.util.ModelCoordinate;

import java.util.List;

/**
 * A tower.
 */
public abstract class Tower {

    private final int cost;
    private final Health health;
    private final String sprite;
    private final List<Attack> attacks;
    private final String name;

    private final Rectangle<ModelCoordinate> rectangle;

    public Tower(String spritePath, Rectangle<ModelCoordinate> rectangle, String name, int cost, int health) {
        this.sprite = spritePath;
        this.rectangle = rectangle;
        this.name = name;
        this.cost = cost;
        this.health = new Health(health);
        this.attacks = createAttacks();
    }

    protected abstract List<Attack> createAttacks();

    public List<Attack> getAttacks() {
        return attacks;
    }

    public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(sprite);
    }
    public String getSprite(){
        return sprite;
    }
    public Health getHealth() {
        return health;
    }

    public int getCost() {
        return cost;
    }
    public String getName(){ return name;}

    public ModelCoordinate getPosition() {
        return rectangle.getPosition();
    }
    public Dimension<ModelCoordinate> getSize() { return rectangle.getSize(); }
}

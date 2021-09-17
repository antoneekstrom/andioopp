package andioopp.domain.model.tower;

import andioopp.domain.model.Health;
import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;

public abstract class Tower {

    private final int range;
    private final int cost;
    private final Health health;
    private String sprite;

    public Tower(String spritePath, int range, int cost, int health) {
        this.sprite = spritePath;
        this.range = range;
        this.cost = cost;
        this.health = new Health(health);
    }

    public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(sprite);
    }

    public Health getHealth() {
        return health;
    }

    public float getCost() {
        return cost;
    }

    protected void setSprite(String sprite) {
        this.sprite = sprite;
    }
}

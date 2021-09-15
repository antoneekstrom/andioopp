package andioopp.domain.model.tower;

import andioopp.domain.model.Health;
import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;

public abstract class Tower {

    private static final float BASE_HEALTH = 7;
    private static final float BASE_COST = 2;

    private final float cost;
    private final Health health;
    private String sprite;

    public Tower(String spritePath) {
        this.sprite = spritePath;
        this.health = new Health(BASE_HEALTH);
        this.cost = BASE_COST;
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

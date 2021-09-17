package andioopp.domain.model.tower;

import andioopp.domain.model.Health;
import andioopp.domain.model.enemy.Enemy;
import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.List;

public abstract class Tower {

    private final int range;
    private final int cost;
    private final Health health;
    private String sprite;
    private ArrayList<Integer> targetedLanes;

    public Tower(String spritePath, int range, int cost, int health, ArrayList<Integer> targetedLanes) {
        this.sprite = spritePath;
        this.range = range;
        this.cost = cost;
        this.health = new Health(health);
        this.targetedLanes = targetedLanes;
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

    public abstract TargetingStrategy getTargetingStrategy();

    public abstract boolean canAttack(Collection<Enemy> enemies);
}

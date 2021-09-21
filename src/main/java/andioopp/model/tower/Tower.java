package andioopp.model.tower;

import andioopp.model.Health;
import andioopp.model.enemy.Enemy;
import andioopp.common.gfx.Sprite;
import andioopp.common.gfx.SpriteFactory;
import andioopp.model.tower.attack.Attack;

import java.util.ArrayList;
import java.util.Collection;

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

    public Attack[] getAttacks() {
        return null;
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

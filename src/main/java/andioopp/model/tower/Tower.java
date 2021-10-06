package andioopp.model.tower;

import andioopp.common.gfx.SpriteFactory;
import andioopp.model.stats.Health;
import andioopp.model.enemy.Enemy;
import andioopp.common.gfx.Sprite;
import andioopp.model.tower.attack.Attack;

import java.util.ArrayList;

/**
 * A tower.
 */
public abstract class Tower {

    private final int cost;
    private final Health health;
    private String sprite;
    private ArrayList<Attack> attacks;
    public String name;

    public Tower(String spritePath, int cost, int health, ArrayList<Attack> attacks) {
        this.sprite = spritePath;
        this.cost = cost;
        this.health = new Health(health);
        this.attacks = attacks;
    }

    public ArrayList<Attack> getAttacks() {
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

    protected void setSprite(String sprite) {
        this.sprite = sprite;
    }
}

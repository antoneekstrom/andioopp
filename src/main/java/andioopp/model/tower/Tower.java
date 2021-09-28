package andioopp.model.tower;

import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.Health;
import andioopp.model.enemy.Enemy;
import andioopp.common.gfx.Sprite;
import andioopp.common.gfx.SpriteFactory;
import andioopp.model.tower.attack.Attack;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Tower {

    private final int cost;
    private final Health health;
    private String sprite;
    private ArrayList<Attack> attacks;


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

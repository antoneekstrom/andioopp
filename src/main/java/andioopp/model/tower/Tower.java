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

    //Enums
    public ArrayList<FilterRequirement> requirements = new ArrayList<>();
    public ArrayList<FilterImmunity> immunty = new ArrayList<>();

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

    private boolean hasMatchingRequirements(Enemy enemy) {
        for(int i = 0; i < requirements.size(); i++) {
            FilterRequirement r = requirements.get(i);
            for(int j = 0; j < enemy.requirements.size(); j++){
                FilterRequirement e = enemy.requirements.get(j);
                if (r.equals(e)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isImmune(Enemy enemy) {
        if(enemy.immunity.isEmpty()) { //if enemy immunity list is empty => Its not immune.
            return false;
        } else {
            for (int i = 0; i < immunty.size(); i++) {
                FilterImmunity imm = immunty.get(i);
                for (int j = 0; j < enemy.immunity.size(); j++) {
                    FilterImmunity EnemyImm = enemy.immunity.get(j);
                    if (imm.equals(EnemyImm)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkFilters(Enemy enemy) {
        boolean b1 = !isImmune(enemy);
        boolean b2 = hasMatchingRequirements(enemy);
        return b1 && b2;
        //return ((!isImmune(enemy)) && hasMatchingRequirements(enemy));
    }
}

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

    //Enums
    public ArrayList<Enum> requirements = new ArrayList<>();
    public ArrayList<Enum> immunty = new ArrayList<>();
    public enum REQUIREMENT {FLYING, GROUND, GHOST, WATER, DIGGING};
    public enum IMMUNITY {FIREBALL, JUMP, EAT, DIG, PIPE, BOMB, STARBITS, LIGHT, PICKAXE, BOOMERANG, ICEBALL}
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

    public boolean hasMatchingRequirements(Enemy enemy) {
        for(int i = 0; i < requirements.size(); i++) {
            Enum r = requirements.get(i);
            for(int j = 0; i < enemy.requirements.size(); i++){
                Enum e = enemy.requirements.get(j);
                if (r.equals(e)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isImmune(Enemy enemy) {
        if(enemy.immunity.isEmpty()) { //if enemy immunity list is empty => Its not immune.
            return false;
        } else {
            for (Enum imm : immunty) {
                for (int j = 0; j < enemy.immunity.size(); j++) {
                    Enum EnemyImm = enemy.immunity.get(j);
                    if (imm.equals(EnemyImm)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkFilters(Enemy enemy) {
        if (!(isImmune(enemy)) && hasMatchingRequirements(enemy)) {
            return true;
        }
        return false;
    }
}

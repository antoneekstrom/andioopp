package andioopp.model.tower.attack;

import andioopp.common.time.Time;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.World;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Attack {
    private final float coolDown;
    private float timeSinceLastAttack;
    private final AttackTargetArea targetArea;

    //Enums
    public ArrayList<FilterRequirement> requirements = new ArrayList<>();
    public ArrayList<FilterImmunity> immunty = new ArrayList<>();

    public Attack(float coolDown, AttackTargetArea targetArea) {
        this.coolDown = coolDown;
        this.targetArea = targetArea;
    }

    public abstract void performAttack(World world, Vector3f position);

    public boolean isAvailableForAttack(Time time){
        float deltaSeconds = time.getElapsedSeconds() - timeSinceLastAttack;
        return(deltaSeconds > this.coolDown);
    }

    public void updateTimeSinceLastAttack(Time time) {
        this.timeSinceLastAttack = time.getElapsedSeconds();
    }

    public Collection<Enemy> getEnemiesInRange(World world, Vector3f position) {
        Collection<Enemy> enemiesInRange = new ArrayListFactory().create();
        for ( Enemy enemy : world.getEnemies() ) {

            if (targetArea.enemyIsInRange(position, enemy.getPosition())) {
                enemiesInRange.add(enemy);
            }
        }
        return enemiesInRange;
    }

    public boolean hasMatchingRequirements(Enemy enemy) {
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

    public boolean isImmune(Enemy enemy) {
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
    }
}

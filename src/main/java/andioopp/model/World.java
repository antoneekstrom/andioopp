package andioopp.model;

import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.projectiles.Projectile;

import java.util.Collection;
import java.util.List;

public class World implements Updateable {

    private final List<Lane> lanes;
    private final Collection<Enemy> enemies;
    private final Collection<Projectile> projectiles;

    World(List<Lane> lanes, Collection<Enemy> enemies, Collection<Projectile> projectiles) {
        this.lanes = lanes;
        this.enemies = enemies;
        this.projectiles = projectiles;
    }

    @Override
    public void update(Time time) {
        getEnemies().forEach((enemy) -> enemy.update(time));
        getProjectiles().forEach((projectile) -> projectile.update(time));



        for (int row = 0; row < getLanes().size(); row++) {
            for (int col = 0; col < getNumberOfCellsInLanes(); col++) {
                Tower tower = getCell(row, col).getTower();
                if(tower != null){
                    for (Attack attack : tower.getAttacks()) {

                        //If the current attack is still on cooldown, move on to the next attack
                        if(!attack.isAvailableForAttack(time)) continue;

                        //Finds all enemies in range of the current attack
                        Collection<Enemy> enemiesInRangeOfCurrentAttack = attack.getEnemiesInRange(this, new Vector3f(col, row, 0));

                        //Checks against all enemies in range if they are immune
                        //If they are immune or if it has the incorrect requirements, move on to the next enemy.
                        //When a targetable enemy is found, the attack can be performed as soon as possible
                        //No need to check the remaing enemies.
                        boolean targetableEnemyExists = false;
                        for (Enemy enemy : enemiesInRangeOfCurrentAttack){
                            if ( tower.isImmune(enemy) ) {
                                continue;
                            }
                            if ( tower.hasMatchingRequirements(enemy) ) {
                                continue;
                            }
                            targetableEnemyExists = true;
                            break;
                        }

                        //Performs the attack and updates its last time of use.
                        if (targetableEnemyExists){
                            attack.performAttack(this, new Vector3f(row, col));
                            attack.updateTimeSinceLastAttack(time);
                        }
                    }
                }
            }
        }

        updateProjectiles(time);
    }

    private void updateProjectiles(Time time) {
        for (Projectile projectile : projectiles) {
            projectile.update(time);
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public Collection<Enemy> getEnemies() {
        return enemies;
    }

    public Lane getLane(int row) {
        return getLanes().get(row);
    }

    public Cell getCell(int row, int col) {
        return getLane(row).getCell(col);
    }

    public int getNumberOfLanes() {
        return lanes.size();
    }

    public int getNumberOfCellsInLanes() {
        return lanes.get(0).getNumberOfCells();
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public Collection<Projectile> getProjectiles(){
        return projectiles;
    }
}

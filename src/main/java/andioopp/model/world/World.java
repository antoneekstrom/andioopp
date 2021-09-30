package andioopp.model.world;

import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.Updateable;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.projectiles.Projectile;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

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
                if (tower != null) {
                    for (Attack attack : tower.getAttacks()) {

                        //If the current attack is still on cooldown, move on to the next attack
                        if (!attack.isAvailableForAttack(time)) continue;

                        //Finds all enemies in range of the current attack
                        Collection<Enemy> enemiesInRangeOfCurrentAttack = attack.getEnemiesInRange(this, new Vector3f(col, row, 0));

                        //Checks against all enemies in range if they are immune
                        //If they are immune or if it has the incorrect requirements, move on to the next enemy.
                        //When a targetable enemy is found, the attack can be performed as soon as possible
                        //No need to check the remaing enemies.
                        boolean targetableEnemyExists = false;
                        for (Enemy enemy : enemiesInRangeOfCurrentAttack){
                            if ( attack.isImmune(enemy) ) {
                                continue;
                            }
                            if ( attack.hasMatchingRequirements(enemy) ) {
                                continue;
                            }
                            targetableEnemyExists = true;
                            break;
                        }

                        //Performs the attack and updates its last time of use.
                        if (targetableEnemyExists){
                            attack.performAttack(this, new Vector3f(col, row));
                            attack.updateTimeSinceLastAttack(time);
                        }
                    }
                }
            }
        }

        checkProjectileHitboxes();

        updateProjectiles(time);


    }

    private void DespawnOutOfBoundProjectiles() {
        //Checks if a projectile is out of bounds and removes it if true.

        //projectiles.removeIf(projectile -> projectile.getPosition().getX() > this.length);


    }

    private void checkProjectileHitboxes(){
        for (Projectile projectile : projectiles) {
            for (Enemy enemy : enemies) {
                Vector3f pp = projectile.getPosition();
                Vector3f ep = enemy.getPosition();
                float dm = 0.2f; //dm stands for delta max

                if ( Math.abs(pp.getX() - ep.getX()) < dm && Math.abs(pp.getY() - ep.getY()) < dm) {
                    evaluateProjectileHit(projectile, enemy);
                }
            }
        }
    }

    private void evaluateProjectileHit(Projectile projectile, Enemy enemy) {
        //if the enemy is in contact with the projectile and isn´t
        // immune to it, damage the enemy and remove the projectile.
        if (!isImmune(projectile, enemy) && isContact(projectile, enemy) && !projectile.alreadyInteractedWith.contains(enemy)) {
            projectiles.remove(projectile);
            enemy.getHealth().decrease(1);
            projectile.alreadyInteractedWith.add(enemy);
        //if the enemy is immune to the projectile the enemy wont get damaged and
        //the projectile will get destroyed.
        } else if(isImmune(projectile, enemy) && isContact(projectile, enemy) && !projectile.alreadyInteractedWith.contains(enemy)) {
            projectiles.remove(projectile);
            System.out.println(" 2 ");
            projectile.alreadyInteractedWith.add(enemy);

        }
        if (isEnemyDead(enemy)) {
            enemies.remove(enemy);
        }
    }

    //TODO destroy when outOfBounds
    //TODO check AlreadyInteractedWith so that a enemy isn´t hit multiple times.
    private boolean isContact(Projectile projectile, Enemy enemy) {
        //Checks if the projectile can damage the enemy by comparing their requirement lists.
        for (int i = 0; i < projectile.requirements.size(); i++) {
            FilterRequirement proReq = projectile.requirements.get(i);
            for (int j = 0; j < enemy.requirements.size(); j++) {
                FilterRequirement enemyReq = enemy.requirements.get(j);
                if (proReq.equals(enemyReq)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isImmune(Projectile projectile, Enemy enemy) {
        for (int i = 0; i < projectile.immunity.size(); i++) {
            FilterImmunity proReq = projectile.immunity.get(i);
            for (int j = 0; j < enemy.immunity.size(); j++) {
                FilterImmunity enemyReq = enemy.immunity.get(j);
                if (proReq.equals(enemyReq)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isEnemyDead(Enemy enemy) {
        return enemy.getHealth().isZero();
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

    public Collection<Projectile> getProjectiles() {
        return projectiles;
    }
}

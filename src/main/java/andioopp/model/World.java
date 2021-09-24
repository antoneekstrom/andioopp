package andioopp.model;

import andioopp.common.time.Time;
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
        for (int row = 0; row < getLanes().size(); row++) {
            for (int col = 0; col < getNumberOfCellsInLanes(); col++) {
                /*if(getCell(row, col).getTower() != null){
                    for (Attack attack : getCell(row, col).getTower().getAttacks()) {

                    }
                }*/
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

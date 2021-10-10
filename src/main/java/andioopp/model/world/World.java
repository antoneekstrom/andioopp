package andioopp.model.world;

import andioopp.common.time.Time;
import andioopp.common.transform.Dimension;
import andioopp.common.transform.Vector3f;
import andioopp.model.interfaces.Updateable;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.projectiles.Projectile;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

        checkProjectileHitboxes();
        updateProjectiles(time);
        handleEnemyAttacks(time);
        despawnOutOfBoundsProjectiles();
        despawnOutOfBoundsEnemies();
    }

    private void despawnOutOfBoundsProjectiles() {
        //Checks if a projectile is out of bounds and removes it if true.
        projectiles.removeIf(projectile -> projectile.getPosition().getX() > getNumberOfCellsInLanes());
    }

    private void despawnOutOfBoundsEnemies() {
        //Checks if a projectile is out of bounds and removes it if true.
        enemies.removeIf(enemy -> enemy.getPosition().getX() < 0);
    }

    private void checkProjectileHitboxes() {
        for (Iterator<Projectile> projectileIterator = projectiles.iterator(); projectileIterator.hasNext(); ) {
            Projectile projectile = projectileIterator.next();

            for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
                Enemy enemy = enemyIterator.next();
                Vector3f pp = projectile.getPosition();
                Vector3f ep = enemy.getPosition();
                float dm = 0.2f; //dm stands for delta max

                if (Math.abs(pp.getX() - ep.getX()) < dm && Math.abs(pp.getY() - ep.getY()) < dm) {
                    evaluateProjectileHit(projectile, enemy, projectileIterator, enemyIterator);
                }
            }
        }
    }

    /**
     * Called when a collision is detected. Checks what to do with the collision.
     *
     * @param projectile         to compare with enemy
     * @param enemy              to compare with projectile
     * @param projectileIterator to edit list of projectiles
     * @param enemyIterator      to edit list of enemies
     */
    private void evaluateProjectileHit(Projectile projectile, Enemy enemy, Iterator<Projectile> projectileIterator, Iterator<Enemy> enemyIterator) {
        if (projectile.alreadyInteractedWith.contains(enemy)) {
            return;
        }

        projectile.alreadyInteractedWith.add(enemy);
        projectileIterator.remove();

        if (enemy.canBeDamagedBy(projectile)) {
            enemy.getHealth().decrease(1);
            if (enemy.isDead()) {
                enemyIterator.remove();
            }
        }
    }

    private void updateProjectiles(Time time) {
        for (Projectile projectile : projectiles) {
            projectile.update(time);
        }
    }

    private void handleEnemyAttacks(Time time) {
        for (Enemy enemy : enemies) {
            int row = (int) enemy.getPosition().getY();

            enemy.setTowerAhead(false);
            for (int col = 0; col < getNumberOfCellsInLanes(); col++) {
                Tower tower = getCell(row, col).getTower();

                if (tower != null) {
                    float deltaX = enemy.getPosition().getX() - col;
                    if (deltaX < 0.5f && deltaX > 0) {
                        enemy.setTowerAhead(true);
                        if (enemy.canAttack(time)) {
                            enemy.setTimeOfLastAttack(time);
                            tower.getHealth().decrease(1);
                        }
                    }
                    if (tower.getHealth().isZero()) {
                        getCell(row, col).setTower(null);
                    }
                }
            }
        }
    }

    public Dimension getGridSize() {
        return new Dimension(getNumberOfCellsInLanes(), getNumberOfLanes());
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

    public Collection<Cell> getCells() {
        return getLanes().stream().map(Lane::getCells).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public Collection<Projectile> getProjectiles() {
        return projectiles;
    }
}

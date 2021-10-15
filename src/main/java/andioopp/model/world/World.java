package andioopp.model.world;

import andioopp.common.transform.Dimension;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.attack.projectiles.Projectile;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class World {

    private final List<Lane> lanes;
    private final Collection<Enemy> enemies;
    private final Collection<Projectile> projectiles;

    World(List<Lane> lanes, Collection<Enemy> enemies, Collection<Projectile> projectiles) {
        this.lanes = lanes;
        this.enemies = enemies;
        this.projectiles = projectiles;
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

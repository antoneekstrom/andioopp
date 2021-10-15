package andioopp.model.domain.world;

import andioopp.common.math.Dimension;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.util.ModelCoordinate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class World {

    private final List<Lane> lanes;
    private final Collection<Enemy> enemies;
    private final Collection<Projectile> projectiles;
    private final Collection<DroppedCoinEntity> droppedCoins;

    World(List<Lane> lanes, Collection<Enemy> enemies, Collection<Projectile> projectiles, Collection<DroppedCoinEntity> droppedCoins) {
        this.lanes = lanes;
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.droppedCoins = droppedCoins;
    }

    public Dimension<ModelCoordinate> getGridSize() {
        return new Dimension<>(new ModelCoordinate(getNumberOfCellsInLanes(), getNumberOfLanes()));
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

    public Collection<DroppedCoinEntity> getDroppedCoins() {
        return droppedCoins;
    }
}

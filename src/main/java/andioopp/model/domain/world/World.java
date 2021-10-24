package andioopp.model.domain.world;

import andioopp.common.math.dimension.Dimension;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.domain.waves.Wave;
import andioopp.model.util.ModelCoordinate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The game world which consists of lanes.
 * The world is inhabited by enemies, projectiles and dropped coins.
 *
 * @author Anton Ekström, Jacob Bengtsson, Amanda Papacosta, Arvid Svedberg, Elin Nilsson
 */
public class World {

    private final List<Lane> lanes;
    private final Collection<Enemy> enemies;
    private final Collection<Projectile> projectiles;
    private final Collection<DroppedCoinEntity> droppedCoins;

    /**
     * Creates a world.
     *
     * @param lanes the lanes
     * @param enemies the enemies
     * @param projectiles the projectiles
     * @param droppedCoins the dropped coins
     */
    World(List<Lane> lanes, Collection<Enemy> enemies, Collection<Projectile> projectiles, Collection<DroppedCoinEntity> droppedCoins) {
        this.lanes = lanes;
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.droppedCoins = droppedCoins;
    }

    /**
     * Returns a Dimension of the grid as a ModelCoordinate.
     */
    public Dimension getGridSize() {
        return new Dimension(new ModelCoordinate(getNumberOfCellsInLanes(), getNumberOfLanes()));
    }

    /**
     * Adds a enemy to the Collection enemies.
     * @param enemy the enemy to add.
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Returns the Collection enemies consisting of enemies.
     * @return the enemies
     */
    public Collection<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Returns a Lane from a specific row.
     * @param row of the Lane.
     * @return a Lane.
     */
    public Lane getLane(int row) {
        return getLanes().get(row);
    }

    /**
     * Returns a Cell from a specific position.
     * @param col of the Cell.
     * @param row of the Cell.
     * @return a Cell.
     */
    public Cell getCell(int col, int row) {
        return getLane(row).getCell(col);
    }

    /**
     * Returns number of Lanes in the List lanes.
     */
    public int getNumberOfLanes() {
        return lanes.size();
    }

    /**
     * Returns the number of Cells in a lane.
     */
    public int getNumberOfCellsInLanes() {
        return lanes.get(0).getNumberOfCells();
    }

    /**
     * Returns the List lanes.
     */
    public List<Lane> getLanes() {
        return lanes;
    }

    /**
     * Returns a Collection of Cells, by getting the List of lanes and collecting all cells.
     */
    public Collection<Cell> getCells() {
        return getLanes().stream().map(Lane::getCells).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Adds a projectile to the Collection of Projectiles.
     */
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    /**
     * Returns the Collection projectiles consisting of Projectiles.
     */
    public Collection<Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * Returns the Collection droppedCoins consisting of DroppedCoinEntities.
     */
    public Collection<DroppedCoinEntity> getDroppedCoins() {
        return droppedCoins;
    }
}

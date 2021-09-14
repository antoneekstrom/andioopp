package andioopp.domain.model;

import andioopp.common.time.Time;
import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.TransformFactory;
import andioopp.common.transform.Vector3f;
import andioopp.domain.model.enemy.Enemy;
import andioopp.domain.model.enemy.EnemyFactory;
import andioopp.domain.model.towers.Tower;

import java.util.ArrayList;

public class Model implements Updateable {

    private final World world;
    private final WaveQueue waves;
    private final Player player;
    private int money = 0;

    public Model(WaveQueue waves, Player player) {
        this.world = new World(new ArrayList<>());
        this.waves = waves;
        this.player = player;

        initWorld(world, ConcreteTransform.getFactory(), new EnemyFactory());
    }

    private void initWorld(World world, TransformFactory transformFactory, EnemyFactory enemyFactory) {
        addLanes(5, transformFactory,enemyFactory);
    }

    private void addLanes(int numlanes, TransformFactory transformFactory, EnemyFactory enemyFactory){
        for (int i = 0; i < numlanes; i++){
            Lane lane = new Lane(transformFactory.create());

            lane.getEnemies().add(enemyFactory.goomba());
            addCellsToLane(lane, 9);
            world.getLanes().add(lane);
        }
    }

    private void addCellsToLane(Lane lane, int numCells) {
        for (int i = 0; i < numCells; i++) {
            lane.getCells().add(new Cell());
        }
    }

    @Override
    public void update(Time time) {
        for (Lane lane : world.getLanes()) {
            for (Enemy enemy : lane.getEnemies()) {
                enemy.update(time);
            }
        }

    }
    public World getWorld() {
        return world;
    }

    public Cell getCell(int row, int col) {
        return getWorld().getLanes().get(row).getCells().get(col);
    }

    public void placeTower(Tower tower, Cell cell) {
        cell.setTower(tower);
    }
}

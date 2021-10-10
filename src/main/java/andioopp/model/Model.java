package andioopp.model;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;
import andioopp.model.enemy.Enemy;
import andioopp.model.interfaces.Updateable;
import andioopp.model.player.Player;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.waves.WaveQueue;
import andioopp.model.world.LaneBuilder;
import andioopp.model.world.World;
import andioopp.model.world.WorldBuilder;

/**
 * Facade for the entire model.
 */
public class Model implements Updateable {

    private final World world;
    private final WaveQueue waves;
    private final Player player;

    private final ListFactory listFactory = new ArrayListFactory();
    private double delay = 1;

    public Model(WaveQueue waves, Player player) {
        this.waves = waves;
        this.player = player;
        this.world = createWorld();
    }

    @Override
    public void update(Time time) {
        world.update(time);
        performAllTowerAttacks(time);

        if (waves.delayEnemies(time, delay)) {
            if (waves.getWave().enemyWave.size() != 0) {
                waves.addWaveToWorld(world);
                waves.setDeltaSeconds(0);
                this.delay = waves.getRandomDelay();
                waves.updateTimeSinceLastEnemy(time);
            }
        }
    }

    private void performAllTowerAttacks(Time time) {
        World world = getWorld();
        for (int row = 0; row < world.getLanes().size(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                Tower tower = world.getCell(row, col).getTower();
                if (tower != null) {
                    for (Attack attack : tower.getAttacks()) {
                        if (!attack.isAvailableForAttack(time)) {
                            continue;
                        }

                        Vector3f position = new Vector3f(col, row);
                        for (Enemy enemy : attack.getEnemiesInRange(world, position)) {
                            if (enemy.canBeDamagedBy(attack)) {
                                attack.performAttack(this, position);
                                attack.updateTimeSinceLastAttack(time);
                            }
                        }
                    }
                }
            }
        }
    }

    private World createWorld() {
        LaneBuilder laneBuilder = new LaneBuilder(listFactory).setCells(9);
        WorldBuilder builder = new WorldBuilder(laneBuilder, listFactory).setLanes(5);
        World world = builder.build();
        waves.addWavesToWaveQueue(world, 1);
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }
}




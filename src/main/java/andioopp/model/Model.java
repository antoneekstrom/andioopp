package andioopp.model;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.tower.Towers;
import andioopp.model.waves.WaveQueue;
import andioopp.model.world.LaneBuilder;
import andioopp.model.world.World;
import andioopp.model.world.WorldBuilder;

public class Model implements Updateable {

    private final World world;
    private final WaveQueue waves;
    private final ListFactory listFactory = new ArrayListFactory();
    float timeSinceLastEnemy;
    float deltaSeconds;

    public Model(WaveQueue waves) {
        this.waves = waves;
        this.world = createWorld();
    }

    @Override
    public void update(Time time) {
        world.update(time);

        if (delayEnemies(time)) {
            if (waves.getWave(world).enemyWave.size() != 0) {

                waves.addWaveToWorld(world, waves.getWave(world));
                this.deltaSeconds = 0;
                updateTimeSinceLastEnemy(time);
            }
        }
    }

    private World createWorld() {
        LaneBuilder laneBuilder = new LaneBuilder(listFactory).setCells(9);
        WorldBuilder builder = new WorldBuilder(laneBuilder, listFactory).setLanes(5);

        World world = builder.build();
        world.getCell(1, 3).setTower(Towers.mario());
        waves.addWavesToWaveQueue(world, 1);

        return world;
    }

    public boolean delayEnemies(Time time) {
        this.deltaSeconds = time.getElapsedSeconds() - timeSinceLastEnemy;
        return (this.deltaSeconds > 2);
    }

    public void updateTimeSinceLastEnemy(Time time) {
        this.timeSinceLastEnemy = time.getElapsedSeconds();
    }

    public World getWorld() {
        return world;
    }
}




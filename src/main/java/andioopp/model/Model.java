package andioopp.model;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.stats.Money;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import andioopp.model.waves.WaveQueue;
import andioopp.model.world.LaneBuilder;
import andioopp.model.world.World;
import andioopp.model.world.WorldBuilder;

import java.util.List;
import java.util.function.Supplier;

/**
 * Facade for the entire model.
 */
public class Model implements Updateable {

    private final World world;
    private final WaveQueue waves;
    private final ListFactory listFactory = new ArrayListFactory();
    private final Money money = new Money(100);
    private final List<Supplier<Tower>> cards;

    private double delay = 1;

    public Model(WaveQueue waves, List<Supplier<Tower>> cards) {
        this.waves = waves;
        this.cards = cards;
        this.world = createWorld();
    }

    public List<Supplier<Tower>> getCards() {
        return cards;
    }

    @Override
    public void update(Time time) {
        world.update(time);

        if (waves.delayEnemies(time, delay)) {
            if (waves.getWave().enemyWave.size() != 0) {

                waves.addWaveToWorld(world);
                waves.setDeltaSeconds(0);
                this.delay = waves.getRandomDelay();
                waves.updateTimeSinceLastEnemy(time);
            }
        }
    }

    private World createWorld() {
        LaneBuilder laneBuilder = new LaneBuilder(listFactory).setCells(9);
        WorldBuilder builder = new WorldBuilder(laneBuilder, listFactory, money).setLanes(5);

        World world = builder.build();

        waves.addWavesToWaveQueue(world, 5);

        return world;
    }

    public World getWorld() {
        return world;
    }
}




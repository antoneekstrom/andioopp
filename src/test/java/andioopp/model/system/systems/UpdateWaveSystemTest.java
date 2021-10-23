package andioopp.model.system.systems;

import andioopp.common.math.range.IntRange;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.waves.WaveQueue;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.system.System;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UpdateWaveSystemTest {

    public static final float TIME_STEP = 0.01f;
    private Model model;

    @Before
    public void before() {
        model = getModel();
    }

    @Test
    public void addsEnemyAfterMaxTime() {
        IntRange spawnTimeRange = new IntRange(5, 10);
        UpdateWaveSystem system = new UpdateWaveSystem(new WaveQueue().addWaves(3, new IntRange(3, 5)), spawnTimeRange);
        int timePassed = spawnTimeRange.getMax();
        integrate(system, TIME_STEP, timePassed / TIME_STEP);
        assertEquals(1, model.getWorld().getEnemies().size());
    }

    private void integrate(System<Model> system, float timeStep, float times) {
        float elapsed = 0;
        for (int i = 0; i < times; i++) {
            system.update(model, new Time(elapsed, timeStep));
            elapsed += timeStep;
        }
    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}

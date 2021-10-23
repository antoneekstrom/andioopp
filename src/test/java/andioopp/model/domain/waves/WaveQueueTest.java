package andioopp.model.domain.waves;

import andioopp.common.storage.ListFactory;
import andioopp.model.Model;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WaveQueueTest {

    private Model model;

    @Before
    public void before() {
        model = getModel();
    }

    @Test
    public void doesQueueHaveCorrectNumberOfWaves() {
        assertEquals(new WaveQueue().addWaves(7, 0).getWavesRemaining(), 7);
    }

    @Test
    public void doesEmptyWaveGetRemoved() {
        WaveQueue waves = new WaveQueue().addWaves(1, 0).addWaves(1, 1);
        waves.spawnNextEnemy(model.getWorld());
        assertEquals(1, waves.getWavesRemaining());
    }

    @Test
    public void isEnemyAddedToWorld() {
        new WaveQueue().addWaves(1, 1).spawnNextEnemy(model.getWorld());
        assertEquals(1, model.getWorld().getEnemies().size());
    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}

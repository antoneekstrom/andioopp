package model.world;

import andioopp.common.storage.ArrayListFactory;
import andioopp.model.Model;
import andioopp.model.player.Money;
import andioopp.model.player.Player;
import andioopp.model.player.TowerCard;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import andioopp.model.waves.WaveQueue;
import andioopp.model.world.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WorldTest {

    private final World world = new WorldBuilder(new LaneBuilder(new ArrayListFactory()), new ArrayListFactory()).setLanes(5).build();

    @Test
    public void testGetNumberOfLanes() {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell());
        Lane lane = new Lane(cells);
        world.getLanes().clear();
        world.getLanes().add(lane);
        assertEquals(1, world.getNumberOfLanes());
    }
}

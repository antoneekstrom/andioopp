package andioopp.model.domain.world;

import andioopp.common.storage.ArrayListFactory;
import org.junit.Test;

import java.util.ArrayList;
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

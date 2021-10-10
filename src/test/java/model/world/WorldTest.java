package model.world;

import andioopp.model.Model;
import andioopp.model.player.Money;
import andioopp.model.player.Player;
import andioopp.model.player.TowerCard;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import andioopp.model.waves.WaveQueue;
import andioopp.model.world.Cell;
import andioopp.model.world.Lane;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WorldTest {

    private final WaveQueue waveQueue = new WaveQueue();
    private final Model model = new Model(waveQueue, new Player(getCards()));

    @Test
    public void testGetNumberOfLanes() {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell());
        Lane lane = new Lane(cells);
        model.getWorld().getLanes().clear();
        model.getWorld().getLanes().add(lane);
        assertEquals(1, model.getWorld().getNumberOfLanes());
    }

    private List<TowerCard<?>> getCards() {
        TowerCard<Tower> mario = new TowerCard<>(new Money(60), Towers::mario);
        TowerCard<Tower> toad = new TowerCard<>(new Money(40), Towers::toad);
        return Arrays.asList(mario, toad);
    }
}

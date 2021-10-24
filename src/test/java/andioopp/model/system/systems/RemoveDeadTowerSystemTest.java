package andioopp.model.system.systems;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.towers.Mario;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.system.systems.RemoveDeadTowersSystem;
import andioopp.model.util.ModelCoordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RemoveDeadTowerSystemTest {


    private final Tower marioShouldDie = new Mario(new ModelCoordinate(new Vector3f(1,1)));
    private final Tower marioShouldDieMedRåge = new Mario(new ModelCoordinate(new Vector3f(1,2)));
    private final Tower marioShouldSurvive = new Mario(new ModelCoordinate(new Vector3f(1,3)));

    RemoveDeadTowersSystem removeDeadTowersSystem = new RemoveDeadTowersSystem();

    private final Model model = getModel();

    @Before
    public void init() {
        int defaultHP = marioShouldDie.getHealth().get();
        int moreThanDefault = marioShouldDie.getHealth().get() + 10;
        int lessThanDefault = marioShouldDie.getHealth().get() - 2;

        marioShouldDie.getHealth().decrease(defaultHP); //sets mario health to 0.
        marioShouldDieMedRåge.getHealth().decrease(moreThanDefault); //sets mario health to minus value.
        marioShouldSurvive.getHealth().decrease(lessThanDefault);

        model.getWorld().getCell(1,1).setTower(marioShouldDie);
        model.getWorld().getCell(1,2).setTower(marioShouldDieMedRåge);
        model.getWorld().getCell(1,3).setTower(marioShouldSurvive);
    }

    @Test
    public void MarioShouldDie() {
        boolean hasTowerBefore = model.getWorld().getCell(1,1).hasTower();

        removeDeadTowersSystem.update(model, new Time(10, 0.1f));

        boolean hasTowerAfter = model.getWorld().getCell(1,1).hasTower();

        assertNotEquals(hasTowerBefore, hasTowerAfter);
    }

    @Test
    public void MarioShouldDieMedRåge() {
        boolean hasTowerBefore = model.getWorld().getCell(1,2).hasTower();

        removeDeadTowersSystem.update(model, new Time(10, 0.1f));

        boolean hasTowerAfter = model.getWorld().getCell(1,2).hasTower();

        assertNotEquals(hasTowerBefore, hasTowerAfter);
    }

    @Test
    public void MarioShouldSurvive() {
        boolean hasTowerBefore = model.getWorld().getCell(1,3).hasTower();

        removeDeadTowersSystem.update(model, new Time(10, 0.1f));

        boolean hasTowerAfter = model.getWorld().getCell(1,3).hasTower();

        assertEquals(hasTowerBefore, hasTowerAfter);
    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}

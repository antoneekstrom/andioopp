package andioopp.model.system.systems;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.enemy.enemies.Goomba;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.system.systems.MoveEnemySystem;
import andioopp.model.util.ModelCoordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;



public class MoveEnemySystemTest {

    private final Enemy e = new Goomba(new ModelCoordinate(new Vector3f(1,1)));

    MoveEnemySystem moveEnemySystem = new MoveEnemySystem();

    Model model = getModel();

    @Before
    public void init() {
        model.getWorld().getEnemies().add(e);
    }

    @Test
    public void EnemyShouldMove() {
        float xBefore = e.getPosition().getX();

        moveEnemySystem.update(model, new Time(10, 0.2f));

        float xAfter = e.getPosition().getX();

        assertTrue(xBefore > xAfter);
    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}

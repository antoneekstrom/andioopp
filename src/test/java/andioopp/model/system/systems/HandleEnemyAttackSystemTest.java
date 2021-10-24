package andioopp.model.system.systems;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.enemy.enemies.Goomba;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.towers.Mario;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.system.systems.HandleEnemyAttackSystem;
import andioopp.model.system.systems.MoveEnemySystem;
import andioopp.model.util.ModelCoordinate;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class HandleEnemyAttackSystemTest {

    private final Enemy goomba = new Goomba(new ModelCoordinate(new Vector3f(1.2f,1)));
    private final Tower mario = new Mario(new ModelCoordinate(new Vector3f(1,1)));
    private final Tower marioWrongLane = new Mario(new ModelCoordinate(new Vector3f(1,2)));
    private final Tower marioBehindEnemy = new Mario(new ModelCoordinate(new Vector3f(7,1)));

    private final HandleEnemyAttackSystem handleEnemyAttackSystem = new HandleEnemyAttackSystem();
    private final MoveEnemySystem moveEnemySystem = new MoveEnemySystem();

    private final Model model = getModel();

    @Before
    public void init() {
        model.getWorld().getCell(1,1).setTower(mario);
        model.getWorld().getCell(1,2).setTower(marioWrongLane);
        model.getWorld().getCell(6,1).setTower(marioBehindEnemy);
        model.getWorld().addEnemy(goomba);

    }

    @Test
    public void EnemyShouldNotHitTowerBehindEnemy() {
        Health marioHealthBefore = new Health(marioBehindEnemy.getHealth());

        handleEnemyAttackSystem.update(model, new Time(10, 0.1f));

        Health marioHealthAfter = marioBehindEnemy.getHealth();

        assertEquals(marioHealthBefore, marioHealthAfter);
    }

    @Test
    public void EnemyShouldNotHitTowerInWrongLane() {
        Health marioHealthBefore = new Health(marioWrongLane.getHealth());

        handleEnemyAttackSystem.update(model, new Time(10, 0.1f));

        Health marioHealthAfter = marioWrongLane.getHealth();

        assertEquals(marioHealthBefore, marioHealthAfter);
    }

    @Test
    public void GoombaShouldHitMario() {
        Health marioHealthBefore = new Health(mario.getHealth());

        handleEnemyAttackSystem.update(model, new Time(10, 0.1f));

        Health marioHealthAfter = mario.getHealth();

        assertNotEquals(marioHealthBefore, marioHealthAfter);
    }

    @Test
    public void TestIsTowerAheadTrue() {
        goomba.setTowerAhead(true);
        assertTrue(goomba.isTowerAhead());
    }

    @Test
    public void TestIsTowerAheadFalse() {
        goomba.setTowerAhead(false);
        assertFalse(goomba.isTowerAhead());
    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }

}

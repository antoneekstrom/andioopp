package andioopp.model.system.systems;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.enemy.EnemyFactory;
import andioopp.model.domain.enemy.enemies.Goomba;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.services.EnemyDropCoinService;
import andioopp.model.system.systems.RemoveDeadEnemiesSystem;
import andioopp.model.util.ModelCoordinate;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class RemoveDeadEnemiesSystemTest {

    private final Model model = getModel();

    private final Enemy goombaShouldBeDead = new Goomba(new ModelCoordinate(new Vector3f(1,1)));
    private final Enemy goombaShouldBeDeadMedRåge = new Goomba(new ModelCoordinate(new Vector3f(2,1)));
    private final Enemy goombaShouldLive = new Goomba(new ModelCoordinate(new Vector3f(3,1)));

    ListFactory listFactory = ArrayList::new;
    RemoveDeadEnemiesSystem removeDeadEnemiesSystem = new RemoveDeadEnemiesSystem(listFactory.create());

    @Before
    public void init() {
        model.getWorld().getEnemies().add(goombaShouldBeDead);
        model.getWorld().getEnemies().add(goombaShouldBeDeadMedRåge);
        model.getWorld().getEnemies().add(goombaShouldLive);


    }

    @Test
    public void EnemyShouldDie() {

        int sizeBefore = model.getWorld().getEnemies().size();

        goombaShouldBeDead.getHealth().decrease(goombaShouldBeDead.getHealth().get()); //Kills the enemy

        removeDeadEnemiesSystem.update(model, new Time(10, 0.2f)); //Run the system

        int sizeAfter = model.getWorld().getEnemies().size();

        assertEquals(sizeBefore, sizeAfter + 1); // Compares the number of enemies before vs after and checks if one enemy got deleted.

    }


    @Test
    public void EnemyShouldDieMedRåge() {

        int sizeBefore = model.getWorld().getEnemies().size();

        goombaShouldBeDeadMedRåge.getHealth().decrease(goombaShouldBeDeadMedRåge.getHealth().get() + 2); //Kills the enemy with more damage that its initial health.

        removeDeadEnemiesSystem.update(model, new Time(10, 0.2f)); //Run the system

        int sizeAfter = model.getWorld().getEnemies().size();

        assertEquals(sizeBefore, sizeAfter + 1); // Compares the number of enemies before vs after and checks if one enemy got deleted.

    }

    @Test
    public void EnemyShouldSurvive() {

        int sizeBefore = model.getWorld().getEnemies().size();

        removeDeadEnemiesSystem.update(model, new Time(10, 0.2f)); //Run the system

        int sizeAfter = model.getWorld().getEnemies().size();

        // The number of enemies should still be the same as before the call of the systems update method becuase the enemy still har health left.
        assertEquals(sizeBefore, sizeAfter);

    }

    @Test
    public void DoesAKilledEnemyDropACoin() {
        model.getWorld().addEnemy(EnemyFactory.createGoomba(model.getWorld(), 0));
        for(Enemy enemy : model.getWorld().getEnemies()){
            enemy.getHealth().decrease(enemy.getHealth().get());
        }
        removeDeadEnemiesSystem.addObserver(new EnemyDropCoinService(model));
        removeDeadEnemiesSystem.update(model, new Time(1, 1));
        assertTrue(model.getWorld().getDroppedCoins().size() > 0);
    }


    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}

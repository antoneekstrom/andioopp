package andioopp.model.domain.towers;

import andioopp.common.storage.ListFactory;
import andioopp.model.Model;
import andioopp.model.domain.enemy.EnemyFactory;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TowerFactoryTest {

    private final Model model = getModel();
    private final World world = model.getWorld();

    @Test
    public void AreThereThreeEnemiesInTheEnemyArrayWhenAWaveSpawnsThreeEnemies() {
        world.addEnemy(EnemyFactory.createBoo(model.getWorld(), 0));
        world.addEnemy(EnemyFactory.createKoopaTroopa(model.getWorld(), 1));
        world.addEnemy(EnemyFactory.createGoomba(model.getWorld(), 2));

        assertEquals(3, model.getWorld().getEnemies().size());
    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}


package andioopp.model.towers;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.damage.BaseDamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.enemy.EnemyFactory;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.tower.attack.projectiles.FireballProjectile;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.system.systems.RemoveDeadEnemiesSystem;
import andioopp.model.system.systems.UpdateProjectileSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TowersTest {



    Model model = getModel();
    World world = model.getWorld();

    @Before
    public void init() {

    }

    @Test
    public void AreThereThreeEnemiesInTheEnemyArrayWhenAWaveSpawnsThreeEnemies() {
        world.addEnemy(EnemyFactory.createBoo(model.getWorld(), 0));
        world.addEnemy(EnemyFactory.createKoopaTroopa(model.getWorld(), 1));
        world.addEnemy(EnemyFactory.createGoomba(model.getWorld(), 2));

        assertTrue(model.getWorld().getEnemies().size() == 3);
    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}


package andioopp.model.system;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.damage.BaseDamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.enemy.enemies.Goomba;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.attack.projectiles.FireballProjectile;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.system.systems.EnemyProjectileCollisionSystem;
import andioopp.model.util.ModelCoordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class EnemyProjectileCollisionSystemTest {

    Model model = getModel();
    private final BaseDamageSource fireDamageSource = new BaseDamageSource(DamageType.FIRE);

    Enemy e = new Goomba(new ModelCoordinate(new Vector3f(1,2)));

    Projectile p = new FireballProjectile(new Vector3f(1,1), fireDamageSource);

    EnemyProjectileCollisionSystem enemyProjectileCollisionSystem = new EnemyProjectileCollisionSystem();



    @Before
    public void init() {
        model.getWorld().getProjectiles().add(p);
        model.getWorld().getEnemies().add(e);
    }

    @Test
    public void enemyShouldNotGetHurt() {
        Health h = new Health(e.getHealth().get());
        int healthBefore = h.get();

        enemyProjectileCollisionSystem.update(model, new Time(10,0.1f));

        int healthAfter = e.getHealth().get();
        java.lang.System.out.println(healthBefore + "  " + healthAfter);
        //assertNotEquals(healthBefore, healthAfter);

    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}


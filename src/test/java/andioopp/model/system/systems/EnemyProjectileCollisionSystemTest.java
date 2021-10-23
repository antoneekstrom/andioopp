package andioopp.model.system.systems;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.damage.BaseDamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.enemy.enemies.Boo;
import andioopp.model.domain.enemy.enemies.Goomba;
import andioopp.model.domain.enemy.enemies.KoopaTroopa;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.TowerFactory;
import andioopp.model.domain.tower.attack.projectiles.FireballProjectile;
import andioopp.model.domain.tower.attack.projectiles.FlashlightProjectile;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.system.systems.EnemyProjectileCollisionSystem;
import andioopp.model.util.ModelCoordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;import static org.junit.Assert.*;

public class EnemyProjectileCollisionSystemTest {

    Model model = getModel();
    private final BaseDamageSource fireDamageSource = new BaseDamageSource(DamageType.FIRE, DamageType.GROUND);
    private final BaseDamageSource lightDamageSource = new BaseDamageSource(DamageType.LIGHT);

    private final Tower bobomb = TowerFactory.createBobomb(new ModelCoordinate(new Vector3f(0,0)));

    Enemy goomba = new Goomba(new ModelCoordinate(new Vector3f(2,2)));
    Enemy boo = new Boo(new ModelCoordinate(new Vector3f(3,3)));
    Enemy koopa = new KoopaTroopa(new ModelCoordinate(new Vector3f(2,1)));

    Projectile fireProjectile = new FireballProjectile(new Vector3f(2,2), fireDamageSource);
    Projectile lightProjectile = new FlashlightProjectile(new Vector3f(2,3), lightDamageSource);

    EnemyProjectileCollisionSystem enemyProjectileCollisionSystem = new EnemyProjectileCollisionSystem();

    Time time = new Time(0, 0.1f);

    @Before
    public void init() {
        model.getWorld().getProjectiles().add(fireProjectile);
        model.getWorld().getProjectiles().add(lightProjectile);
        model.getWorld().getEnemies().add(goomba);
        model.getWorld().getEnemies().add(boo);
        model.getWorld().getEnemies().add(koopa);
        model.getWorld().getCell(4,4).setTower(bobomb);
    }

    @Test
    public void bobombShouldNotKillEnemyOutOfRange() {
        Health h = new Health(koopa.getHealth());
        int healthBefore = h.get();

        enemyProjectileCollisionSystem.update(model, time);

        int healthAfter = koopa.getHealth().get();
        assertEquals(healthBefore, healthAfter);
    }

    @Test
    public void booShouldGetHurtByLightProjectile() {
        Health h = new Health(boo.getHealth());
        int healthBefore = h.get();

        enemyProjectileCollisionSystem.update(model, time);

        int healthAfter = boo.getHealth().get();
        assertTrue(healthBefore > healthAfter); //Boo's health should have been decreased.

        //java.lang.System.out.println(lightProjectile.getPosition().getX()+ " < " + boo.getPosition().getX() + " && " + lightProjectile.getPosition().getX() + " + " + lightProjectile.getSize().getWidth() + " > " + boo.getPosition().getX());
        //java.lang.System.out.println(lightProjectile.getPosition().getY() + " < " + boo.getPosition().getY() + " && " + lightProjectile.getPosition().getY() + " + " + lightProjectile.getSize().getHeight() + " > " + boo.getPosition().getY());
    }

    @Test
    public void enemyShouldGetHurt() {
        Health h = new Health(goomba.getHealth());
        int healthBefore = h.get();
        int i = 0;

        enemyProjectileCollisionSystem.update(model, time);

        int healthAfter = goomba.getHealth().get();

        java.lang.System.out.println(fireProjectile.getPosition().getX()+ " <= " + goomba.getPosition().getX() + " && " + fireProjectile.getPosition().getX() + " + " + fireProjectile.getSize().getWidth() + " > " + goomba.getPosition().getX());
        java.lang.System.out.println(fireProjectile.getPosition().getY() + " <= " + goomba.getPosition().getY() + " && " + fireProjectile.getPosition().getY() + " + " + fireProjectile.getSize().getHeight() + " > " + goomba.getPosition().getY());

        assertTrue(healthBefore > healthAfter);

    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}



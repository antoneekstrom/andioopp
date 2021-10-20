package andioopp.model.system;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.damage.BaseDamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.tower.attack.projectiles.FireballProjectile;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;

import java.util.ArrayList;

import andioopp.model.system.systems.RemoveDeadProjectilesSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveDeadProjectileSystemTest {

    private final Model model = getModel();
    private final BaseDamageSource fireDamageSource = new BaseDamageSource(DamageType.FIRE);

    private final Projectile p1 = new FireballProjectile(new Vector3f(1,1), fireDamageSource);

    RemoveDeadProjectilesSystem removeDeadProjectilesSystem = new RemoveDeadProjectilesSystem();

    @Before
    public void init() {
        model.getWorld().getProjectiles().add(p1);
    }

    @Test
    public void ProjectileShouldStillExist() {
        p1.getHealth().decrease(p1.getHealth().get()-1);  //Sets health of projectile to 1;

        int sizeBefore = model.getWorld().getProjectiles().size();

        removeDeadProjectilesSystem.update(model, new Time(10, 0.2f));

        int sizeAfter = model.getWorld().getProjectiles().size();

        assertEquals(sizeBefore, sizeAfter); //Due to projectile, p1, still being placed in game board the projectile should still exist.

    }

    @Test
    public void ProjectileShouldBeRemoved() {
        p1.getHealth().decrease(p1.getHealth().get());  //Sets health of projectile to 0;

        int sizeBefore = model.getWorld().getProjectiles().size();

        removeDeadProjectilesSystem.update(model, new Time(10, 0.2f));

        int sizeAfter = model.getWorld().getProjectiles().size();

        assertNotEquals(sizeBefore, sizeAfter); //Due to projectile, p1, still being placed in game board the projectile should still exist.

    }



    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }


}

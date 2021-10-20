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
import andioopp.model.system.systems.UpdateProjectileSystem;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class UpdateProjectileSystemTest {

    private final BaseDamageSource fireDamageSource = new BaseDamageSource(DamageType.FIRE);

    Projectile p = new FireballProjectile(new Vector3f(2,2), fireDamageSource);

    UpdateProjectileSystem updateProjectileSystem = new UpdateProjectileSystem();

    Model model = getModel();

    @Before
    public void init() {
        model.getWorld().getProjectiles().add(p);
    }

    @Test
    public void ProjectileShouldMove() {
        float yBefore = p.getPosition().getY();

        updateProjectileSystem.update(model, new Time(20, 0.1f));

        float yAfter = p.getPosition().getY();

        assertTrue(yBefore != yAfter);
    }

    private Model getModel() {
        ListFactory listFactory = ArrayList::new;
        World world = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5).build();
        Player p = new Player(listFactory.create());
        return new Model(world, p);
    }
}

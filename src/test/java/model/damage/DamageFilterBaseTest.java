package model.damage;

import andioopp.model.damage.DamageFilter;
import andioopp.model.damage.BaseDamageSource;
import andioopp.model.damage.DamageType;
import andioopp.model.damage.targets.FilterAny;
import andioopp.model.damage.targets.FilterFlying;
import andioopp.model.damage.targets.FilterGhost;
import andioopp.model.damage.targets.FilterGrounded;
import org.junit.Test;

import static org.junit.Assert.*;

public class DamageFilterBaseTest {

    private final DamageFilter FILTER_ANY = new FilterAny();
    private final BaseDamageSource SOURCE_ANY = new BaseDamageSource();

    private final BaseDamageSource FIREBALL = new BaseDamageSource(DamageType.FIRE, DamageType.GROUND);
    private final DamageFilter GOOMBA = new FilterGrounded();
    private final DamageFilter BOO = new FilterFlying().combineWith(new FilterGhost());

    @Test
    public void trivialFilterShouldBeDamagedByTrivialSource() {
        assertTrue(FILTER_ANY.canBeDamagedBy(SOURCE_ANY));
    }

    @Test
    public void booShouldIgnoreFireball() {
        assertFalse(BOO.canBeDamagedBy(FIREBALL));
    }

    @Test
    public void goombaShouldBeDamagedByFireball() {
        assertTrue(GOOMBA.canBeDamagedBy(FIREBALL));
    }
}

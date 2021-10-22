package andioopp.model.domain.tower.attack.attacks;

import andioopp.common.math.vector.Vector3f;
import andioopp.model.Model;
import andioopp.model.domain.damage.BaseDamageSource;
import andioopp.model.domain.damage.DamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.tower.attack.projectiles.FlashlightProjectile;
import andioopp.model.domain.tower.attack.strategies.SingleLaneForward;

/**
 * A flashlight attack.
 * Creates a flashlight
 */
public class FlashlightAttack extends Attack {

    private static final DamageSource DAMAGE_SOURCE = new BaseDamageSource(DamageType.LIGHT);

    public FlashlightAttack(float cooldown) {
        super(cooldown, new SingleLaneForward(3f), DAMAGE_SOURCE);
    }

    @Override
    protected void onAttack(Model model, Vector3f position) {
        model.getWorld().addProjectile(new FlashlightProjectile(position.add(new Vector3f(0.4f, -0.4f)), DAMAGE_SOURCE));
    }
}

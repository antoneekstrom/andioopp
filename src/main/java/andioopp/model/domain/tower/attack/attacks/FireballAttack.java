package andioopp.model.domain.tower.attack.attacks;

import andioopp.common.math.vector.Vector3f;
import andioopp.model.domain.tower.attack.projectiles.FireballProjectile;
import andioopp.model.Model;
import andioopp.model.domain.damage.BaseDamageSource;
import andioopp.model.domain.damage.DamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.tower.attack.strategies.SingleLaneForward;

public class FireballAttack extends Attack {

    private static final DamageSource DAMAGE_SOURCE = new BaseDamageSource(DamageType.FIRE, DamageType.GROUND);

    public FireballAttack(float coolDown) {
        super(coolDown, new SingleLaneForward(), DAMAGE_SOURCE);
    }

    /**
     * Spawns a fireball projectile at the position of the tower that is attacking.
     *
     * @param model    the model
     * @param position position of the tower, or wherever the attack is to be performed
     */
    @Override
    public void onAttack(Model model, Vector3f position) {
        model.getWorld().addProjectile(new FireballProjectile(position, DAMAGE_SOURCE));
    }
}

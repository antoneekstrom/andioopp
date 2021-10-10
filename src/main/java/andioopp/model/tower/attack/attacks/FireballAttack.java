package andioopp.model.tower.attack.attacks;

import andioopp.common.transform.Vector3f;
import andioopp.model.Model;
import andioopp.model.damage.BaseDamageSource;
import andioopp.model.damage.DamageSource;
import andioopp.model.damage.DamageType;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.projectiles.Fireball;
import andioopp.model.tower.attack.strategies.SingleLaneForward;

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
    public void performAttack(Model model, Vector3f position) {
        model.getWorld().addProjectile(new Fireball(position, DAMAGE_SOURCE));
    }
}

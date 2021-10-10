package andioopp.model.tower.attack.attacks;

import andioopp.common.transform.Vector3f;
import andioopp.model.damage.DamageSourceType;
import andioopp.model.damage.DamageTargetType;
import andioopp.model.world.World;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.projectiles.FireballProjectile;
import andioopp.model.tower.attack.strategies.SingleLaneForward;

import java.util.ArrayList;

public class FireballAttack extends Attack {

    //Enums
    public ArrayList<DamageTargetType> damageTargetTypes = new ArrayList<>();
    public ArrayList<DamageSourceType> damageSourceType = new ArrayList<>();

    public FireballAttack(float coolDown) {
        super(coolDown, new SingleLaneForward());
        damageTargetTypes.add(DamageTargetType.GROUND);
        damageSourceType.add(DamageSourceType.FIRE);
    }

    /**
     * Spawns a fireball projectile at the position of the tower that is attacking.
     * @param world
     * @param position position of the tower, or wherever the attack is to be performed
     */
    @Override
    public void performAttack(World world, Vector3f position) {
        world.addProjectile( new FireballProjectile(position, damageTargetTypes, damageSourceType) );
    }
}

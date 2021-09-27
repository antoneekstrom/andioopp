package andioopp.model.tower.attack.attacks;

import andioopp.common.transform.Vector3f;
import andioopp.model.world.World;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.projectiles.FireballProjectile;
import andioopp.model.tower.attack.strategies.SingleLaneForward;

public class FireballAttack extends Attack {

    public FireballAttack(float coolDown) {
        super(coolDown, new SingleLaneForward());
    }

    @Override
    public void performAttack(World world, Vector3f position) {
        world.addProjectile( new FireballProjectile(position) );
    }
}

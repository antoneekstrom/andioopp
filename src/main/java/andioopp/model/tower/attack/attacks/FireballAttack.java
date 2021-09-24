package andioopp.model.tower.attack.attacks;

import andioopp.common.transform.Vector3f;
import andioopp.model.World;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.AttackTargetArea;
import andioopp.model.tower.attack.projectiles.FireballProjectile;
import andioopp.model.tower.attack.strategies.SingleLaneForward;

public class FireballAttack extends Attack {
    private AttackTargetArea SingleLaneForward;

    public FireballAttack(World world, float coolDown, Vector3f position) {
        super(world, coolDown, position);
    }

    @Override
    public void performAttack(Tower tower) {
        getWorld().addProjectile( new FireballProjectile(getPosition()) );
        //Summon fireball entity at (row, col)
        //And then the fireball moves on its own
    }

    @Override
    public AttackTargetArea getTargetArea() {
        return SingleLaneForward;
    }
}

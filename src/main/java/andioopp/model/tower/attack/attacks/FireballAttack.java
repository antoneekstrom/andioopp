package andioopp.model.tower.attack.attacks;

import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.World;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.AttackTargetArea;
import andioopp.model.tower.attack.projectiles.FireballProjectile;
import andioopp.model.tower.attack.strategies.SingleLaneForward;

import java.util.ArrayList;

public class FireballAttack extends Attack {

    //Enums
    public ArrayList<FilterRequirement> requirements = new ArrayList<>();
    public ArrayList<FilterImmunity> immunity = new ArrayList<>();

    public FireballAttack(float coolDown) {
        super(coolDown, new SingleLaneForward());
        requirements.add(FilterRequirement.GROUND);
        immunity.add(FilterImmunity.FIREBALL);
    }

    @Override
    public void performAttack(World world, Vector3f position) {
        world.addProjectile( new FireballProjectile(position, requirements, immunity) );
    }
}

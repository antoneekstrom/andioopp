package andioopp.model.tower.attack.attacks;

import andioopp.common.transform.Vector3f;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.AttackTargetArea;
import andioopp.model.tower.attack.strategies.NonTargeting;
import andioopp.model.world.World;

public class DigCoinAttack extends Attack {

    public DigCoinAttack(float coolDown) {
        super(coolDown, new NonTargeting());
    }

    @Override
    public void performAttack(World world, Vector3f position) {
        world.getMoney().increase((int) Math.round(Math.random()*20) + 20);
    }
}

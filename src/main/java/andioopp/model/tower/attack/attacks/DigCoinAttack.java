package andioopp.model.tower.attack.attacks;

import andioopp.common.time.FxClock;
import andioopp.common.transform.Vector3f;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.strategies.NonTargeting;
import andioopp.model.world.World;

/**
 * An attack (sort of) which generates money
 * Mainly used by Toad.
 */
public class DigCoinAttack extends Attack {

    public DigCoinAttack(float coolDown) {
        super(coolDown, new NonTargeting());
        timeOfLastAttack = FxClock.nanosToSeconds(FxClock.getNowTimeNanos());
    }

    /**
     * Increases the amount of money by 20-40 units.
     * @param world
     * @param position position of the tower, or wherever the attack is to be performed
     */
    @Override
    public void performAttack(World world, Vector3f position) {
        world.getMoney().increase((int) Math.round(Math.random()*10) + 15);
    }
}

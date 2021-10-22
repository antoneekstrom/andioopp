package andioopp.model.domain.tower.attack.strategies;

import andioopp.common.math.vector.Vector3f;
import andioopp.model.domain.world.World;

/**
 * A targeting area used for towers that either don't attack enemies (e.g. Toad) or
 * for towers that attack without considering where enemies are located (e.g. Bob-omb).
 */
public class NonTargeting implements AttackTargetArea {

    @Override
    public boolean enemyIsInRange(Vector3f towerPosition, Vector3f enemyPosition, World world) {
        return true;
    }
}

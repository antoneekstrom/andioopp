package andioopp.model.domain.tower.attack.strategies;

import andioopp.common.math.Vector3f;
import andioopp.model.domain.tower.attack.AttackTargetArea;
import andioopp.model.domain.world.World;

public class NonTargeting implements AttackTargetArea {

    @Override
    public boolean enemyIsInRange(Vector3f towerPosition, Vector3f enemyPosition, World world) {
        return true;
    }
}

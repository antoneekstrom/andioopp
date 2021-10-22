package andioopp.model.domain.tower.attack.attacks;

import andioopp.common.math.vector.Vector3f;
import andioopp.model.Model;
import andioopp.model.domain.damage.BaseDamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.attack.strategies.NonTargeting;

/**
 * An explosion attack that eliminates all enemies within short range
 *
 * @author Arvid Svedberg
 */
public class Explosion extends Attack {

    private static final BaseDamageSource DAMAGE_SOURCE = new BaseDamageSource(
            DamageType.BOMB,
            DamageType.DIG,
            DamageType.GROUND,
            DamageType.FLYING,
            DamageType.WATER
    );

    public Explosion() {
        super(1f, new NonTargeting(), DAMAGE_SOURCE);
    }

    @Override
    public void onAttack(Model model, Vector3f position) {

        Tower tower = model.getWorld().getCell((int)position.getX(), (int)position.getY()).getTower();
        for (Enemy e : model.getWorld().getEnemies()) {
            if (isInRange(e, position) && e.canBeDamagedBy(DAMAGE_SOURCE)) {
                eliminateEnemy(e);
                eliminateTower(tower);
            }
        }
    }

    private boolean isInRange(Enemy e, Vector3f pos) {
        boolean isInRangeXAxis = (e.getPosition().getX() >= pos.getX() - 1.7f && pos.getX() + 2f >= e.getPosition().getX());
        boolean isInRangeYAxis = (e.getPosition().getY() >= pos.getY() - 1f && pos.getY() + 1f >= e.getPosition().getY());
        //if this is true then the enemy is in the 3x3 square surrounding the tower using this attack.
        return isInRangeXAxis && isInRangeYAxis;
    }

    private void eliminateTower(Tower tower) {

        tower.getHealth().decrease(tower.getHealth().get());
    }

    private void eliminateEnemy(Enemy e) {
        e.getHealth().decrease(e.getHealth().get());
    }
}

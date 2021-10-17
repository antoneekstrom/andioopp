package andioopp.model.tower.attack.attacks;

import andioopp.common.transform.Vector3f;
import andioopp.model.Model;
import andioopp.model.damage.BaseDamageSource;
import andioopp.model.damage.DamageType;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.strategies.NonTargeting;

/**
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

    public Explosion(float cooldown) {
        super(cooldown, new NonTargeting(), DAMAGE_SOURCE);
    }

    @Override
    public void performAttack(Model model, Vector3f position) {
        for (Enemy e : model.getWorld().getEnemies()) {
            if (isInRange(e, position)) {
                eliminateEnemy(e);
            }
        }
    }

    private boolean isInRange(Enemy e, Vector3f pos) {
        boolean isInRangeXAxis = (e.getPosition().getX() >= pos.getX() - 1 && pos.getX() + 1 >= e.getPosition().getX());
        boolean isInRangeYAxis = (e.getPosition().getY() >= pos.getY() - 1 && pos.getY() + 1 >= e.getPosition().getY());
        //if this is true then the enemy is in the 3x3 square surrounding the tower using this attack.
        return isInRangeXAxis && isInRangeYAxis;
    }

    private void eliminateEnemy(Enemy e) {
        e.getHealth().decrease(e.getHealth().get());
    }
}
package andioopp.model.tower.attack.attacks;

import andioopp.common.math.FloatRange;
import andioopp.common.transform.Vector3f;
import andioopp.model.Model;
import andioopp.model.damage.BaseDamageSource;
import andioopp.model.damage.DamageType;
import andioopp.model.player.Money;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.strategies.NonTargeting;

/**
 * An attack (sort of) which generates money
 */
public class DigCoinAttack extends Attack {

    private final FloatRange moneyRange;

    public DigCoinAttack(float cooldown, FloatRange moneyRange) {
        super(cooldown, new NonTargeting(), new BaseDamageSource(DamageType.ANY));
        this.moneyRange = moneyRange;
    }

    @Override
    public void performAttack(Model model, Vector3f position) {
        model.getPlayer().give(getRandomMoney());
    }

    private Money getRandomMoney() {
        return new Money(Math.round(moneyRange.getRandom()));
    }
}

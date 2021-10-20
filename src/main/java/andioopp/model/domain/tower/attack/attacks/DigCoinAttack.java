package andioopp.model.domain.tower.attack.attacks;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.math.range.FloatRange;
import andioopp.model.Model;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.damage.BaseDamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.tower.attack.strategies.NonTargeting;
import andioopp.model.util.ModelCoordinate;

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
    public void onAttack(Model model, Vector3f position) {
        spawnCoin(model, getCoinSpawnPosition(position), getRandomMoney());
    }

    private void spawnCoin(Model model, ModelCoordinate position, Money value) {
        model.getWorld().getDroppedCoins().add(new DroppedCoinEntity(position, value));
    }

    private ModelCoordinate getCoinSpawnPosition(Vector3f origin) {
        return new ModelCoordinate(origin.add(Vector3f.random().scale(0.5f)));
    }

    private Money getRandomMoney() {
        return new Money(Math.round(moneyRange.getRandom()));
    }
}

package andioopp.model.services;

import andioopp.common.observer.Observer;
import andioopp.model.Model;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.domain.money.Money;
import andioopp.model.system.events.EnemyDeathEvent;
import andioopp.model.util.ModelCoordinate;

/**
 * A service used to drop a coin when an enemy dies.
 */
public class EnemyDropCoinService implements Observer<EnemyDeathEvent> {

    private final Model model;

    public EnemyDropCoinService(Model model) {
        this.model = model;
    }

    @Override
    public void onEvent(EnemyDeathEvent event) {
        ModelCoordinate position = new ModelCoordinate(event.getPosition());
        Money reward = event.getEnemy().getReward();
        model.getWorld().getDroppedCoins().add(new DroppedCoinEntity(position, reward));
    }
}

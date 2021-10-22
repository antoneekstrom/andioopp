package andioopp.model.system.systems;

import andioopp.common.observer.ObservableCollection;
import andioopp.common.observer.Observer;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.system.System;
import andioopp.model.system.events.EnemyDeathEvent;

import java.util.Collection;

public class RemoveDeadEnemiesSystem extends ObservableCollection<EnemyDeathEvent> implements System<Model> {
/**
 * A class that handles removal of defeated Enemies.
 */
public class RemoveDeadEnemiesSystem implements System<Model>, Observable<EnemyDeathEvent, Observer<EnemyDeathEvent>> {

    private final Collection<Observer<EnemyDeathEvent>> observers;

    public RemoveDeadEnemiesSystem(Collection<Observer<EnemyDeathEvent>> observers) {
        super(observers);
    }

    @Override
    public void update(Model model, Time time) {
        removeDeadEnemies(model);
    }

    private void removeDeadEnemies(Model model) {
        model.getWorld().getEnemies().removeIf(enemy -> {
            boolean isDead = enemy.isDead();
            if(isDead) {
                notifyObservers(new EnemyDeathEvent(enemy));
            }
            return isDead;
        });
    }

    @Override
    public Collection<Observer<EnemyDeathEvent>> getObservers() {
        return observers;
    }
}

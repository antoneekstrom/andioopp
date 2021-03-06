package andioopp.model.system.systems;

import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableCollection;
import andioopp.common.observer.Observer;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.system.System;
import andioopp.model.system.events.EnemyDeathEvent;

import java.util.Collection;

public class RemoveDeadEnemiesSystem extends ObservableCollection<EnemyDeathEvent> implements System<Model> {

    public RemoveDeadEnemiesSystem(Collection<Observer<EnemyDeathEvent>> observers) {
        super(observers);
    }

    @Override
    public void update(Model model, Time time) {
        removeDeadEnemies(model);

        removeDeadProjectiles(model);
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

    private void removeDeadProjectiles(Model model) {
        model.getWorld().getProjectiles().removeIf(projectile -> {
            boolean isDead = projectile.getHealth().isDead();
            if(isDead) {

            }
            return isDead;
        });
    }
}

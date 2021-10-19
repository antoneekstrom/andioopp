package andioopp.model.system.singleUseSystems;

import andioopp.common.observer.Observer;
import andioopp.model.Model;
import andioopp.model.system.events.EnemyEscapeEvent;

import java.util.Collection;

public class EndGameSystem {

    private final Collection<Observer<EnemyEscapeEvent>> observers;

    public EndGameSystem(Collection<Observer<EnemyEscapeEvent>> observers) {
        this.observers = observers;
    }

    public void init(Model model) {

    }
}

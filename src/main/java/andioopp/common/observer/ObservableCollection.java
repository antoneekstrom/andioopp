package andioopp.common.observer;

import java.util.Collection;

/**
 * A collection of observables
 * @param <T> the type of observed event
 */
public class ObservableCollection<T> implements Observable<T> {

    private final Collection<Observer<T>> observers;

    public ObservableCollection(Collection<Observer<T>> observers) {
        this.observers = observers;
    }

    @Override
    public Collection<Observer<T>> getObservers() {
        return observers;
    }
}

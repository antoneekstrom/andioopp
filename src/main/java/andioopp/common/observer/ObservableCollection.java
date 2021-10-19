package andioopp.common.observer;

import java.util.Collection;

public class ObservableCollection<T> implements Observable<T> {

    private final Collection<Observer<T>> observables;

    public ObservableCollection(Collection<Observer<T>> observables) {
        this.observables = observables;
    }

    @Override
    public Collection<Observer<T>> getObservers() {
        return observables;
    }
}

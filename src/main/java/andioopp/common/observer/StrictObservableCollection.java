package andioopp.common.observer;

import java.util.Collection;

public class StrictObservableCollection<T, O extends Observer<T>> implements StrictObservable<T, O> {

    private final Collection<O> observables;

    public StrictObservableCollection(Collection<O> observables) {
        this.observables = observables;
    }

    @Override
    public Collection<O> getObservers() {
        return observables;
    }
}

package andioopp.common.observer;

import java.util.Collection;

public class CollectionObservable<T, O extends Observer<T>> implements Observable<T, O> {

    private final Collection<O> observables;

    public CollectionObservable(Collection<O> observables) {
        this.observables = observables;
    }

    @Override
    public Collection<O> getObservers() {
        return observables;
    }
}

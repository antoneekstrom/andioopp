package andioopp.common.observer;

import java.util.List;

public class ObservableWithListFactory<T> implements Observable<T> {
    private final List<Observer<T>> observers;
    public ObservableWithListFactory(List<Observer<T>> observers) {
        this.observers = observers;
    }
    public List<Observer<T>> getObservers() {
        return observers;
    }
}

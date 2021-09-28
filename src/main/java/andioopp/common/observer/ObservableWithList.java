package andioopp.common.observer;

import java.util.List;

public class ObservableWithList<T> implements Observable<T> {
    private final List<Observer<T>> observers;
    public ObservableWithList(List<Observer<T>> observers) {
        this.observers = observers;
    }
    public List<Observer<T>> getObservers() {
        return observers;
    }
}

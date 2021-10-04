package andioopp.common.observer;

import java.util.List;

public class ObservableWithList<T, O  extends Observer<T>> implements Observable<T, O> {
    private final List<O> observers;
    public ObservableWithList(List<O> observers) {
        this.observers = observers;
    }
    public List<O> getObservers() {
        return observers;
    }
}

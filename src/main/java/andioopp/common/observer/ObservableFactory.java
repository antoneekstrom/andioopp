package andioopp.common.observer;

import java.util.ArrayList;

public class ObservableFactory<T> {
    public Observable<T> create() {
        return new ObservableWithList<>(new ArrayList<>());
    }
}

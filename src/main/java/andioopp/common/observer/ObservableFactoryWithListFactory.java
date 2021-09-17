package andioopp.common.observer;

import andioopp.common.storage.ListFactory;

public class ObservableFactoryWithListFactory implements ObservableFactory {

    private final ListFactory listFactory;

    public ObservableFactoryWithListFactory(ListFactory listFactory) {
        this.listFactory = listFactory;
    }

    public <T> Observable<T> create() {
        return new ObservableWithList<>(getListFactory().create());
    }

    private ListFactory getListFactory() {
        return listFactory;
    }
}

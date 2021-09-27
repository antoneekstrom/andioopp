package andioopp.service.infrastructure.creation;

import andioopp.common.observer.ObservableWithListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Clock;
import andioopp.common.time.FxClock;
import andioopp.common.transform.TransformFactory;

public class CreationService {

    private final ListFactory listFactory;
    private final TransformFactory transformFactory;

    public CreationService(ListFactory listFactory, TransformFactory transformFactory) {
        this.listFactory = listFactory;
        this.transformFactory = transformFactory;
    }

    public Clock createClock() {
        return new FxClock(new ObservableWithListFactory<>(getListFactory().create()));
    }

    public TransformFactory getTransformFactory() {
        return transformFactory;
    }

    public ListFactory getListFactory() {
        return listFactory;
    }
}

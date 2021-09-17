package andioopp.common.storage;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public interface ListFactory {

    <T> List<T> create(int initialCapacity);

    default <T> List<T> create() {
        return create(0);
    }

    default <T> List<T> create(int initialSize, Supplier<T> supplier) {
        List<T> list = create(initialSize);
        for (int i = 0; i < initialSize; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    default <T> List<T> create(Collection<T> collectionToCopy) {
        List<T> copy = create(collectionToCopy.size());
        copy.addAll(collectionToCopy);
        return copy;
    }
}

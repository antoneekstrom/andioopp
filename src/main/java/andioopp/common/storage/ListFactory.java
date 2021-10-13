package andioopp.common.storage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * Creates lists.
 */
@FunctionalInterface
public interface ListFactory {

    /**
     * Creates a new list instance with an initial allocated size.
     * @param initialCapacity initially allocated capacity
     * @param <T> the type of list
     * @return the list
     */
    <T> List<T> create(int initialCapacity);

    /**
     * Creates a new list instance.
     * @param <T> the type of list
     * @return the list
     */
    default <T> List<T> create() {
        return create(0);
    }

    /**
     * Creates a new list instance and populates it with a given amount of items.
     * @param initialSize amount of items to put in the list
     * @param supplier supplies the list with items
     * @param <T> the type of list
     * @return the list
     */
    default <T> List<T> create(int initialSize, Supplier<T> supplier) {
        List<T> list = create(initialSize);
        for (int i = 0; i < initialSize; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * Creates a new list instance from an array.
     * @param array the array
     * @param <T> the type of list
     * @return the list
     */
    default <T> List<T> create(T ...array) {
        List<T> list = create(array.length);
        list.addAll(Arrays.asList(array));
        return list;
    }

    /**
     * Creates a new list instances and copies over the items of another given collection.
     * @param collectionToCopy the other collection
     * @param <T> the type of list
     * @return the list
     */
    default <T> List<T> create(Collection<T> collectionToCopy) {
        List<T> copy = create(collectionToCopy.size());
        copy.addAll(collectionToCopy);
        return copy;
    }
}

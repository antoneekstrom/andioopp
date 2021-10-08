package andioopp.common.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates arraylists.
 */
public class ArrayListFactory implements ListFactory {

    public <T> List<T> create(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

}

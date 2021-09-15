package andioopp.common.storage;

import java.util.ArrayList;
import java.util.List;

public class ArrayListFactory implements ListFactory {

    public <T> List<T> create(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

}

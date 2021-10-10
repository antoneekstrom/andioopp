package andioopp.model.damage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DamageSource {

    private final List<DamageSourceType> types;

    public DamageSource(DamageSourceType ...types) {
        this(Arrays.asList(types));
    }

    public DamageSource(List<DamageSourceType> types) {
        this.types = types;
    }

    public List<DamageSourceType> getTypes() {
        return Collections.unmodifiableList(types);
    }
}

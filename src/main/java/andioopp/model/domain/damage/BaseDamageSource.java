package andioopp.model.domain.damage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A base for all different Damage Sources.
 */
public class BaseDamageSource implements DamageSource {

    private final List<DamageType> types;

    public BaseDamageSource(DamageType... types) {
        this(Arrays.asList(types));
    }

    public BaseDamageSource(List<DamageType> types) {
        this.types = types;
    }

    @Override
    public List<DamageType> getTypes() {
        return Collections.unmodifiableList(types);
    }
}

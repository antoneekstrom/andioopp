package andioopp.model.domain.damage;

import java.util.List;

/**
 * A functional interface for DamageSources.
 */
@FunctionalInterface
public interface DamageSource {
    List<DamageType> getTypes();
}

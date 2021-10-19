package andioopp.model.domain.damage;

import java.util.List;

@FunctionalInterface
public interface DamageSource {
    List<DamageType> getTypes();
}

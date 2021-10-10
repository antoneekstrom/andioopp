package andioopp.model.damage;

import java.util.List;

@FunctionalInterface
public interface DamageSource {
    List<DamageType> getTypes();
}

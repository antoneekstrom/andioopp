package andioopp.model.domain.damage;

import java.util.Collection;

/**
 * TODO
 */
public interface DamageFilter {

    boolean isImmuneAgainst(DamageSource src);

    boolean meetsAllRequirements(DamageSource src);

    default boolean isRequirementMet(Collection<DamageType> types, DamageType requirement) {
        return types.contains(requirement);
    }

    default boolean canBeDamagedBy(DamageSource src) {
        return isRequirementMet(src.getTypes(), DamageType.ANY) || (!isImmuneAgainst(src) && meetsAllRequirements(src));
    }

    default DamageFilter combineWith(DamageFilter other) {
        DamageFilter self = this;
        return new DamageFilter() {
            @Override
            public boolean isImmuneAgainst(DamageSource src) {
                return self.isImmuneAgainst(src) || other.isImmuneAgainst(src);
            }

            @Override
            public boolean meetsAllRequirements(DamageSource src) {
                return self.meetsAllRequirements(src) && other.meetsAllRequirements(src);
            }
        };
    }

}

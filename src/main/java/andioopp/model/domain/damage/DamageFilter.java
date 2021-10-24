package andioopp.model.domain.damage;

import java.util.Collection;

/**
 * TODO
 */
public interface DamageFilter {

    /**
     * Returns true if this filter is immune against any damagetype in the source.
     *
     * @param src the damage source
     * @return if the filter is immune
     */
    boolean isImmuneAgainst(DamageSource src);

    /**
     * Returns true if the source has all required damagetypes for this filter.
     *
     * @param src the damage source
     * @return if the source has all required types
     */
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

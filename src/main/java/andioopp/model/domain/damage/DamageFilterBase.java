package andioopp.model.domain.damage;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A class that handles Damage Filters.
 */
public class DamageFilterBase implements DamageFilter {

    private static final ListFactory DEFAULT_LISTFACTORY = new ArrayListFactory();
    private final List<DamageType> immunities;
    private final List<DamageType> requirements;

    public DamageFilterBase(List<DamageType> requirements, List<DamageType> immunities) {
        this.immunities = immunities;
        this.requirements = requirements;
    }

    protected DamageFilterBase(ListFactory listFactory) {
        this.requirements = getRequirements(listFactory);
        this.immunities = getImmunities(listFactory);
    }

    protected DamageFilterBase() {
        this(DEFAULT_LISTFACTORY);
    }

    @Override
    public boolean meetsAllRequirements(DamageSource src) {
        return requirements.stream().allMatch((requirement) -> isRequirementMet(src.getTypes(), requirement));
    }

    @Override
    public boolean isImmuneAgainst(DamageSource src) {
        return src.getTypes().stream().anyMatch(this::isImmuneAgainst);
    }

    public boolean isImmuneAgainst(DamageType type) {
        return immunities.contains(type);
    }

    protected List<DamageType> getImmunities(ListFactory listFactory) {
        return Collections.emptyList();
    }

    protected List<DamageType> getRequirements(ListFactory listFactory) {
        return Collections.emptyList();
    }
}

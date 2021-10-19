package andioopp.model.domain.damage;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DamageFilterBase implements DamageFilter {

    private static final ListFactory DEFAULT_LISTFACTORY = new ArrayListFactory();
    private final List<DamageType> immunities;
    private final List<DamageType> requirements;

    protected DamageFilterBase(ListFactory listFactory) {
        this.requirements = getRequirements(listFactory);
        this.immunities = getImmunities(listFactory);
    }

    protected DamageFilterBase() {
        this(DEFAULT_LISTFACTORY);
    }

    @Override
    public boolean canBeDamagedBy(DamageSource src) {
        return isRequirementMet(src.getTypes(), DamageType.ANY) || (!isImmuneAgainst(src) && meetsAllRequirements(src));
    }

    protected List<DamageType> getImmunities(ListFactory listFactory) {
        return Collections.emptyList();
    }

    protected List<DamageType> getRequirements(ListFactory listFactory) {
        return Collections.emptyList();
    }

    private boolean meetsAllRequirements(DamageSource src) {
        return requirements.stream().allMatch((requirement) -> isRequirementMet(src.getTypes(), requirement));
    }

    private boolean isRequirementMet(Collection<DamageType> types, DamageType requirement) {
        return types.contains(requirement);
    }

    private boolean isImmuneAgainst(DamageSource src) {
        return src.getTypes().stream().anyMatch(this::isImmuneAgainst);
    }

    private boolean isImmuneAgainst(DamageType type) {
        return immunities.contains(type);
    }
}

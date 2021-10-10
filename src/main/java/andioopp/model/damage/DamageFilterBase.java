package andioopp.model.damage;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DamageFilterBase implements DamageFilter {

    private final List<DamageSourceType> immunities;
    private final List<DamageSourceType> requirements;

    private static final ListFactory DEFAULT_LISTFACTORY = new ArrayListFactory();

    protected DamageFilterBase(ListFactory listFactory) {
        this.requirements = getRequirements(listFactory);
        this.immunities = getImmunities(listFactory);
    }

    protected DamageFilterBase() {
        this(DEFAULT_LISTFACTORY);
    }

    public boolean canBeDamagedBy(DamageSource src) {
        return !isImmuneAgainst(src) && meetsAllRequirements(src);
    }

    protected List<DamageSourceType> getImmunities(ListFactory listFactory) {
        return Collections.emptyList();
    }

    protected List<DamageSourceType> getRequirements(ListFactory listFactory) {
        return Collections.emptyList();
    }

    private boolean meetsAllRequirements(DamageSource src) {
        return requirements.stream().allMatch((requirement) -> isRequirementMet(src.getTypes(), requirement));
    }

    private boolean isRequirementMet(Collection<DamageSourceType> types, DamageSourceType requirement) {
        return types.contains(requirement);
    }

    private boolean isImmuneAgainst(DamageSource src) {
        return src.getTypes().stream().anyMatch(this::isImmuneAgainst);
    }

    private boolean isImmuneAgainst(DamageSourceType type) {
        return immunities.contains(type);
    }
}

package andioopp.model.domain.damage.targets;

import andioopp.common.storage.ListFactory;
import andioopp.model.domain.damage.DamageFilterBase;
import andioopp.model.domain.damage.DamageType;

import java.util.List;

public class FilterFlying extends DamageFilterBase {
    @Override
    protected List<DamageType> getRequirements(ListFactory listFactory) {
        return listFactory.create(DamageType.FLYING);
    }
}

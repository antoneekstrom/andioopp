package andioopp.model.damage.targets;

import andioopp.common.storage.ListFactory;
import andioopp.model.damage.DamageFilterBase;
import andioopp.model.damage.DamageSourceType;

import java.util.List;

public class FilterGrounded extends DamageFilterBase {
    @Override
    protected List<DamageSourceType> getRequirements(ListFactory listFactory) {
        return listFactory.create(DamageSourceType.GROUND);
    }
}

package andioopp.model.damage.targets;

import andioopp.common.storage.ListFactory;
import andioopp.model.damage.DamageFilterBase;
import andioopp.model.damage.DamageType;

import java.util.List;

public class FilterGrounded extends DamageFilterBase {
    @Override
    protected List<DamageType> getRequirements(ListFactory listFactory) {
        return listFactory.create(DamageType.GROUND);
    }
}

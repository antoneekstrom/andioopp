package andioopp.model.domain.damage.targets;

import andioopp.common.storage.ListFactory;
import andioopp.model.domain.damage.DamageFilterBase;
import andioopp.model.domain.damage.DamageType;

import java.util.List;
/**
 * Filters DamageTypes considering Ghost-type enemies.
 */
public class FilterGhost extends DamageFilterBase {

    @Override
    protected List<DamageType> getRequirements(ListFactory listFactory) {
        return listFactory.create(DamageType.LIGHT);
    }
}

package andioopp.model.domain.tower.towers;

import andioopp.common.storage.ArrayListFactory;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.attack.attacks.Attack;
import andioopp.model.domain.tower.Tower;
import andioopp.model.util.ModelCoordinate;

import java.util.List;

/**
 * A currently unimplemented Rosalina tower.
 */
public class Rosalina extends Tower {
    private static final String SPRITE_PATH = "rosalina.png";

    public Rosalina(ModelCoordinate position) {
        super(position, SPRITE_PATH, "Rosalina", new Health(10));
    }

    @Override
    protected List<Attack> createAttacks() {
        return new ArrayListFactory().create();
    }
}

package andioopp.model.domain.tower.towers;

import andioopp.common.storage.ArrayListFactory;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.tower.Tower;

import java.util.List;

public class Rosalina extends Tower {
    private static final String SPRITE_PATH = "rosalina-removebg-preview.png";

    public Rosalina() {
        super(SPRITE_PATH, "Rosalina", 500, 10);
    }

    @Override
    protected List<Attack> createAttacks() {
        return new ArrayListFactory().create();
    }
}

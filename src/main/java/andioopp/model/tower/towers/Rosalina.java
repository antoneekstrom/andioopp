package andioopp.model.tower.towers;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Arrays;
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

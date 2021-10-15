package andioopp.model.domain.tower.towers;

import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.tower.attack.attacks.FireballAttack;
import andioopp.model.domain.tower.Tower;

import java.util.Arrays;
import java.util.List;


public class Mario extends Tower {

    private static final String SPRITE_PATH = "mario.png";

    //TODO se toad, attacklistan kan inte vara statisk typ, för då finns bara en instans av attacken
    public Mario() {
        super(SPRITE_PATH, "Mario", 60, 5);
    }

    @Override
    protected List<Attack> createAttacks() {
        return Arrays.asList(new FireballAttack(2.5f));
    }
}

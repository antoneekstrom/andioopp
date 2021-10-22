package andioopp.model.domain.tower.towers;

import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.attack.attacks.Attack;
import andioopp.model.domain.tower.attack.attacks.Explosion;
import andioopp.model.util.ModelCoordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Bob-omb tower that explodes damaging nearby enemies.
 * Bob-omb dissappears after exploding.
 */
public class Bobomb extends Tower {
    private static final String SPRITE_PATH = "bobomb.png";

    public Bobomb(ModelCoordinate position) {
        super(position, SPRITE_PATH, "Bobomb", new Health(5));
    }

    @Override
    protected List<Attack> createAttacks() {
        return new ArrayList<>(Arrays.asList(new Explosion()));
    }
}

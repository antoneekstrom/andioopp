package andioopp.model.tower.attack.attacks;

import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.strategies.NonTargeting;
import andioopp.model.world.World;

import java.util.ArrayList;

public class Explosion extends Attack {

    //Enums
    public ArrayList<FilterRequirement> requirements = new ArrayList<>();
    public ArrayList<FilterImmunity> immunity = new ArrayList<>();

    public Explosion() {
        super(0, new NonTargeting());
        requirements.add(FilterRequirement.GROUND);
        requirements.add(FilterRequirement.DIGGING);
        requirements.add(FilterRequirement.FLYING);
        requirements.add(FilterRequirement.WATER);
        immunity.add(FilterImmunity.BOMB);
    }

    @Override
    public void performAttack(World world, Vector3f position) {
        for (Enemy e : world.getEnemies()) {
            if (isInRange(e, position)) {
                eliminate(world, e);
            }
        }
    }

    //TODO vet inte ifall detta ska ligga i en projectile istället eftersom
    // att det är där vi kollar requirements och immunities osv vilket
    // behövs här också, dock är ju inte en explosion en projectile egentligen.. ??

    private boolean isInRange(Enemy e, Vector3f pos) {
        boolean isInRangeXAxis = (e.getPosition().getX() >= pos.getX() - 1  &&  pos.getX() + 1 >= e.getPosition().getX());
        boolean isInRangeYAxis = (e.getPosition().getY() >= pos.getY() - 1  &&  pos.getY() + 1 >= e.getPosition().getY());
        //if this is true then the enemy is in the 3x3 square surrounding the tower using this attack.
        return isInRangeXAxis && isInRangeYAxis;
    }

    private void eliminate(World world, Enemy e) {
        e.getHealth().decrease(e.getHealth().get());

    }
}

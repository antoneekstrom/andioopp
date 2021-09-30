package andioopp.model.tower.attack.projectiles;

import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;

import java.util.ArrayList;

public class FireballProjectile extends Projectile {

    public ArrayList<FilterRequirement> requirements;
    public ArrayList<FilterImmunity> immunity;
    private int pierceCounter = 0;

    public FireballProjectile(Vector3f position, ArrayList<FilterRequirement> requirements, ArrayList<FilterImmunity> immunity) {
        super(position, requirements, immunity);
        requirements.add(FilterRequirement.GROUND);
        immunity.add(FilterImmunity.FIREBALL);
    }

    @Override
    public boolean shouldRemove() {
        pierceCounter++;
        if (pierceCounter >= 5){
            return true;
        }
        return false;
    }


    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(0.02f,0,0));
    }
}

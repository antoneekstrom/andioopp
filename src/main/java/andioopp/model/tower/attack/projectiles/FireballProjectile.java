package andioopp.model.tower.attack.projectiles;

import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;
import andioopp.model.damage.DamageSourceType;
import andioopp.model.damage.DamageTargetType;

import java.util.ArrayList;

public class FireballProjectile extends Projectile {

    public ArrayList<DamageTargetType> damageTargetTypes;
    public ArrayList<DamageSourceType> damageSourceType;
    private int pierceCounter = 0;

    public FireballProjectile(Vector3f position, ArrayList<DamageTargetType> damageTargetTypes, ArrayList<DamageSourceType> damageSourceType) {
        super(position, damageTargetTypes, damageSourceType);
        damageTargetTypes.add(DamageTargetType.GROUND);
        damageSourceType.add(DamageSourceType.FIRE);
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
        getTransform().translate(new Vector3f(time.getDeltaSeconds()));
    }
}

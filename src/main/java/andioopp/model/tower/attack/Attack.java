package andioopp.model.tower.attack;

import andioopp.common.time.Time;
import andioopp.model.tower.Tower;

public abstract class Attack {
    private final float coolDown;
    private float timeSinceLastAttack;

    public Attack(float coolDown) {
        this.coolDown = coolDown;
    }

    public abstract void performAttack(Tower tower);

    public boolean isAvailableForAttack(Time time){
        float deltaSeconds = time.getElapsedSeconds() - timeSinceLastAttack;
        return(deltaSeconds > this.coolDown);
    }

    public void updateTimeSinceLastAttack(Time time) {
        this.timeSinceLastAttack = time.getElapsedSeconds();
    }

    public abstract AttackTargetArea getTargetArea();


}


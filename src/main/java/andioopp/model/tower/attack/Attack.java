package andioopp.model.tower.attack;

import andioopp.model.tower.Tower;

public abstract class Attack {
    public abstract void performAttack(Tower tower);
    public abstract boolean isAvailable();
    public abstract AttackTargetArea getTargetArea();
}

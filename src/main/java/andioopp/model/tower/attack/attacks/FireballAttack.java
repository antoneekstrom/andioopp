package andioopp.model.tower.attack.attacks;

import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.AttackTargetArea;
import andioopp.model.tower.attack.strategies.SingleLaneForward;

public class FireballAttack extends Attack {
    private AttackTargetArea SingleLaneForward;

    public FireballAttack(float coolDown, int row, int col) {
        super(coolDown, row, col);
    }

    @Override
    public void performAttack(Tower tower) {
        //Summon fireball entity at (row, col)
        //And then the fireball moves on its own
    }

    @Override
    public AttackTargetArea getTargetArea() {
        return SingleLaneForward;
    }
}

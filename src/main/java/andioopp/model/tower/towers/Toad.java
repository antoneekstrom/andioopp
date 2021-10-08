package andioopp.model.tower.towers;

import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.DigCoinAttack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Toad extends Tower {

    private static final String SPRITE_PATH = "toad.png";
    private static final Attack[] attackList = {new DigCoinAttack(3f)};
    //TODO Det fungerar inte att ha en statisk attacklista för att då körs bara attacken en gång typ
    //Det finns bara en instans av den liksom


    public Toad() {
        super(SPRITE_PATH,"Toad", 60, 5, new ArrayList<>(Arrays.asList(new DigCoinAttack(3f))));

    }

    public boolean canAttack(Collection<Enemy> enemies){
        return true;
    }

}

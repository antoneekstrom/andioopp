package andioopp.model.tower.attack;

import andioopp.model.World;
import andioopp.model.enemy.Enemy;

import java.util.Collection;

public interface AttackTargetArea {
    Collection<Enemy> getEnemiesInArea(World world);
}

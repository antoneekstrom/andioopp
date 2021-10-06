package model.world;

import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.Model;
import andioopp.model.enemy.Enemies;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.attack.projectiles.FireballProjectile;
import andioopp.model.tower.attack.projectiles.Projectile;
import andioopp.model.waves.WaveQueue;
import andioopp.model.world.World;
import com.sun.source.tree.AssertTree;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import andioopp.model.*;
import andioopp.model.enemy.Enemies;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import andioopp.model.tower.towers.Mario;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class WorldTest {

    private WaveQueue waveQueue = new WaveQueue();
    private Model model = new Model(waveQueue);
    private ArrayList<FilterRequirement> FireballRequirements = new ArrayList<>();
    private ArrayList<FilterImmunity> FireballImmunity = new ArrayList<>();
    private Collection<Projectile> projectiles;

    @Before
    public void setup() {
        FireballRequirements.add(FilterRequirement.GROUND);
        FireballImmunity.add(FilterImmunity.FIREBALL);

    }

    @Test
    public void testIsEnemyDead() {
        Enemies enemies = new Enemies();
        Enemy enemy1 = enemies.createRandomEnemy(world, 1);
        Enemy enemy2 = enemies.createRandomEnemy(world, 1);

        enemy1.getHealth().decrease(enemy1.getHealth().get());
        assertTrue(world.isEnemyDead(enemy1));

        enemy2.getHealth().decrease(enemy2.getHealth().get() - 1);
        assertFalse(world.isEnemyDead(enemy2));
    }

    /*
    @Test
    public void testIsContact(){

    }

    @Test
    public void testDespawnOutOfBoundProjectile() {
        Vector3f v1 = new Vector3f(1,1);
        Vector3f v2 = new Vector3f(world.getNumberOfCellsInLanes() + 1,1);
        Projectile NOTOutOfBound = new FireballProjectile(v1, FireballRequirements, FireballImmunity);
        Projectile outOfBound = new FireballProjectile(v2, FireballRequirements, FireballImmunity);
        projectiles.add(NOTOutOfBound);
        projectiles.add(outOfBound);

    }*/


}

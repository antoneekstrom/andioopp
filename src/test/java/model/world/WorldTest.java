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
import andioopp.model.world.Cell;
import andioopp.model.world.Lane;
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
import java.util.List;

public class WorldTest {

    private WaveQueue waveQueue = new WaveQueue();
    private Model model = new Model(waveQueue);
    private ArrayList<FilterRequirement> FireballRequirements = new ArrayList<>();
    private ArrayList<FilterImmunity> FireballImmunity = new ArrayList<>();


    @Before
    public void setup() {
        FireballRequirements.add(FilterRequirement.GROUND);
        FireballImmunity.add(FilterImmunity.FIREBALL);

    }

    @Test
    public void testIsEnemyDead() {
        Enemies enemies = new Enemies();
        Enemy enemy1 = enemies.createRandomEnemy(model.getWorld(), 1);
        Enemy enemy2 = enemies.createRandomEnemy(model.getWorld(), 1);
        Enemy enemy3 = enemies.createRandomEnemy(model.getWorld(), 1);

        //checks if enemy is dead if decreasing it´s health to 0.
        enemy1.getHealth().decrease(enemy1.getHealth().get());
        assertTrue(model.getWorld().isEnemyDead(enemy1));

        //checks if enemy is dead when decreasing its health to 1.
        enemy2.getHealth().decrease(enemy2.getHealth().get() - 1);
        assertFalse(model.getWorld().isEnemyDead(enemy2));

        //checks if enemy is dead when decreasing it´s health to -1.
        enemy3.getHealth().decrease(enemy3.getHealth().get() + 1);
        assertTrue(model.getWorld().isEnemyDead(enemy3));
    }


    @Test
    public void testGetNumberOfLanes(){
        List<Cell> cells = new ArrayList<>();
        Cell cell = new Cell();
        cells.add(cell);
        Lane lane = new Lane(cells);
        //Clears all lanes from the world
        model.getWorld().getLanes().clear();
        //Adds one single lane
        model.getWorld().getLanes().add(lane);
        //checks if nuber of lanes is 1.
        assertEquals(1, model.getWorld().getNumberOfLanes());
    }

    @Test
    public void testAddProjectiles() {
        Vector3f v = new Vector3f(1, 1);
        Collection<Projectile> projectiles = new ArrayList<>();
        Projectile projectile = new FireballProjectile(v, FireballRequirements, FireballImmunity);
        projectiles.add(projectile);
        model.getWorld().addProjectile(projectile);

        assertTrue(projectiles.contains(projectile));
    }


}

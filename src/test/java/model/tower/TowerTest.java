package model.tower;

import static org.junit.Assert.*;

import andioopp.model.*;
import andioopp.model.enemy.Enemies;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import org.junit.Before;
import org.junit.Test;

public class TowerTest {

    private Tower tower;
    private Enemy enemy;
    private Model model;

    @Before
    public void setup() {
        model = new Model(new WaveQueue(), new Player());
        World world = model.getWorld();
        tower = Towers.mario();
        enemy = Enemies.goomba(world, 1);
    }

    @Test
    public void TestEnum() {
        FilterRequirement e1 = FilterRequirement.GROUND;
        FilterRequirement e2 = FilterRequirement.GROUND;

    }

    @Test
    public void TestFilter() {
        boolean b = tower.checkFilters(enemy);
        assertTrue(b);
    }
}

package model.tower;

import static org.junit.Assert.*;

import andioopp.model.*;
import andioopp.model.enemy.Enemies;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import andioopp.model.tower.towers.Mario;
import org.junit.Before;
import org.junit.Test;

public class TowerTest {

    private Tower tower;
    private Enemy goomba;
    private Enemy buzzyBeetle;
    private Model model;
    Tower mario = new Mario();

    /*

    @Before
    public void setup() {
        System.out.println( mario.requirements.size() + " REQUIEMREMNT SIZE I TESTIFLEN");
        model = new Model(new WaveQueue(), new Player());
        World world = model.getWorld();
        goomba = Enemies.goomba(world, 1);
        buzzyBeetle = Enemies.buzzyBeetle(world, 1);
    }


    @Test
    public void testtesttesttest() {
        int i = goomba.requirements.size();
        System.out.println(i + " Gomba req size");
        int j = mario.requirements.size();
        System.out.println(j + " mario req size");
    }

    @Test
    public void TestFilterTrue() {
        boolean s = mario.checkFilters(goomba);
        assertTrue(s);
    }

    @Test
    public void TestFilterFalse() {
        boolean f = mario.checkFilters(buzzyBeetle);
        assertFalse(f);
    }

    */
}
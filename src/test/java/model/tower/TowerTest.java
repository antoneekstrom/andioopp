package model.tower;

import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.towers.Mario;

public class TowerTest {

    private Tower tower;
    private Enemy goomba;
    private Enemy buzzyBeetle;
    private Model model;
    Tower mario = new Mario();

    /*

    @Before
    public void setup() {
        System.out.println( mario.damageTargetTypes.size() + " REQUIEMREMNT SIZE I TESTIFLEN");
        model = new Model(new WaveQueue(), new Player());
        World world = model.getWorld();
        goomba = Enemies.goomba(world, 1);
        buzzyBeetle = Enemies.buzzyBeetle(world, 1);
    }


    @Test
    public void testtesttesttest() {
        int i = goomba.damageTargetTypes.size();
        System.out.println(i + " Gomba req size");
        int j = mario.damageTargetTypes.size();
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

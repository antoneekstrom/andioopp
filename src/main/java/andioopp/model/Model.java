package andioopp.model;

public class Model {

    private final World world;
    private final WaveQueue waves;
    private final Player player;
    private int money = 0;

    public Model(World world, WaveQueue waves, Player player) {
        this.world = world;
        this.waves = waves;
        this.player = player;
    }

    public int getMoney() {
        return money;
    }

    public void buyTower() {

    }
}

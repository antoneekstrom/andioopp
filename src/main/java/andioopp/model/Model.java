package andioopp.model;

import andioopp.model.player.Player;
import andioopp.model.world.World;

public class Model {

    private final World world;
    private final Player player;

    public Model(World world, Player player) {
        this.player = player;
        this.world = world;
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }
}

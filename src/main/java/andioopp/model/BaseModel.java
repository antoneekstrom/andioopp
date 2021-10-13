package andioopp.model;

import andioopp.model.player.Player;
import andioopp.model.world.World;

public class BaseModel implements Model {

    private final World world;
    private final Player player;

    public BaseModel(World world, Player player) {
        this.player = player;
        this.world = world;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public World getWorld() {
        return world;
    }
}

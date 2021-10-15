package andioopp.plupp.model;

import andioopp.plupp.model.domain.world.World;

public class Model {

    private final World world;

    public Model(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}

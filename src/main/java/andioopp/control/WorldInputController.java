package andioopp.control;

import andioopp.model.world.World;
import andioopp.view.View;

public class WorldInputController {

    private final World world;
    private final View<?> view;

    public WorldInputController(World world, View<?> view) {
        this.world = world;
        this.view = view;
    }
}

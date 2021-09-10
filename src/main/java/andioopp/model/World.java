package andioopp.model;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Lane> lanes;

    public World(ArrayList<Lane> lanes) {
        this.lanes = lanes;
    }

    public List<Lane> getLanes() {
        return lanes;
    }
}

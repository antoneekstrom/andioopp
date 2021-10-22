package andioopp.model.domain.world;
import andioopp.common.storage.ListFactory;

import java.util.List;

/**
 * Creates worlds.
 */
public class WorldBuilder {

    private final ListFactory listFactory;
    private final LaneBuilder laneBuilder;

    private List<Lane> lanes;

    public WorldBuilder(LaneBuilder laneBuilder, ListFactory listFactory) {
        this.listFactory = listFactory;
        this.laneBuilder = laneBuilder;
    }

    /**
     * Builds a new World.
     */
    public World build() {
        return new World(getListFactory().create(getLanes()), getListFactory().create(), getListFactory().create(), getListFactory().create());
    }

    /**
     * Returns the WorldBuilder itself after updating the List Lanes based on the int numLanes.
     */
    public WorldBuilder setLanes(int numLanes) {
        return setLanes(getListFactory().create(numLanes, getLaneBuilder()::build));
    }

    /**
     * Returns the WorldBuilder itself after updating the List lanes.
     */
    public WorldBuilder setLanes(List<Lane> lanes) {
        this.lanes = lanes;
        return this;
    }

    /**
     * Returns a list of Lanes.
     */
    public List<Lane> getLanes() {
        return lanes;
    }


    private LaneBuilder getLaneBuilder() {
        return laneBuilder;
    }

    private ListFactory getListFactory() {
        return listFactory;
    }
}

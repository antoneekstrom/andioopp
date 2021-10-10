package andioopp.model.world;
import andioopp.common.storage.ListFactory;
import andioopp.model.player.Money;

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

    public World build() {
        return new World(getListFactory().create(getLanes()), getListFactory().create(), getListFactory().create());
    }

    public WorldBuilder setLanes(int numLanes) {
        return setLanes(getListFactory().create(numLanes, getLaneBuilder()::build));
    }

    public WorldBuilder setLanes(List<Lane> lanes) {
        this.lanes = lanes;
        return this;
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public LaneBuilder getLaneBuilder() {
        return laneBuilder;
    }

    private ListFactory getListFactory() {
        return listFactory;
    }
}

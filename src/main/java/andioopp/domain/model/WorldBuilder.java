package andioopp.domain.model;
import andioopp.common.storage.ListFactory;
import andioopp.domain.model.tower.Tower;

import java.util.List;

public class WorldBuilder {

    private final ListFactory listFactory;
    private final LaneBuilder laneBuilder;

    private List<Lane> lanes;

    public WorldBuilder(LaneBuilder laneBuilder, ListFactory listFactory) {
        this.listFactory = listFactory;
        this.laneBuilder = laneBuilder;
    }

    public World build() {
        return new World(getListFactory().create(getLanes()));
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

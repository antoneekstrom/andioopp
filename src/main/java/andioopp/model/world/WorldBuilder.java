package andioopp.model.world;
import andioopp.common.storage.ListFactory;
import andioopp.model.Money;

import java.util.List;

public class WorldBuilder {

    private final ListFactory listFactory;
    private final LaneBuilder laneBuilder;
    private final Money money;

    private List<Lane> lanes;

    public WorldBuilder(LaneBuilder laneBuilder, ListFactory listFactory, Money money) {
        this.listFactory = listFactory;
        this.laneBuilder = laneBuilder;
        this.money = money;
    }

    public World build() {
        return new World(getListFactory().create(getLanes()), getListFactory().create(), getListFactory().create(), money);
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

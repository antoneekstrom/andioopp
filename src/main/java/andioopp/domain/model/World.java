package andioopp.domain.model;

import andioopp.common.time.Time;

import java.util.List;

public class World implements Updateable {

    private final List<Lane> lanes;

    World(List<Lane> lanes) {
        this.lanes = lanes;
    }

    @Override
    public void update(Time time) {
    }

    public List<Lane> getLanes() {
        return lanes;
    }
}

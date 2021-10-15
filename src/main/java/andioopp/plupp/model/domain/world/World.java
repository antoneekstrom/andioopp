package andioopp.plupp.model.domain.world;

import andioopp.common.storage.ListFactory;
import andioopp.plupp.model.domain.coin.DroppedCoin;

import java.util.Collection;

public class World {

    private final Collection<DroppedCoin> droppedCoins;

    public World(ListFactory listFactory) {
        this.droppedCoins = listFactory.create();
    }

    public Collection<DroppedCoin> getDroppedCoins() {
        return droppedCoins;
    }
}

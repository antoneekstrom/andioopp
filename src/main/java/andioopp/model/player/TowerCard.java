package andioopp.model.player;

import andioopp.model.tower.Tower;

import java.util.function.Supplier;

public class TowerCard<T extends Tower> implements TransactionSupplier<T> {

    private final Money cost;
    private final Supplier<T> supplier;

    public TowerCard(Money cost, Supplier<T> supplier) {
        this.cost = cost;
        this.supplier = supplier;
    }

    @Override
    public Transaction<T> purchase(Money money) {
        return new Transaction<>(money, cost, supplier.get());
    }

    public Supplier<T> getSupplier() {
        return supplier;
    }

    public Money getCost() {
        return cost;
    }
}

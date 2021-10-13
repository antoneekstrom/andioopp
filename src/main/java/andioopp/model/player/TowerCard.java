package andioopp.model.player;

import andioopp.model.money.Money;
import andioopp.model.money.Transaction;
import andioopp.model.money.TransactionSupplier;
import andioopp.model.tower.Tower;

import java.util.function.Supplier;

/**
 * A card which the player can use to purchase a tower.
 * @author Anton Ekström
 * @param <T>
 */
public class TowerCard<T extends Tower> implements TransactionSupplier<T> {

    private final Money cost;
    private final Supplier<T> supplier;

    /**
     * Creates a tower card.
     * @param cost the cost of purchasing the tower
     * @param supplier the supplier which creates towers
     */
    public TowerCard(Money cost, Supplier<T> supplier) {
        this.cost = cost;
        this.supplier = supplier;
    }

    @Override
    public Transaction<T> purchase(Money money) {
        return Transaction.perform(money, cost, supplier.get());
    }

    public Supplier<T> getSupplier() {
        return supplier;
    }

    public Money getCost() {
        return cost;
    }
}

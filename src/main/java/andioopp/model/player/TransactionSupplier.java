package andioopp.model.player;

@FunctionalInterface
public interface TransactionSupplier<T> {
    Transaction<T> purchase(Money money);
}

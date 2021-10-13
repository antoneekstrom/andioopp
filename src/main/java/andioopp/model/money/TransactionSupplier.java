package andioopp.model.money;

/**
 * Creates transactions.
 * @param <T> the type of the result of a transaction
 * @author Anton Ekström
 */
@FunctionalInterface
public interface TransactionSupplier<T> {
    Transaction<T> purchase(Money money);
}

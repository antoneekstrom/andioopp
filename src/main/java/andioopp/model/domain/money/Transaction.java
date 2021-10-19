package andioopp.model.domain.money;

import andioopp.model.domain.money.exceptions.UnsuccessfulTransactionException;

/**
 * @param <T> the type of the result of the transaction
 * @author Anton Ekström
 */
public class Transaction<T> {

    private final Money remaining;
    private final T result;
    private final boolean successful;

    /**
     * Creates a transaction.
     *
     * @param remaining  the remaining money
     * @param result     the result
     * @param successful if the transaction was successful
     */
    private Transaction(Money remaining, T result, boolean successful) {
        this.remaining = remaining;
        this.result = result;
        this.successful = successful;
    }

    /**
     * Performs a transaction.
     *
     * @param money  the money to spend
     * @param cost   the cost of the transaction
     * @param result the result
     * @param <T>    the type of the result
     * @return the transaction
     */
    public static <T> Transaction<T> perform(Money money, Money cost, T result) {
        if (money.canSpend(cost)) {
            return new Transaction<>(money.spend(cost), result, true);
        } else {
            return new Transaction<>(money, result, false);
        }
    }

    /**
     * Returns the remaining money after the transaction.
     *
     * @return the remaining money
     */
    public Money remainingMoney() {
        return remaining;
    }

    /**
     * Returns the result of the transaction, if the transaction was succesful. Otherwise, throws an exception.
     *
     * @return the result of the transaction
     * @throws UnsuccessfulTransactionException() if the transaction was unsuccessful, use {@link #isSuccessful()} to check beforehand
     */
    public T getResult() {
        if (!isSuccessful()) {
            throw new UnsuccessfulTransactionException();
        }
        return result;
    }

    /**
     * Returns true if the transaction was successful.
     * Meaning, the money was spent and one can retrieve the result with {@link this#getResult()}.
     *
     * @return true if successful
     */
    public boolean isSuccessful() {
        return successful;
    }
}

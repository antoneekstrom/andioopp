package andioopp.model.domain.money.exceptions;

import andioopp.model.domain.money.Transaction;

/**
 * Indicates that the result of a {@link Transaction} could not be retrieved.
 * Would be thrown when trying the access the result of an unsuccessful transaction.
 * @author Anton Ekström
 */
public class UnsuccessfulTransactionException extends RuntimeException {
    public UnsuccessfulTransactionException() {
        super("Could not get result because the transaction was unsuccessful.");
    }
}

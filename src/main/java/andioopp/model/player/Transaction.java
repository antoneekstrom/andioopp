package andioopp.model.player;

public class Transaction<T> {

    private final Money remaining;
    private final T result;
    private final boolean successful;

    Transaction(Money money, Money cost, T result) {
        this.result = result;
        this.successful = money.canSpend(cost);
        this.remaining = money.spend(cost);
    }

    public Money remainingMoney() {
        return remaining;
    }

    public T getResult() throws Exception {
        if (!isSuccessful()) {
            throw new Exception();
        }
        return result;
    }

    public boolean isSuccessful() {
        return successful;
    }
}

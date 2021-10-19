package andioopp.model.domain.money;

/**
 * Stores money.
 * @author Anton Ekström
 */
public class Wallet {

    private Money money;

    public Wallet(Money money) {
        this.money = money;
    }

    public <T> Transaction<T> buy(TransactionSupplier<T> supplier) {
        Transaction<T> transaction = supplier.purchase(getMoney());
        if (transaction.isSuccessful()) {
            setMoney(transaction.remainingMoney());
        }
        return transaction;
    }

    public void add(Money money) {
        setMoney(getMoney().add(money));
    }

    private void setMoney(Money money) {
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }
}

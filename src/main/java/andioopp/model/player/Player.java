package andioopp.model.player;

import java.util.Collections;
import java.util.List;

public class Player {

    private Money money;
    private final List<TowerCard<?>> cards;

    public Player(List<TowerCard<?>> cards, Money money) {
        this.money = money;
        this.cards = cards;
    }

    public Player(List<TowerCard<?>> cards) {
        this(cards, new Money(0));
    }

    public Money give(Money money) {
        setMoney(getMoney().add(money));
        return getMoney();
    }

    public <T> Transaction<T> buy(TransactionSupplier<T> supplier) {
        Transaction<T> transaction = supplier.purchase(getMoney());
        setMoney(transaction.remainingMoney());
        return transaction;
    }

    public List<TowerCard<?>> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Money getMoney() {
        return money;
    }

    private void setMoney(Money money) {
        this.money = money;
    }
}

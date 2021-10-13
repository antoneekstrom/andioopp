package andioopp.model.player;

import java.util.Collections;
import java.util.List;

public class Player {

    private final Wallet wallet;
    private final List<TowerCard<?>> cards;

    public Player(List<TowerCard<?>> cards, Wallet wallet) {
        this.wallet = wallet;
        this.cards = cards;
    }

    public Player(List<TowerCard<?>> cards) {
        this(cards, new Wallet(new Money(10)));
    }

    public void giveMoney(Money money) {
        wallet.add(money);
    }

    public <T> Transaction<T> buy(TransactionSupplier<T> supplier) {
        return wallet.buy(supplier);
    }

    public Money getMoney() {
        return wallet.getMoney();
    }

    public List<TowerCard<?>> getCards() {
        return Collections.unmodifiableList(cards);
    }
}

package andioopp.model.domain.player;

import andioopp.model.domain.money.Money;
import andioopp.model.domain.money.Transaction;
import andioopp.model.domain.money.TransactionSupplier;
import andioopp.model.domain.money.Wallet;

import java.util.Collections;
import java.util.List;

/**
 * A class for the player of the game
 */
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

    /**
     * Adds money to wallet
     */
    public void giveMoney(Money money) {
        wallet.add(money);
    }

    /**
     * Returns a transaction
     */
    public <T> Transaction<T> buy(TransactionSupplier<T> supplier) {
        return wallet.buy(supplier);
    }

    /**
     * Returns current value of money in wallet
     */
    public Money getMoney() {
        return wallet.getMoney();
    }

    /**
     * Returns the cards of Towers available in the game
     */
    public List<TowerCard<?>> getCards() {
        return Collections.unmodifiableList(cards);
    }
}

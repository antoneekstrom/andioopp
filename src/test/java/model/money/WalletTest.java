package model.money;

import andioopp.model.money.Money;
import andioopp.model.money.Transaction;
import andioopp.model.money.TransactionSupplier;
import andioopp.model.money.Wallet;
import org.junit.Test;

import static org.junit.Assert.*;

public class WalletTest {

    private final int MONEY_VALUE = 7;
    private final Money BASE_MONEY = new Money(MONEY_VALUE);
    private final Money SMALLER_MONEY = new Money(4);
    private final Money BIGGER_MONEY = new Money(9);

    @Test
    public void buyShouldProduceSuccessfulTransaction() {
        Wallet wallet = new Wallet(BASE_MONEY);
        assertTrue(wallet.buy(createTransactionSupplier(SMALLER_MONEY)).isSuccessful());
    }

    @Test
    public void unsuccessfulTransactionShouldNotConsumeMoney() {
        Wallet wallet = new Wallet(BASE_MONEY);
        wallet.buy(createTransactionSupplier(BIGGER_MONEY));
        assertEquals(BASE_MONEY, wallet.getMoney());
    }

    @Test
    public void remainingMoneyShouldMatchWallet() {
        Wallet wallet = new Wallet(BASE_MONEY);
        assertEquals(wallet.getMoney(), wallet.buy(createTransactionSupplier(BIGGER_MONEY)).remainingMoney());
    }

    private TransactionSupplier<?> createTransactionSupplier(Money cost) {
        return (money) -> Transaction.perform(money, cost, null);
    }

}

package model.player;

import andioopp.model.player.Money;
import andioopp.model.player.SpendMoneyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {

    private final int MONEY_VALUE = 7;
    private final Money BASE_MONEY = new Money(MONEY_VALUE);
    private final Money NEGATIVE_MONEY = new Money(-5);
    private final Money SMALLER_MONEY = new Money(4);
    private final Money BIGGER_MONEY = new Money(9);

    @Test
    public void moneyShouldHaveMoneyValue() {
        assertEquals(BASE_MONEY.getMoney(), MONEY_VALUE);
    }

    @Test
    public void canNotSpendNegativeMoney() {
        assertFalse(BASE_MONEY.canSpend(NEGATIVE_MONEY));
    }

    @Test
    public void canNotSpendBiggerMoney() {
        assertFalse(BASE_MONEY.canSpend(BIGGER_MONEY));
    }

    @Test
    public void canSpendSmallerMoney() {
        assertTrue(BASE_MONEY.canSpend(SMALLER_MONEY));
    }

    @Test
    public void canSpendExactMoney() {
        assertTrue(BASE_MONEY.canSpend(BASE_MONEY));
    }

    @Test
    public void spendingMoneyMakesNewMoneySmaller() {
        Money expected = new Money(BASE_MONEY.getMoney() - SMALLER_MONEY.getMoney());
        assertEquals(expected, BASE_MONEY.spend(SMALLER_MONEY));
    }

    @Test
    public void spendingExactMoneyMakesNewMoneyZero() {
        assertEquals(new Money(0), BASE_MONEY.spend(BASE_MONEY));
    }

    @Test
    public void spendingNegativeMoneyThrowsException() {
        assertThrows(SpendMoneyException.class, () -> BASE_MONEY.spend(NEGATIVE_MONEY));
    }

    @Test
    public void spendingBiggerMoneyThrowsException() {
        assertThrows(SpendMoneyException.class, () -> BASE_MONEY.spend(BIGGER_MONEY));
    }
}

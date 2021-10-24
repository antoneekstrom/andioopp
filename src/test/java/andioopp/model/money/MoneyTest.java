package andioopp.model.money;

import andioopp.model.domain.money.Money;
import andioopp.model.domain.money.exceptions.SpendMoneyException;
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
        assertEquals(BASE_MONEY.getValue(), MONEY_VALUE);
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
        Money expected = new Money(BASE_MONEY.getValue() - SMALLER_MONEY.getValue());
        assertEquals(expected, BASE_MONEY.spend(SMALLER_MONEY));
    }

    @Test
    public void spendingExactMoneyMakesNewMoneyZero() {
        assertEquals(new Money(0), BASE_MONEY.spend(BASE_MONEY));
    }

    @Test
    public void spendingNegativeMoneyThrows() {
        assertThrows(SpendMoneyException.class, () -> BASE_MONEY.spend(NEGATIVE_MONEY));
    }

    @Test
    public void spendingBiggerMoneyThrows() {
        assertThrows(SpendMoneyException.class, () -> BASE_MONEY.spend(BIGGER_MONEY));
    }
}

package andioopp.model.money;

import andioopp.model.money.exceptions.SpendMoneyException;

import java.util.Objects;

/**
 * An immutable type which represents an amount of money.
 * <p>
 * Any operation on Money will yield a new money object with the result.
 */
public class Money {
    private final int money;

    /**
     * Creates money.
     *
     * @param money the money
     */
    public Money(int money) {
        this.money = money;
    }

    /**
     * Adds the value of another money.
     *
     * @param money the other money to add
     * @return the resulting money
     */
    public Money add(Money money) {
        return add(money.getValue());
    }

    /**
     * Spends the money.
     *
     * @param money the amount money to spend
     * @return the remaining money
     * @throws SpendMoneyException Caused by attempting to spend negative money or more money than there is available
     */
    public Money spend(Money money) {
        return spend(money.getValue());
    }

    /**
     * Returns true if spending the given amount of money is allowed.
     *
     * @param money the money to spend
     * @return true if allowed
     */
    public boolean canSpend(Money money) {
        return canSpend(money.getValue());
    }

    /**
     * Returns the value of the money.
     *
     * @return the money
     */
    public int getValue() {
        return money;
    }


    private Money spend(int amount) {
        if (canSpend(amount)) {
            return subtract(amount);
        } else {
            throw new SpendMoneyException();
        }
    }

    private Money add(int amount) {
        if (amount > 0) {
            return new Money(getValue() + amount);
        } else {
            throw new SpendMoneyException();
        }
    }

    private Money subtract(int amount) {
        return new Money(getValue() - amount);
    }

    private boolean canSpend(int amount) {
        return getValue() >= amount && 0 < amount;
    }


    @Override
    public String toString() {
        return String.format("Money(%d)", getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return getValue() == money1.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}

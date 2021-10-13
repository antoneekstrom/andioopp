package andioopp.model.player;

import java.util.Objects;

/**
 * An immutable type which represents an amount of money.
 * <p>
 * Any operation on Money will yield a new money object with the result.
 */
public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public Money add(Money money) {
        return add(money.getMoney());
    }

    public Money spend(Money money) {
        return spend(money.getMoney());
    }

    public boolean canSpend(Money money) {
        return canSpend(money.getMoney());
    }

    private Money spend(int amount) {
        if (canSpend(amount)) {
            return subtract(amount);
        } else {
            throw new RuntimeException();
        }
    }

    private Money add(int amount) {
        if (amount > 0) {
            return new Money(getMoney() + amount);
        } else {
            throw new RuntimeException();
        }
    }

    private Money subtract(int amount) {
        return new Money(getMoney() - amount);
    }

    private boolean canSpend(int amount) {
        return getMoney() >= amount && 0 < amount;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return String.format("Money(%d)", getMoney());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return getMoney() == money1.getMoney();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMoney());
    }
}

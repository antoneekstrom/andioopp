package andioopp.model.player;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public Money add(Money money) {
        return add(money.getMoney());
    }

    public Money spend(Money cost) {
        return spend(cost.getMoney());
    }

    public boolean canSpend(Money cost) {
        return canSpend(cost.getMoney());
    }

    private Money spend(int amount) {
        if (canSpend(money)) {
            return subtract(amount);
        }
        else {
            return this;
        }
    }

    private Money add(int amount) {
        if (amount > 0) {
            return new Money(getMoney() + amount);
        }
        else {
            return this;
        }
    }

    private Money subtract(int amount) {
        return new Money(getMoney() - amount);
    }

    private boolean canSpend(int amount) {
        return getMoney() >= amount;
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

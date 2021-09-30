package andioopp.model;

public class Money {
    int money;

    public Money(int startingMoney) {
        this.money = startingMoney;
    }

    public int increase(int amount) {
        money += amount;
        return money;
    }

    public int decrease(int amount) {
        money -= amount;
        return money;
    }

    public int get() {
        return money;
    }
}

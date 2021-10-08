package andioopp.model.stats;

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

    public boolean purchase(int amount) {
        if(amount > money) return false;

        money -= amount;
        return true;
    }

    public int get() {
        return money;
    }
}

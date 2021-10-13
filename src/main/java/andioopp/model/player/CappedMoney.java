package andioopp.model.player;

public class CappedMoney extends Money {

    private final int cap;

    public CappedMoney(int money, int cap) {
        super(money);
        this.cap = cap;
    }

    @Override
    public Money add(Money money) {
        Money result = super.add(money);
        if (result.getMoney() > getCap()) {
            return new Money(getCap());
        }
        else {
            return result;
        }
    }

    public int getCap() {
        return cap;
    }
}

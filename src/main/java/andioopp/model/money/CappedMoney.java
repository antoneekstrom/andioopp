package andioopp.model.money;

/**
 * Money which cannot exceed a given cap.
 * @author Anton Ekström
 */
public class CappedMoney extends Money {

    private final int cap;

    public CappedMoney(int money, int cap) {
        super(money);
        this.cap = cap;
    }

    @Override
    public Money add(Money money) {
        Money result = super.add(money);
        if (result.getValue() > getCap()) {
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

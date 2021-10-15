package andioopp.model.domain.stats;

public class Health {

    private int healthPoints;

    public Health(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int decrease(int value) {
        return add(-value);
    }

    public int add(int value) {
        if (isEqualToOrHigher(value)) {
            healthPoints = 0;
        }
        else {
            healthPoints += value;
        }

        return get();
    }
    public boolean isZero() {
        return healthPoints < 1;
    }

    public boolean isEqualToOrHigher(int value) {
        return get() <= value;
    }

    public int get() {
        return healthPoints;
    }
}

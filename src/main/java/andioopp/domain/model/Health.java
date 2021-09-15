package andioopp.domain.model;

public class Health {

    private float healthPoints;

    public Health(float healthPoints) {
        this.healthPoints = healthPoints;
    }

    public float decrease(float value) {
        return add(-value);
    }

    public float add(float value) {
        if (isEqualToOrHigher(value)) {
            healthPoints = 0;
        }
        else {
            healthPoints -= value;
        }

        return get();
    }

    public boolean isEqualToOrHigher(float value) {
        return get() <= value;
    }

    public float get() {
        return healthPoints;
    }
}

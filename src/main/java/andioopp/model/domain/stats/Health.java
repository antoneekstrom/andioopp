package andioopp.model.domain.stats;

import java.util.Objects;

/**
 * Stores healthpoints.
 *
 * @author Anton Ekström
 */
public class Health {

    private int healthPoints;

    public Health(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Health(Health health) {
        this.healthPoints = health.healthPoints;
    }

    /**
     * Decreases health by the given amount, to a minimum value of zero.
     *
     * @param value the value to decrease by
     */
    public Health decrease(int value) {
        increase(-value);
        return this;
    }

    /**
     * Increases health by the given amount.
     *
     * @param value the value to increase by
     */
    public Health increase(int value) {
        if (healthPoints + value < 0) {
            healthPoints = 0;
        }
        else {
            healthPoints += value;
        }
        return this;
    }

    /**
     * Returns true if healthpoints are zero.
     *
     * @return true if healthpoints are zero
     */
    public boolean isDead() {
        return healthPoints < 1;
    }

    /**
     * Returns the healthpoints
     * @return the healthpoints
     */
    public int get() {
        return healthPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Health health = (Health) o;
        return healthPoints == health.healthPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(healthPoints);
    }

    @Override
    public String toString() {
        return "Health{" +
                "healthPoints=" + healthPoints +
                '}';
    }

}

package andioopp.common.time;

import java.util.Objects;

/**
 * Represents a moment in time emitted by a {@link Clock}.
 */
public class Time {

    private final float time, deltaTime;

    public Time(float time, float deltaTime) {
        this.time = time;
        this.deltaTime = deltaTime;
    }

    /**
     * Returns time elapsed since the clock was started.
     * @return the elapsed time in seconds
     */
    public float getTime() {
        return time;
    }

    /**
     * Returns time elapsed since the last event emitted by a clock.
     * @return the elapsed time in seconds
     */
    public float getDeltaTime() {
        return deltaTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Float.compare(time.time, this.time) == 0 && Float.compare(time.deltaTime, deltaTime) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, deltaTime);
    }

    @Override
    public String toString() {
        return "Time{" +
                "time=" + time +
                ", deltaTime=" + deltaTime +
                '}';
    }
}

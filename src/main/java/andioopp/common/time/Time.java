package andioopp.common.time;

/**
 * Represents a moment in time emitted by a {@link Clock}.
 */
public class Time {

    private final float elapsedSeconds, deltaSeconds;

    public Time(float elapsedSeconds, float deltaSeconds) {
        this.elapsedSeconds = elapsedSeconds;
        this.deltaSeconds = deltaSeconds;
    }

    /**
     * Returns time elapsed since the clock was started.
     * @return the elapsed time in seconds
     */
    public float getElapsedSeconds() {
        return elapsedSeconds;
    }

    /**
     * Returns time elapsed since the last event emitted by a clock.
     * @return the elapsed time in seconds
     */
    public float getDeltaSeconds() {
        return deltaSeconds;
    }
}

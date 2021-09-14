package andioopp.common.time;

public class Time {

    private final float elapsedSeconds, deltaSeconds;

    public Time(float elapsedSeconds, float deltaSeconds) {
        this.elapsedSeconds = elapsedSeconds;
        this.deltaSeconds = deltaSeconds;
    }

    public float getElapsedSeconds() {
        return elapsedSeconds;
    }

    public float getDeltaSeconds() {
        return deltaSeconds;
    }
}

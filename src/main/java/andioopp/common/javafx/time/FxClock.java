package andioopp.common.javafx.time;

import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;
import andioopp.common.time.Clock;
import andioopp.common.time.Time;
import javafx.animation.AnimationTimer;

/**
 * Implementation of {@link Clock} which uses {@link AnimationTimer} from javafx.
 */
public class FxClock extends AnimationTimer implements Clock {

    private final Observable<Time, Observer<Time>> observable;
    private long previousTime = 0L;

    public FxClock(Observable<Time, Observer<Time>> observable) {
        this.observable = observable;
    }

    private long getDeltaTime(long currentTime) {
        long elapsed = currentTime - previousTime;
        previousTime = currentTime;
        return elapsed;
    }

    public static float nanosToSeconds(long nanos) {
        return (float) nanos * 0.000_000_001f;
    }

    @Override
    public void start() {
        super.start();
        previousTime = getNowTimeNanos();
    }

    public static long getNowTimeNanos() {
        return System.nanoTime();
    }

    @Override
    public void handle(long time) {
        observable.notifyObservers(new Time(nanosToSeconds(getNowTimeNanos()), nanosToSeconds(getDeltaTime(time))));
    }

    @Override
    public void listen(Observer<Time> listener) {
        observable.addObserver(listener);
    }

    @Override
    public void unlisten(Observer<Time> listener) {
        observable.removeObserver(listener);
    }

    @Override
    public void unlistenAll() {
        observable.getObservers().clear();
    }
}

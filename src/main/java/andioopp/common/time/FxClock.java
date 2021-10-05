package andioopp.common.time;

import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;
import javafx.animation.AnimationTimer;

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

    private float nanosToSeconds(long nanos) {
        return (float) nanos * 0.000_000_001f;
    }

    @Override
    public void start() {
        super.start();
        previousTime = System.nanoTime();
    }

    @Override
    public void handle(long time) {
        observable.notifyObservers(new Time(nanosToSeconds(time), nanosToSeconds(getDeltaTime(time))));
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

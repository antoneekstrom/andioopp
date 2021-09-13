package andioopp.common.time;

import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableFactory;
import andioopp.common.observer.Observer;
import javafx.animation.AnimationTimer;

public class FxClock extends AnimationTimer implements Clock {

    private final Observable<Time> observable;
    private long previousTime = 0L;

    public FxClock() {
        observable = new ObservableFactory<Time>().create();
    }

    @Override
    public void handle(long time) {
        observable.notifyObservers(new Time(millisToSeconds(time), millisToSeconds(getDeltaTime(time))));
    }

    @Override
    public void listen(Observer<Time> listener) {
        observable.addObserver(listener);
    }

    @Override
    public void unlisten(Observer<Time> listener) {
        observable.removeObserver(listener);
    }

    private long getDeltaTime(long currentTime) {
        long elapsed = currentTime - previousTime;
        previousTime = currentTime;
        return elapsed;
    }

    private long millisToSeconds(long millis) {
        return millis / 1000L;
    }
}

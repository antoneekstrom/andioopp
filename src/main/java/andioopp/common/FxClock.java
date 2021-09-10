package andioopp.common;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

public class FxClock extends AnimationTimer implements Clock {

    private List<ClockListener> listeners;

    public FxClock() {
        listeners = new ArrayList<>();
    }

    @Override
    public void handle(long l) {
        listeners.forEach(ClockListener::onClockUpdate);
    }

    @Override
    public void observe(ClockListener listener) {
        listeners.add(listener);
    }
}

package andioopp.common;


public interface Clock {
    void start();
    void observe(ClockListener action);
    void stop();
}

package andioopp.common.time;


import andioopp.common.observer.Observer;

public interface Clock {
    void start();
    void listen(Observer<Time> listener);
    void unlisten(Observer<Time> listener);
    void stop();
}

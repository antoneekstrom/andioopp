package andioopp.common.time;


import andioopp.common.observer.Observer;

public interface Clock {
    void start();
    void stop();
    void listen(Observer<Time> listener);
    void unlisten(Observer<Time> listener);
    void unlistenAll();
}

package andioopp.service;

import andioopp.common.time.Time;

public interface Service<T> {
    void onSetup(T context);
    void onDestroy(T context);
    void update(T context, Time time);
}

package andioopp.model;

import andioopp.common.time.Time;

@FunctionalInterface
public interface Updateable {

    void update(Time time);

}

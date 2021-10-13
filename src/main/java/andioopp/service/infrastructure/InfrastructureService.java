package andioopp.service.infrastructure;

import andioopp.common.graphics.Window;
import andioopp.common.time.Time;
import andioopp.service.Service;

public abstract class InfrastructureService implements Service<Window<?>> {

    @Override
    public void onSetup(Window<?> w) {
    }

    @Override
    public void onDestroy(Window<?> w) {
    }

    @Override
    public void update(Window<?> context, Time time) {
    }
}

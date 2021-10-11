package andioopp.service.infrastructure;

import andioopp.common.graphics.Window;

public abstract class InfrastructureService {
    public void onSetup(Window<?> w) {}
    public void onDestroy(Window<?> w) {}
}

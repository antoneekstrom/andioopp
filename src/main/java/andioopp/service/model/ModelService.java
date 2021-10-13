package andioopp.service.model;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.service.Service;

/**
 * A service that operates on the {@link Model}.
 */
public abstract class ModelService implements Service<Model> {

    @Override
    public void onSetup(Model model) {}

    @Override
    public void onDestroy(Model model) {}

    @Override
    public void update(Model model, Time time) {}
}

package andioopp.service.domain;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.service.Service;

public abstract class DomainService implements Service<Model> {

    @Override
    public void onSetup(Model model) {}

    @Override
    public void onDestroy(Model model) {}

    @Override
    public void update(Model model, Time time) {}
}

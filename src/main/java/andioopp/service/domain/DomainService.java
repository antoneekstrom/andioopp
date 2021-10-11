package andioopp.service.domain;

import andioopp.common.time.Time;
import andioopp.model.Model;

public abstract class DomainService {

    public void onSetup(Model model) {}

    public void onDestroy(Model model) {}

    public void update(Model model, Time time) {}

}

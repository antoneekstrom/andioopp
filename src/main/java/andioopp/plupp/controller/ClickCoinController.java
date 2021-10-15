package andioopp.plupp.controller;

import andioopp.plupp.model.Model;
import andioopp.plupp.service.ServiceProvider;
import andioopp.plupp.view.views.DroppedCoinsView;

public class ClickCoinController implements Controller<Model, DroppedCoinsView> {
    @Override
    public void init(Model model, DroppedCoinsView view, ServiceProvider serviceProvider) {
    }

    @Override
    public void deinit() {
    }
}

package andioopp.controller;

import andioopp.controller.service.ServiceProvider;
import andioopp.model.Model;
import andioopp.view.views.world.DroppedCoinsView;

public class ClickCoinController implements Controller<Model, DroppedCoinsView> {
    @Override
    public void init(Model model, DroppedCoinsView view, ServiceProvider serviceProvider) {
    }

    @Override
    public void deinit() {
    }
}

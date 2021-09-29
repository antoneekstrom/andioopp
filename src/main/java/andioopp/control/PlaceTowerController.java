package andioopp.control;

import andioopp.model.Model;
import andioopp.service.infrastructure.input.MouseInputService;

public class PlaceTowerController {

    private final MouseInputService mouseInputService;
    private final Model model;

    public PlaceTowerController(MouseInputService mouseInputService, Model model) {
        this.mouseInputService = mouseInputService;
        this.model = model;
    }
}

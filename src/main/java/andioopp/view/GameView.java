package andioopp.view;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.transform.Rectangle;
import andioopp.common.transform.TransformFactory;
import andioopp.control.TowerCardDragEvent;
import andioopp.model.Model;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.view.gui.CardsView;
import andioopp.view.gui.MoneyView;

public class GameView<S extends Sprite<?>> implements View<S> {

    private final ComposedView<S> view;

    private final LanesView<S> lanesView;
    private final CardsView<S> cardsView;

    public GameView(Rectangle viewportRect, TransformFactory transformFactory, DragAndDropService<TowerCardDragEvent> dragAndDropService) {
        lanesView = new LanesView<>(viewportRect);
        cardsView = new CardsView<>(viewportRect);

        TowersView<S> towersView = new TowersView<>(viewportRect, transformFactory);
        EnemiesView<S> enemiesView = new EnemiesView<>(viewportRect, transformFactory);
        ProjectilesView<S> projectilesView = new ProjectilesView<>(viewportRect, transformFactory);
        DroppedCoinsView<S> droppedCoinsView = new DroppedCoinsView<>(viewportRect, transformFactory);
        MoneyView<S> moneyView = new MoneyView<>(transformFactory);
        TowerDragMouseView<S> towerDragMouseView = new TowerDragMouseView<>(dragAndDropService);

        view = new ComposedView<>(lanesView, cardsView, towersView, enemiesView, projectilesView, droppedCoinsView, moneyView, towerDragMouseView);
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        view.render(renderer, model);
    }

    public ComposedView<S> getView() {
        return view;
    }

    public LanesView<S> getLanesView() {
        return lanesView;
    }

    public CardsView<S> getCardsView() {
        return cardsView;
    }
}

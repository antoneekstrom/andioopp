package andioopp.view;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.Rectangle;
import andioopp.common.transform.TransformFactory;
import andioopp.model.Model;
import andioopp.view.gui.CardsView;
import andioopp.view.gui.CoinView;

public class GameView<S extends Sprite<?>> implements View<S> {

    private final ComposedView<S> view;

    private final LanesView<S> lanesView;
    private final CardsView<S> cardsView;

    public GameView(Rectangle viewportRect, TransformFactory transformFactory) {
        lanesView = new LanesView<>(viewportRect);
        cardsView = new CardsView<>(viewportRect);

        TowersView<S> towersView = new TowersView<>(viewportRect, transformFactory);
        EnemiesView<S> enemiesView = new EnemiesView<>(transformFactory, viewportRect);
        ProjectilesView<S> projectilesView = new ProjectilesView<>(viewportRect, transformFactory);
        CoinView<S> coinView = new CoinView<>(transformFactory);

        view = new ComposedView<>(lanesView, cardsView, towersView, enemiesView, projectilesView, coinView);
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

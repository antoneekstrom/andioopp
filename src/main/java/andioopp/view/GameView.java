package andioopp.view;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.Window;
import andioopp.common.transform.*;
import andioopp.model.Model;
import andioopp.view.gui.CardsView;
import andioopp.view.gui.CoinView;

public class GameView<S extends Sprite<?>> implements View<S> {

    private final ComposedView<S> view;

    private final LanesView<S> lanesView;
    private final CardsView<S> cardsView;

    public GameView(Window<?> window, TransformFactory transformFactory) {
        MutableRectangle viewportRect = init(window);

        lanesView = new LanesView<>(viewportRect);
        cardsView = new CardsView<>(viewportRect);

        TowersView<S> towersView = new TowersView<>(viewportRect, transformFactory);
        EnemiesView<S> enemiesView = new EnemiesView<>(transformFactory, viewportRect);
        ProjectilesView<S> projectilesView = new ProjectilesView<>(viewportRect, transformFactory);
        CoinView<S> coinView = new CoinView<>(transformFactory);

        view = new ComposedView<>(lanesView, cardsView, towersView, enemiesView, projectilesView, coinView);
    }

    private MutableRectangle init(Window<?> window) {
        MutableRectangle rectangle = new MutableRectangle(computeViewportRect(window.getSize()));
        // window.getResizeObservable().addObserver(rectangle::setSize);
        return rectangle;
    }

    private Rectangle computeViewportRect(Dimension windowSize) {
        Vector3f worldSizeFactor = new Vector3f(0.7f, 0.7f);
        Dimension worldSize = new Dimension(windowSize.toVector().scale(worldSizeFactor));
        Vector3f worldPos = new Vector3f(windowSize.getWidth() - (worldSize.getWidth() * 1.01f), windowSize.getHeight() - (worldSize.getHeight() * 1.10f));
        return new ImmutableRectangle(worldPos, worldSize);
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

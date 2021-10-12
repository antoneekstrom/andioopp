package andioopp.view;

import andioopp.common.transform.*;
import andioopp.model.Model;
import andioopp.model.tower.Towers;
import andioopp.model.world.World;
import andioopp.common.gfx.Color;
import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.view.gui.CoinView;

import andioopp.view.gui.CardsView;
import andioopp.view.gui.TowerCard;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class View<S extends Sprite<?>> {
    private final Renderer<S> renderer;

    private final TowersView<S> towersView;
    private final CoinView<S> coinView;
    private final ProjectilesView<S> projectilesView;
    private final EnemiesView<S> enemiesView;
    private final CardsView<S> cardsView;
    private final CellsView<S> cellsView;

    public View(Renderer<S> renderer, TransformFactory transformFactory, Rectangle viewport) {
        this.renderer = renderer;
        this.cellsView = new CellsView<>(renderer, viewport);
        this.towersView = new TowersView<>(renderer, cellsView, transformFactory);
        this.coinView = new CoinView<>(renderer, transformFactory);
        this.projectilesView = new ProjectilesView<>(renderer, cellsView, viewport, transformFactory);
        this.enemiesView = new EnemiesView<>(renderer, cellsView, transformFactory, viewport);
        this.cardsView = new CardsView<>(renderer, getCards(), getCardsViewport(viewport));
    }

    private Rectangle getCardsViewport(Rectangle viewport) {
        return new Rectangle(viewport.getPosition().onlyX(), new Dimension(Vector3f.zero()));
    }

    private List<TowerCard<S>> getCards() {
        return Arrays.asList(new TowerCard<>(Towers::mario), new TowerCard<>(Towers::toad));
    }

    /**
     * Renders the model.
     *
     * @param model the model to render
     */
    public void render(Model model) {
        World world = model.getWorld();
        clearScreen();

        cellsView.render(world);
        towersView.render(world);
        enemiesView.render(world);
        projectilesView.render(world);
        coinView.render(world);
        cardsView.render();
    }

    public TowersView<S> getTowersView() {
        return towersView;
    }

    public ProjectilesView<S> getProjectilesView() {
        return projectilesView;
    }

    public EnemiesView<S> getEnemiesView() {
        return enemiesView;
    }

    public CoinView<S> getCoinView() {
        return coinView;
    }

    public CardsView<S> getCardsView() {
        return cardsView;
    }

    public CellsView<S> getCellsView() {
        return cellsView;
    }

    private void clearScreen() {
        getRenderer().clear(Color.WHITE);
    }

    private Renderer<S> getRenderer() {
        return renderer;
    }
}

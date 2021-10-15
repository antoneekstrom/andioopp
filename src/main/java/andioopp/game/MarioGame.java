package andioopp.plupp.game;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.model.Model;
import andioopp.model.money.Money;
import andioopp.model.money.Wallet;
import andioopp.model.player.Player;
import andioopp.model.player.TowerCard;
import andioopp.model.tower.Towers;
import andioopp.model.world.LaneBuilder;
import andioopp.model.world.World;
import andioopp.model.world.WorldBuilder;
import andioopp.plupp.controller.Controller;
import andioopp.plupp.service.ServiceProvider;
import andioopp.plupp.view.View;

import java.util.List;

public class MarioGame extends Game<Model> {

    public MarioGame() {
        super(new ServiceProvider(new ArrayListFactory()));
    }

    @Override
    protected List<Controller<Model, ?>> initControllers() {
        return getServices().getListFactory().create();
    }

    @Override
    protected List<View<Model>> initViews() {
        return getServices().getListFactory().create();
    }

    @Override
    protected Model initModel() {
        ListFactory listFactory = getServices().getListFactory();
        List<TowerCard<?>> cards = getCards(listFactory);
        Player player = getPlayer(cards);
        WorldBuilder worldBuilder = getWorldBuilder(listFactory);
        World build = worldBuilder.build();
        return new Model(build, player);
    }

    private List<TowerCard<?>> getCards(ListFactory listFactory) {
        return listFactory.create(
                new TowerCard<>(new Money(40), Towers::toad),
                new TowerCard<>(new Money(60), Towers::mario),
                new TowerCard<>(new Money(-1), Towers::rosalina),
                new TowerCard<>(new Money(-1), Towers::bobomb),
                new TowerCard<>(new Money(-1), Towers::yoshi),
                new TowerCard<>(new Money(-1), Towers::luigi)
        );
    }

    private Player getPlayer(List<TowerCard<?>> cards) {
        return new Player(cards, new Wallet(new Money(100)));
    }

    private WorldBuilder getWorldBuilder(ListFactory listFactory) {
        return new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5);
    }
}

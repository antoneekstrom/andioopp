package andioopp.game;

import andioopp.common.graphics.Window;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.controller.Controller;
import andioopp.controller.service.MouseService;
import andioopp.controller.service.ServiceProvider;
import andioopp.model.Model;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.money.Wallet;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.player.TowerCard;
import andioopp.model.domain.tower.Towers;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.system.System;
import andioopp.view.View;

import javax.naming.ldap.Control;
import java.util.List;

public class MarioGame extends Game<Model> {

    public MarioGame(Window<?> window) {
        super(new ServiceProvider(new ArrayListFactory(), new MouseService(window)), window);
    }

    @Override
    protected List<System<Model>> initSystems() {
        return getServices().getListFactory().create();
    }

    @Override
    protected List<Controller<Model>> initControllers() {
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

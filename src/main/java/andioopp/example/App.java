package andioopp.example;

import andioopp.common.ClockListener;
import andioopp.gfx.*;
import andioopp.model.*;
import andioopp.model.enemies.*;
import andioopp.common.Clock;
import andioopp.common.FxClock;

import java.util.ArrayList;

public class App implements GfxProgram {
    @Override
    public <S extends Sprite<?>, R extends Renderer<S>> void run(Window<R> window) {
        window.setMaximized(true);
        Renderer<S> r = window.getRenderer();
        SpriteFactory<S> spriteFactory = r.getSpriteFactory();

        ArrayList<Lane> lanes = new ArrayList<>();
        Lane lane = new Lane();
        lanes.add(lane);
        Enemy<S> goomba = new Goomba<>(spriteFactory);
        lane.addEnemy(goomba);
        Model model = new Model(new World(lanes), new WaveQueue(), new Player());

        Clock clock = new FxClock();
        clock.observe(model::update);
        clock.observe(() -> {
           r.drawSprite(goomba.getSprite(), goomba.getTransform());
        });
        clock.start();
    }
}

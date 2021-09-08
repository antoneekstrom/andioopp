package andioopp.example;

import andioopp.common.ConcreteTransform;
import andioopp.gfx.*;

public class App implements GfxProgram {
    @Override
    public <S extends Sprite<?>, R extends Renderer<S>> void run(Window<R> window) {
        window.setMaximized(true);
        Renderer<S> r = window.getRenderer();
        SpriteFactory<S> spriteFactory = r.getSpriteFactory();
        S mario = spriteFactory.create("mario_run.png");
        r.drawSprite(mario, new ConcreteTransform());
    }
}

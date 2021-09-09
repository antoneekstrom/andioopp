package andioopp.example;

import andioopp.common.Entity;
import andioopp.gfx.*;

public class App implements GfxProgram {
    @Override
    public <S extends Sprite<?>, R extends Renderer<S>> void run(Window<R> window) {
        window.setMaximized(true);
        Renderer<S> r = window.getRenderer();
        SpriteFactory<S> spriteFactory = r.getSpriteFactory();
        S marioSprite = spriteFactory.create("mario_run.png");
        Entity<S> e = new Entity<>(marioSprite);
        r.drawSprite(e.getSprite(), e.getTransform());
    }
}

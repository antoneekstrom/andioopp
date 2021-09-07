package andioopp.example;

import andioopp.gfx.*;
import javafx.geometry.Point2D;

public class App implements GfxProgram {
    @Override
    public <S extends Sprite<?>, R extends Renderer<S>> void run(Window<R> window) {
        window.setMaximized(true);
        Renderer<S> r = window.getRenderer();
        SpriteFactory<S> spriteFactory = r.getSpriteFactory();
        S mario = spriteFactory.create("mario_run.png", new Point2D(0 ,0));
        r.drawSprite(mario);
    }
}

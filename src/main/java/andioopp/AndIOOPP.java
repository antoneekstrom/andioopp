package andioopp;

import andioopp.gfx.Renderer;
import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;
import andioopp.gfx.Window;
import andioopp.gfx.javafx.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AndIOOPP extends Application {
	public static void main(String[] args) {
		AndIOOPP.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Window<FxRenderer> window = new FxWindow(stage, null);
		SpriteFactory<? extends Sprite<Image>> f = new FxSpriteFactory();
		Sprite<Image> s = f.create("", null);

		window.getRenderer().drawSprite(s);
	}

	<S extends Sprite<?>> void run(SpriteFactory<S> f) {

	}
}
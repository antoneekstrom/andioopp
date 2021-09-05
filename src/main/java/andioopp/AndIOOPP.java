package andioopp;

import andioopp.gfx.*;
import andioopp.gfx.Window;
import andioopp.gfx.javafx.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

public class AndIOOPP extends Application implements GfxProgram {
	public static void main(String[] args) {
		AndIOOPP.launch(args);
	}

	@Override
	public void start(Stage stage) {
		WindowBuilder<FxWindow> builder = new FxWindowBuilder(stage);
		builder.setTitle("and I OOPP");
		builder.setSize(new Dimension(256, 256));

		run(builder.build());
	}

	@Override
	public <S extends Sprite<?>, R extends Renderer<S>> void run(Window<R> window) {
		Renderer<S> renderer = window.getRenderer();
		SpriteFactory<S> f = renderer.getSpriteFactory();
	}
}
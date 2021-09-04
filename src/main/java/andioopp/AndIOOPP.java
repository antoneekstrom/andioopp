package andioopp;

import andioopp.gfx.WindowBuilder;
import andioopp.gfx.javafx.FxWindowBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

public class AndIOOPP extends Application {
	public static void main(String[] args) {
		AndIOOPP.launch(args);
	}

	@Override
	public void start(Stage stage) {
		WindowBuilder builder = new FxWindowBuilder(stage);
		builder.setTitle("and I OOPP");
		builder.setSize(new Dimension(128, 128));
		builder.build();
	}
}
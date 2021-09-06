package andioopp.gfx.javafx;

import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import java.awt.*;

import static org.junit.Assert.assertNotNull;

public class FxWindowBuilderTest extends ApplicationTest {

    private final Dimension WINDOW_SIZE = new Dimension(128, 128);

    private FxWindow window;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        this.stage = stage;

        FxWindowBuilder builder = new FxWindowBuilder(stage);
        String WINDOW_TITLE = "Test";

        builder.setSize(WINDOW_SIZE);
        builder.setTitle(WINDOW_TITLE);

        window = builder.build();
    }

    @Test
    public void buildShouldReturnWindow() {
        assertNotNull(window);
    }

    @Test
    public void buildShouldAppendGroupToStage() {
        Assertions.assertThat(stage.getScene().getRoot()).hasAnyChild();
    }

    @Test
    public void buildShouldAppendGroupWithCanvasToStage() {
        Parent root = window.getStage().getScene().getRoot();
        Assertions.assertThat(window.getCanvas().getParent()).isEqualTo(root);
    }
}
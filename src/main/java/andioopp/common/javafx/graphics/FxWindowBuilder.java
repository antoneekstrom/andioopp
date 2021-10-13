package andioopp.common.javafx.graphics;

import andioopp.common.graphics.WindowBuilder;
import andioopp.common.math.Dimension;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Builds {@link FxWindow} objects.
 * Configures the JavaFX stage and adds a {@link Canvas} to the root which is used to render the graphics.
 */
public class FxWindowBuilder implements WindowBuilder<FxWindow> {

    private final Stage stage;

    private String title = "Window";
    private Dimension size = new Dimension(256, 256);
    private boolean isResizable = true;
    private Image icon;

    /**
     * Instantiates a windowbuilder.
     *
     * @param stage JavaFX {@link Stage} to use
     */
    public FxWindowBuilder(Stage stage) {
        this.stage = stage;
    }

    @Override
    public FxWindow build() {
        Canvas canvas = createCanvas();
        configureStageWithScene(createScene(canvas));
        return createWindowWithCanvas(canvas);
    }

    private void configureStageWithScene(Scene scene) {
        setSceneOnStage(scene);
        setTitleOnStage();
        setResizableOnStage();
        setIconOnStage();
        showStage();
    }

    private FxWindow createWindowWithCanvas(Canvas canvas) {
        return new FxWindow(getStage(), canvas);
    }

    private void setIconOnStage() {
        if (Objects.nonNull(getIcon())) {
            getStage().getIcons().add(getIcon());
        }
    }

    private void setResizableOnStage() {
        getStage().setResizable(isResizable());
    }

    private void showStage() {
        getStage().show();
    }

    private void setSceneOnStage(Scene scene) {
        getStage().setScene(scene);
    }

    private void setTitleOnStage() {
        getStage().setTitle(getTitle());
    }

    private Canvas createCanvas() {
        return new Canvas(getSize().getWidth(), getSize().getHeight());
    }

    private Scene createScene(Canvas canvas) {
        return new Scene(new Group(canvas));
    }

    private Stage getStage() {
        return stage;
    }

    public Dimension getSize() {
        return size;
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public Image getIcon() {
        return icon;
    }

    @Override
    public void setIcon(String path) {
        icon = new Image(path);
    }

    public boolean isResizable() {
        return isResizable;
    }

    @Override
    public void setResizable(boolean isResizable) {
        this.isResizable = isResizable;
    }
}

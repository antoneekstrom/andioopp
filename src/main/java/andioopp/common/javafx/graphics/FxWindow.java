package andioopp.common.javafx.graphics;

import andioopp.common.graphics.Window;
import andioopp.common.javafx.input.FxMouseInputAdapter;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.observer.*;
import andioopp.common.storage.ArrayListFactory;
import andioopp.controller.input.MouseInput;
import javafx.beans.value.ChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * {@link Window} implementation for JavaFX. Encapsulates a {@link Stage} and {@link Canvas} instance.
 */
public class FxWindow implements Window<FxRenderer> {

    private final Stage stage;

    private final MouseInput mouseInput;
    private final Observable<Dimension> resizeObservable;
    private final FxRenderer renderer;

    public FxWindow(Stage stage, Canvas canvas) {
        this.stage = stage;

        renderer = new FxRenderer(canvas.getGraphicsContext2D());
        resizeObservable = createResizeObservable(stage, canvas);
        mouseInput = FxMouseInputAdapter.fromStage(stage, ArrayList::new);
    }

    private Observable<Dimension> createResizeObservable(Stage stage, Canvas canvas) {
        final Observable<Dimension> resizeObservable;
        resizeObservable = new ObservableCollection<>(new ArrayListFactory().create());
        ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> {
            resizeObservable.notifyObservers(getSize());
            canvas.resize(getSize().getWidth(), getSize().getHeight());
        };
        stage.widthProperty().addListener(resizeListener);
        stage.heightProperty().addListener(resizeListener);
        return resizeObservable;
    }

    @Override
    public MouseInput getMouseInput() {
        return mouseInput;
    }

    @Override
    public Observable<Dimension> getResizeObservable() {
        return resizeObservable;
    }

    @Override
    public void setMaximized(boolean isMaximized) {
        stage.setMaximized(isMaximized);
    }

    @Override
    public FxRenderer getRenderer() {
        return renderer;
    }

    @Override
    public Dimension getSize() {
        return new Dimension((float) stage.getWidth(), (float) stage.getHeight());
    }
}

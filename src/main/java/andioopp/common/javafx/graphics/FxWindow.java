package andioopp.common.javafx.graphics;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.observer.CollectionObservable;
import andioopp.common.storage.ArrayListFactory;
import andioopp.controller.input.MouseInputEvent;
import andioopp.common.graphics.Window;
import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;
import javafx.beans.value.ChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * {@link Window} implementation for JavaFX. Encapsulates a {@link Stage} and {@link Canvas} instance.
 */
public class FxWindow implements Window<FxRenderer> {

    private final Stage stage;

    private final Observable<MouseInputEvent, Observer<MouseInputEvent>> mouseObservable;
    private final Observable<Dimension, Observer<Dimension>> resizeObservable;
    private final FxRenderer renderer;

    public FxWindow(Stage stage, Canvas canvas) {
        this.stage = stage;

        renderer = new FxRenderer(canvas.getGraphicsContext2D());
        mouseObservable = createMouseInputObservable(stage);
        resizeObservable = createResizeObservable(stage, canvas);
    }

    private Observable<MouseInputEvent, Observer<MouseInputEvent>> createMouseInputObservable(Stage stage) {
        final Observable<MouseInputEvent, Observer<MouseInputEvent>> mouseObservable;
        mouseObservable = new CollectionObservable<>(new ArrayListFactory().create());
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_MOVED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseInputEvent(position, MouseInputEvent.MouseEventType.MOVE));
        });
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_DRAGGED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseInputEvent(position, MouseInputEvent.MouseEventType.DRAG));
        });
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseInputEvent(position, MouseInputEvent.MouseEventType.PRESS));
        });
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_RELEASED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseInputEvent(position, MouseInputEvent.MouseEventType.RELEASE));
        });
        return mouseObservable;
    }

    private Observable<Dimension, Observer<Dimension>> createResizeObservable(Stage stage, Canvas canvas) {
        final Observable<Dimension, Observer<Dimension>> resizeObservable;
        resizeObservable = new CollectionObservable<>(new ArrayListFactory().create());
        ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> {
            resizeObservable.notifyObservers(getSize());
            canvas.resize(getSize().getWidth(), getSize().getHeight());
        };
        stage.widthProperty().addListener(resizeListener);
        stage.heightProperty().addListener(resizeListener);
        return resizeObservable;
    }

    @Override
    public Observable<MouseInputEvent, Observer<MouseInputEvent>> getMouseObservable() {
        return mouseObservable;
    }

    @Override
    public Observable<Dimension, Observer<Dimension>> getResizeObservable() {
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

package andioopp.common.gfx.javafx;

import andioopp.common.gfx.Window;
import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableWithList;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.input.MouseEvent;
import andioopp.common.transform.Dimension;
import andioopp.common.transform.Vector3f;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * {@link Window} implementation for JavaFX. Encapsulates a {@link Stage} and {@link Canvas} instance.
 */
public class FxWindow implements Window<FxRenderer> {

    private final Stage stage;
    private final Canvas canvas;

    private final Observable<MouseEvent, Observer<MouseEvent>> mouseObservable;

    public FxWindow(Stage stage, Canvas canvas) {
        this.stage = stage;
        this.canvas = canvas;

        mouseObservable = new ObservableWithList<>(new ArrayListFactory().create());
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_MOVED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseEvent(position, MouseEvent.MouseEventType.MOVE));
        });
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseEvent(position, MouseEvent.MouseEventType.PRESS));
        });
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_RELEASED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseEvent(position, MouseEvent.MouseEventType.RELEASE));
        });
    }

    @Override
    public Observable<MouseEvent, Observer<MouseEvent>> getMouseObservable() {
        return mouseObservable;
    }

    @Override
    public Observable<Dimension, Observer<Dimension>> getResizeObservable() {
        return null;
    }

    @Override
    public void setMaximized(boolean isMaximized) {
        getStage().setMaximized(isMaximized);
    }

    @Override
    public FxRenderer getRenderer() {
        return new FxRenderer(getCanvas().getGraphicsContext2D());
    }

    @Override
    public int getWidth() {
        return (int) stage.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) stage.getHeight();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Stage getStage() {
        return stage;
    }
}

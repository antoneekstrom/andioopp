package andioopp.service.infrastructure.input;

import andioopp.common.input.MouseData;
import andioopp.common.transform.Rectangle;

public class DragAndDropObserver implements DraggableObserver, DroppableObserver {

    private final Rectangle rectangle;

    public DragAndDropObserver(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void onEvent(MouseData event) {
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
}

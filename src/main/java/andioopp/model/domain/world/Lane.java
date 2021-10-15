package andioopp.model.domain.world;

import java.util.List;

/**
 * A lane which contains {@link Cell} objects.
 */
public class Lane {
    private final List<Cell> cells;

    public Lane(List<Cell> cells) {
        this.cells = cells;
    }

    public int getNumberOfCells() {
        return getCells().size();
    }

    public Cell getCell(int col) {
        return getCells().get(col);
    }

    public List<Cell> getCells() {
        return cells;
    }
}

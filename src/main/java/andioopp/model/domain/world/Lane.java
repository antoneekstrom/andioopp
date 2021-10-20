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

    /**
     * Returns the number of Cells.
     */
    public int getNumberOfCells() {
        return getCells().size();
    }

    /**
     * Returns a List of the Cells in a column.
     */
    public Cell getCell(int col) {
        return getCells().get(col);
    }

    /**
     * Returns a List of the Cells in the Lane.
     */
    public List<Cell> getCells() {
        return cells;
    }
}

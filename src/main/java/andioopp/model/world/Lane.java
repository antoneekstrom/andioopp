package andioopp.model.world;

import java.util.List;

/**
 * A lane which contains {@link Cell} objects.
 */
public class Lane {
    private final List<Cell> cells;

    Lane(List<Cell> cells) {
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

import java.util.List;

public interface Grid {
    short getGenerationsCount(Cell cell, short generationTurns);

    boolean isInBoundaries(Short[][] grid, short row, short col);

    Short[][] getGridAfterGeneration(Short[][] matrix);

    boolean isEligibleToChangeCellColor(Cell currentCell);

    List<Short> getSurroundingCells(Short[][] grid, Cell cell);
}

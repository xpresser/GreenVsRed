import java.util.List;

public interface Grid {
    short getGenerationsCount(Cell cell, short generationTurns);

    boolean isInBoundaries(Boolean[][] grid, short row, short col);

    Boolean[][] getGridAfterGeneration(Boolean[][] matrix);

    boolean isEligibleToChangeCellColor(Cell currentCell);

    List<Short> getSurroundingCells(Boolean[][] grid, Cell cell);
}

import java.util.List;

public interface Grid {
    long getGenerationsCount(Cell cell, int generationTurns);

    boolean isInBoundaries(long[][] grid, int row, int col);

    long[][] getGridAfterGeneration(long[][] matrix);

    boolean isEligibleToChangeCellColor(Cell currentCell);

    List<Integer> getSurroundingCells(long[][] grid, Cell cell);
}

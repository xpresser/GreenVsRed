import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridImpl implements Grid {
    private long[][] matrix;

    private final List<Integer> greenNumbers = new ArrayList<>(List.of(0, 1, 4, 5, 7, 8));

    private final List<Integer> redNumbers = new ArrayList<>(List.of(0, 1, 2, 4, 5, 7, 8));

    public GridImpl(int rows, int columns) {
        setMatrixSize(rows, columns);
    }

    @Override
    public long getGenerationsCount(Cell cell, int generationTurns) {
        long generationCount = 0;

        if (isInBoundaries(matrix, cell.getRow(), cell.getColumn())) {
            for (int i = 0; i < generationTurns; i++) {
                this.matrix = getGridAfterGeneration(matrix);

                if (matrix[cell.getRow()][cell.getColumn()] == 1) {
                    generationCount += 1;
                }
            }

            return generationCount;
        }

        System.out.printf("%s%n", GridErrorMessage.INVALID_COORDINATES);
        return generationCount;
    }

    @Override
    public boolean isInBoundaries(long[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length;
    }

    @Override
    public long[][] getGridAfterGeneration(long[][] matrix) {
        long[][] resultGrid = Arrays.stream(matrix).map(long[]::clone).toArray(long[][]::new);
        Cell currentCell;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                currentCell = new Cell(matrix, row, col);
                switch (currentCell.getColor()) {
                    case RED:
                        if (isEligibleToChangeCellColor(currentCell)) {
                            resultGrid[row][col] = 1;
                        }
                        break;
                    case GREEN:
                        if (isEligibleToChangeCellColor(currentCell)) {
                            resultGrid[row][col] = 0;
                        }
                        break;
                }
            }
        }

        return resultGrid;
    }

    @Override
    public boolean isEligibleToChangeCellColor(Cell currentCell) {
        List<Integer> surroundingCells = getSurroundingCells(this.matrix, currentCell);
        int onesCount = (int) surroundingCells.stream().filter(e -> e == 1).count();

        switch (currentCell.getColor()) {
            case GREEN:
                return greenNumbers.contains(onesCount);
            case RED:
                return !redNumbers.contains(onesCount);
        }

        return false;
    }

    @Override
    public List<Integer> getSurroundingCells(long[][] grid, Cell cell) {
        List<Integer> surroundings = new ArrayList<>();

        for (int rowIndexLimit = -1; rowIndexLimit <= 1; rowIndexLimit++) {
            for (int columnIndexLimit = -1; columnIndexLimit <= 1; columnIndexLimit++) {
                int row = cell.getRow() + rowIndexLimit;
                int column = cell.getColumn() + columnIndexLimit;

                if (isInBoundaries(grid, row, column)) {
                    if ((rowIndexLimit != 0) || (columnIndexLimit != 0)) {
                        surroundings.add((int) grid[row][column]);
                    }
                }
            }
        }

        return surroundings;
    }

    private void checkGridLimitations(long width, long height) {
        if (width > height) {
            System.out.printf("%s%n", GridErrorMessage.WIDTH_GREATER_THAN_HEIGHT);
        } else if (width >= 1000) {
            System.out.printf("Width %s%n", GridErrorMessage.EQUAL_OR_GREATER_THAN_1000);
        } else if (height >= 1000) {
            System.out.printf("Height %s%n", GridErrorMessage.EQUAL_OR_GREATER_THAN_1000);
        }
    }

    public long[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrixSize(int width, int height) {
        checkGridLimitations(width, height);

        this.matrix = new long[width][height];
    }
}

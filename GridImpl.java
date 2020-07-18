import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GridImpl implements Grid {
    private Short[][] matrix;

    private final List<Short> greenNumbersOfChange = Stream.of(0, 1, 4, 5, 7, 8)
            .map(Integer::shortValue).collect(Collectors.toList());

    private final List<Short> redNumbersOfChange = Stream.of(0, 1, 2, 4, 5, 7, 8)
            .map(Integer::shortValue).collect(Collectors.toList());

    public GridImpl(short rows, short columns) {
        setMatrixSize(rows, columns);
    }

    @Override
    public short getGenerationsCount(Cell cell, short generationTurns) {
        short generationCount = 0;

        if (isInBoundaries(matrix, cell.getRow(), cell.getColumn())) {
            for (short i = 0; i < generationTurns; i++) {
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
    public boolean isInBoundaries(Short[][] grid, short row, short col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length;
    }

    @Override
    public Short[][] getGridAfterGeneration(Short[][] matrix) {
        Short[][] resultGrid = Arrays.stream(matrix).map(Short[]::clone).toArray(Short[][]::new);
        Cell currentCell;

        for (short row = 0; row < matrix.length; row++) {
            for (short col = 0; col < matrix[row].length; col++) {
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
        List<Short> surroundingCells = getSurroundingCells(this.matrix, currentCell);
        short greenSurroundingCellsCount = (short) surroundingCells.stream().filter(e -> e == 1).count();

        switch (currentCell.getColor()) {
            case GREEN:
                return greenNumbersOfChange.contains(greenSurroundingCellsCount);
            case RED:
                return !redNumbersOfChange.contains(greenSurroundingCellsCount);
        }

        return false;
    }

    @Override
    public List<Short> getSurroundingCells(Short[][] grid, Cell cell) {
        List<Short> surroundings = new ArrayList<>();

        for (short rowIndexLimit = -1; rowIndexLimit <= 1; rowIndexLimit++) {
            for (short columnIndexLimit = -1; columnIndexLimit <= 1; columnIndexLimit++) {
                short row = (short) (cell.getRow() + rowIndexLimit);
                short column = (short) (cell.getColumn() + columnIndexLimit);

                if (isInBoundaries(grid, row, column)) {
                    if ((rowIndexLimit != 0) || (columnIndexLimit != 0)) {
                        surroundings.add(grid[row][column]);
                    }
                }
            }
        }

        return surroundings;
    }

    private void checkGridLimitations(short width, short height) {
        if (width > height) {
            System.out.printf("%s%n", GridErrorMessage.WIDTH_GREATER_THAN_HEIGHT);
        } else if (width >= 1000) {
            System.out.printf("Width %s%n", GridErrorMessage.EQUAL_OR_GREATER_THAN_1000);
        } else if (height >= 1000) {
            System.out.printf("Height %s%n", GridErrorMessage.EQUAL_OR_GREATER_THAN_1000);
        } else if (width <= 0) {
            System.out.printf("Width %s%n", GridErrorMessage.ZERO_OR_A_NEGATIVE_NUMBER);
        } else if (height <= 0) {
            System.out.printf("Height %s%n", GridErrorMessage.ZERO_OR_A_NEGATIVE_NUMBER);
        }
    }

    public Short[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrixSize(short width, short height) {
        checkGridLimitations(width, height);

        this.matrix = new Short[width][height];
    }
}

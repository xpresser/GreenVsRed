import java.util.Arrays;
import java.util.Scanner;

public class GreenVsRed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Short[] gridSizeInput = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Short::parseShort)
                .toArray(Short[]::new);

        GridImpl grid = new GridImpl(gridSizeInput[0], gridSizeInput[1]);

        for (int i = 0; i < grid.getMatrix().length; i++) {
            Boolean[] generationZeroState = Arrays.stream(scanner.nextLine().split(""))
                    .map(element -> Byte.parseByte(element) == 1)
                    .toArray(Boolean[]::new);

            grid.getMatrix()[i] = generationZeroState;
        }

        String[] lastArguments = scanner.nextLine().split(",\\s+");

        short targetCellRow = Short.parseShort(lastArguments[0]);
        short targetCellColumn = Short.parseShort(lastArguments[1]);
        Cell targetCell = new Cell(grid.getMatrix(), targetCellRow, targetCellColumn);

        short generationTimes = Short.parseShort(lastArguments[2]);
        short greenColorGenerationCount = grid.getGenerationsCount(targetCell, generationTimes);

        System.out.printf("# expected result: %d", greenColorGenerationCount);
    }
}

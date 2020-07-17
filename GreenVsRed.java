import java.util.Arrays;
import java.util.Scanner;

public class GreenVsRed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] gridSizeInput = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        GridImpl grid = new GridImpl(gridSizeInput[0], gridSizeInput[1]);

        for (int i = 0; i < grid.getMatrix().length; i++) {
            long[] generationZeroState = Arrays.stream(scanner.nextLine().split(""))
                    .mapToLong(Long::parseLong)
                    .toArray();

            grid.getMatrix()[i] = generationZeroState;
        }

        String[] lastArguments = scanner.nextLine().split(",\\s+");

        int targetCellRow = Integer.parseInt(lastArguments[0]);
        int targetCellColumn = Integer.parseInt(lastArguments[1]);
        Cell targetCell = new Cell(grid.getMatrix(), targetCellRow, targetCellColumn);

        int generationTimes = Integer.parseInt(lastArguments[2]);
        long greenColorGenerationCount = grid.getGenerationsCount(targetCell, generationTimes);

        System.out.printf("# expected result: %d", greenColorGenerationCount);
    }
}

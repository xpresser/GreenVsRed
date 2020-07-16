public class Cell {
    private int row;

    private int column;

    private CellColor color;

    public Cell() {

    }

    public Cell(long[][] grid, int row, int column) {
        setRow(row);
        setColumn(column);
        setColor(grid, row, column);
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CellColor getColor() {
        return this.color;
    }

    public void setColor(long[][] grid, int width, int height) {
        if (grid[width][height] == 1) {
            this.color = CellColor.GREEN;
        } else if (grid[width][height] == 0){
            this.color = CellColor.RED;
        }
    }
}

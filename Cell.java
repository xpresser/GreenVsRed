public class Cell {
    private short row;

    private short column;

    private CellColor color;

    public Cell() {

    }

    public Cell(Boolean[][] grid, short row, short column) {
        setRow(row);
        setColumn(column);
        setColor(grid, row, column);
    }

    public short getRow() {
        return this.row;
    }

    public void setRow(short row) {
        this.row = row;
    }

    public short getColumn() {
        return this.column;
    }

    public void setColumn(short column) {
        this.column = column;
    }

    public CellColor getColor() {
        return this.color;
    }

    public void setColor(Boolean[][] grid, short width, short height) {
        this.color = grid[width][height] ? CellColor.GREEN : CellColor.RED;
    }
}

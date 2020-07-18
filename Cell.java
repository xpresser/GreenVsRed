public class Cell {
    private short row;

    private short column;

    private CellColor color;

    public Cell() {

    }

    public Cell(Short[][] grid, short row, short column) {
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

    public void setColor(Short[][] grid, short width, short height) {
        if (grid[width][height] == 1) {
            this.color = CellColor.GREEN;
        } else if (grid[width][height] == 0){
            this.color = CellColor.RED;
        }
    }
}

package escape.coordinate;

import escape.required.Coordinate;

public class CoordinateImpl implements Coordinate{
    private CoordinateType type;
    private int row;
    private int col;

    /**
     * Default contstructor
     * @param type
     * @param row
     * @param col
     */
    public CoordinateImpl(CoordinateType type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return col;
    }
}

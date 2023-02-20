package escape.coordinate;

import java.util.ArrayList;
import java.util.Objects;

import escape.required.Coordinate;
import escape.required.EscapePiece.MovementPattern;

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

	/**
	 * Get the relevant neighbors of a coordinate
	 * @param pattern MovementPattern describing the directions to check for neighbors
	 * @return an ArrayList of relevant neighbors
	 */
    public ArrayList<CoordinateImpl> getNeighbors(MovementPattern pattern) {
        ArrayList<CoordinateImpl> neighbors = new ArrayList<CoordinateImpl>();
        switch (pattern) {
            case OMNI:
                neighbors.addAll(getOrthoNeighbors());
                neighbors.addAll(getDiagNeighbors()); 
                break;
            case ORTHOGONAL:
                neighbors.addAll(getOrthoNeighbors());
                break;
            case DIAGONAL:
                neighbors.addAll(getDiagNeighbors());
                break;
            default:
        }
        return neighbors;
    }

	/**
	 * Get the orthogonal neighbors of a coordinate
	 * @return an ArrayList of relevant neighbors
	 */
    private ArrayList<CoordinateImpl> getOrthoNeighbors() {
        ArrayList<CoordinateImpl> neighbors = new ArrayList<CoordinateImpl>();
        if (type == CoordinateType.SQUARE) {
            neighbors.add(new CoordinateImpl(type, row + 1, col));
            neighbors.add(new CoordinateImpl(type, row, col - 1));
            neighbors.add(new CoordinateImpl(type, row, col + 1));
            neighbors.add(new CoordinateImpl(type, row - 1, col));
        }
        return neighbors;
    }
    
    /**
	 * Get the diagonal neighbors of a coordinate
	 * @return an ArrayList of relevant neighbors
	 */
    private ArrayList<CoordinateImpl> getDiagNeighbors() {
        ArrayList<CoordinateImpl> neighbors = new ArrayList<CoordinateImpl>();
        if (type == CoordinateType.SQUARE) {
            neighbors.add(new CoordinateImpl(type, row + 1, col + 1));
            neighbors.add(new CoordinateImpl(type, row + 1, col - 1));
            neighbors.add(new CoordinateImpl(type, row - 1, col - 1));
            neighbors.add(new CoordinateImpl(type, row - 1, col + 1));
        }
        return neighbors;
    }
    
    /**
	 * Get the coordiantes in the linear path of a coordinate
	 * @param distance length of the linear path
	 * @param deltaRow direction of the path in terms of rows
	 * @param deltaCol direction of the path in terms of columns
	 * @return an ArrayList of coordinates in the linear path
	 */
    public ArrayList<CoordinateImpl> getLinearNeighbors(int distance, int deltaRow, int deltaCol) {
        ArrayList<CoordinateImpl> neighbors = new ArrayList<CoordinateImpl>();
        for (int i = 1; i <= distance; i++) {
            neighbors.add(new CoordinateImpl(type, row + i * deltaRow, col + i * deltaCol));
        }
        return neighbors;
    }
    
    @Override
    public boolean equals(Object obj) {
        try {
            CoordinateImpl coord = (CoordinateImpl) obj;
            return coord.getRow() == row && coord.getColumn() == col;
        } catch (Exception e)  {
            return false;
        }
    }
    
    @Override public int hashCode() {
        return Objects.hash(getRow(), getColumn());
    }
    
    @Override
	public String toString()
	{
		return "Coordinate [type=" + type + ", row=" + row + ", col=" + col + "]";
	}
}

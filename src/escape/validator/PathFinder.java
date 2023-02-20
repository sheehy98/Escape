package escape.validator;

import java.util.ArrayList;
import java.util.Map;

import escape.builder.PieceTypeDescriptor;
import escape.coordinate.CoordinateImpl;
import escape.piece.EscapePieceImpl;
import escape.required.Coordinate;
import escape.required.EscapePiece.*;

/**
 * Class to check if valid path exists between two coordinates given 
 * a board and piece type descriptors
 */
public class PathFinder {
    private Map<Coordinate, EscapePieceImpl> board;
    private int rows;
    private int cols;
    private MovementPattern pattern;
    private int distance;
    private boolean canFly;
    // The deltaRows and deltaCols describing every possible linear direction for SQUARE
    private int[][] linearDirections = {
        {1, 1},  {1, 0},  {1, -1},
        {0, 0},           {0, 1},
        {-1, 1}, {-1, 0}, {-1, -1},
    };
    
    /*
     * Constructor, some properties set by accessing methods of 'descriptor'
     */
    public PathFinder(Map<Coordinate, EscapePieceImpl> board, int rows, int cols, PieceTypeDescriptor descriptor) {
        this.board = board;
        this.rows = rows;
        this.cols = cols;
        this.pattern = descriptor.getMovementPattern();
        this.distance = descriptor.getAttribute(PieceAttributeID.DISTANCE).getValue();
        this.canFly = (descriptor.getAttribute(PieceAttributeID.FLY) != null) ? true : false;
    }

    /**
	 * Determines if the target coordinate is within the bounds of the board
	 * @param to coordinate to check
	 * @return true if the coordinate is within the board, false otherwise
	 */
    private boolean insideBoard(Coordinate to) {
        if (rows == 0 || cols == 0) { return true; }
        return (
            to.getRow() > 0 && 
            to.getRow() <= rows && 
            to.getColumn() > 0 && 
            to.getColumn() <= cols
        );
    }

    /**
	 * Determines if there is a valid path between two coordiantes
	 * @param from starting coordinate of the potential path
	 * @param to ending coordinate of the potential path
	 * @return true if there is a valid path, false otherwise
	 */
    public boolean hasPath(CoordinateImpl from, Coordinate to) {
        if (pattern == MovementPattern.LINEAR) {
            return doLinearSearch(from, to);
        }
        return doBFS(from, to);
    }

    /**
	 * Performs a breadth first search to determine if 'to' can be
     * reached from 'from' within a distance specified by the piece
	 * @param from starting coordinate of the potential path
	 * @param to ending coordinate of the potential path
	 * @return true if 'to' can be found through the breadth first 
     *         search, false otherwise
	 */
    private boolean doBFS(CoordinateImpl from, Coordinate to) {
        ArrayList<CoordinateImpl> visited = new ArrayList<CoordinateImpl>();
        ArrayList<CoordinateImpl> queue = new ArrayList<CoordinateImpl>();
        queue.add(from);

        // Search with increasing depth until the maximum distance is reached
        for (int i = 1; i <= distance; i++) {
            ArrayList<CoordinateImpl> nextQueue = new ArrayList<CoordinateImpl>();

            // Iterate over each coordinate at the current depth
            for (CoordinateImpl queued: queue) {
                // Check each coordiante's neighbors
                for(CoordinateImpl neighbor: queued.getNeighbors(pattern)) {
                    // Check if neighbor is a valid member of the next depth level
                    if (
                        insideBoard(neighbor) &&
                        !queue.contains(neighbor) &&
                        !visited.contains(neighbor) &&
                        (!board.containsKey(neighbor) || canFly)
                    ) {
                        // If the neighbor is the desired location and the space is clear, search is done
                        if (to.equals(neighbor) && !board.containsKey(neighbor)) {
                            return true;
                        }
                        // Otherwise, add this coordinate to the next depth level
                        nextQueue.add(neighbor);
                    }
                }
            }

            // Mark all checked coordinates as visited, move on to the next depth level
            visited.addAll(queue);
            queue = nextQueue;
        }

        return false;
    }

    /**
	 * Performs a search through every linear path with a maximum
     * length specified by the piece to determine if 'to' can be
     * reached from 'from'
	 * @param from starting coordinate of the potential path
	 * @param to ending coordinate of the potential path
	 * @return true if 'to' can be found through the linear search, false otherwise
	 */
    private boolean doLinearSearch(CoordinateImpl from, Coordinate to) {
        ArrayList<CoordinateImpl> neighbors = new ArrayList<CoordinateImpl>();

        // Search with increasing depth until the maximum distance is reached
        for (int i = 1; i <= distance; i++) {
            // Check each direction
            for (int[] direction: linearDirections) {
                // Get all coordinates on the given path
                neighbors = from.getLinearNeighbors(i, direction[0], direction[1]);
                // Check if the path ends at the desired location and it is not occupied
                if (to.equals(neighbors.get(i - 1)) && !board.containsKey(neighbors.get(i - 1))) {
                    // Check if the path is blocked
                    for (CoordinateImpl neighbor: neighbors) {
                        if (board.containsKey(neighbor) && !canFly) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }
}

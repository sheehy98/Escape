package escape.validator;

import java.util.ArrayList;
import java.util.Map;

import escape.builder.PieceTypeDescriptor;
import escape.coordinate.CoordinateImpl;
import escape.piece.EscapePieceImpl;
import escape.required.Coordinate;
import escape.required.LocationType;
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
    private boolean canUnblock;
    private boolean canJump;
    private boolean canFight;
    
    /*
     * Constructor, some properties set by accessing methods of 'descriptor'
     */
    public PathFinder(Map<Coordinate, EscapePieceImpl> board, int rows, int cols, PieceTypeDescriptor descriptor, boolean canFight) {
        this.board = board;
        this.rows = rows;
        this.cols = cols;
        this.pattern = descriptor.getMovementPattern();
        this.distance = descriptor.getAttribute(PieceAttributeID.DISTANCE).getValue();
        this.canFly = (descriptor.getAttribute(PieceAttributeID.FLY) != null) ? true : false;
        this.canUnblock = (descriptor.getAttribute(PieceAttributeID.UNBLOCK) != null) ? true : false;
        this.canJump = (descriptor.getAttribute(PieceAttributeID.JUMP) != null) ? true : false;
        this.canFight = canFight;
    }

    /**
	 * Determines if the target coordinate is within the bounds of the board
	 * @param to coordinate to check
	 * @return true if the coordinate is within the board, false otherwise
	 */
    private boolean insideBoard(Coordinate to) {
        return (
            (rows == 0 || (
                to.getRow() > 0 && 
                to.getRow() <= rows
            )) &&
            (cols == 0 || (
                to.getColumn() > 0 && 
                to.getColumn() <= cols
            ))
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
	 * Determines if there is any valid path starting from a given coordinate
	 * @param from starting coordinate of the potential path
	 * @return true if there is any valid path, false otherwise
	 */
    public boolean hasAnyPath(CoordinateImpl from) {
        return hasPath(from, null);
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
                        this.canTraverse(to, neighbor)
                    ) {
                        // If the neighbor is the desired location and the space is clear, search is done
                        if (
                            this.isTarget(to, neighbor) &&
                            this.isValidEnd(neighbor)
                        ) {
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
            for (int[] direction: from.getLinearDirections()) {
                // Get all coordinates on the given path
                neighbors = from.getLinearNeighbors(i, direction[0], direction[1]);

                // Check if the path ends at the desired location and if that location is landable
                if (
                    insideBoard(neighbors.get(i - 1)) &&
                    this.isTarget(to, neighbors.get(i - 1)) && 
                    this.isValidEnd(neighbors.get(i - 1))
                ) {
                    boolean isJumping = false;
                    // Check if the path to the desired location is blocked
                    for (CoordinateImpl neighbor: neighbors) {

                        if (!canTraverse(to, neighbor)) {
                            // Jump if necessary and possible
                            if (
                                canJump && 
                                !isJumping &&
                                this.isJumpable(neighbor)
                            ) {
                                isJumping = true;
                            }
                            else {
                                isJumping = false;
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    /**
	 * Determines if a coordinate is possible to move through
	 * @param to the target coordinate of the move
	 * @param location the current coordinate along the path being checked
	 * @return true if the coordinate is possible to land on; otherwise false
	 */
    private boolean canTraverse (Coordinate to, CoordinateImpl location) {
        return(
            canFly ||
            this.isClear(location) || 
            this.isUnblocking(location) ||
            (
                this.isTarget(to, location) &&
                (
                    isExit(location) || 
                    canFight
                )
            )
        );
    }

    /**
	 * Determines if two coordinates are equal
	 * @param to the target coordinate of the move
	 * @param location the current coordinate along the path 
	 * @return true if the two coordinates are equal; otherwise false
	 */
    private boolean isTarget (Coordinate to, Coordinate location) {
        return (to == null || to.equals(location));
    }

    /**
	 * Determines if a coordinate is a valid ending location for a move
	 * @param location the coordinate to check
	 * @return true if the coordinate is a valid ending location for a move; otherwise false
	 */
    private boolean isValidEnd(CoordinateImpl location) {
        return ( 
            this.isClear(location) ||
            this.isExit(location) ||
            this.isFighting(location)
        );
    }

    /**
	 * Determines if a coordinate is a clear on the board
	 * @param location the coordinate to check
	 * @return true if the coordinate is a clear on the board; otherwise false
	 */
    private boolean isClear(CoordinateImpl location) {
        return !board.containsKey(location);
    }

    /**
	 * Determines if a move to a specified coordinate would result in combat
	 * @param location the coordinate to check
	 * @return true if the move would result in combat; otherwise false
	 */
    private boolean isFighting(CoordinateImpl location) {
        return (board.get(location).getType() == null && canFight);
    }

    /**
	 * Determines if traversing through a given coordinate would lead to unblocking
	 * @param location the coordinate to check
	 * @return true if the traversal would lead to unblocking; otherwise false
	 */
    private boolean isUnblocking(CoordinateImpl location) {
        return (board.get(location).getType() == LocationType.BLOCK && canUnblock);
    }

    /**
	 * Determines if a coordinate is an exit on the board
	 * @param location the coordinate to check
	 * @return true if the coordinate is an exit on the board; otherwise false
	 */
    private boolean isExit(Coordinate location) {
        return (board.get(location).getType() == LocationType.EXIT);
    }

    /**
	 * Determines if a coordinate is theoretically jumpable, which translates
     * to being either a player or an exit
	 * @param location the coordinate to check
	 * @return true if the ccoordinate is theoretically jumpable; otherwise false
	 */
    private boolean isJumpable(Coordinate location) {
        return (
            board.get(location).getType() == null ||
            board.get(location).getType() == LocationType.EXIT
        );
    }
}
package escape.validator;

import java.util.Map;

import escape.builder.PieceTypeDescriptor;
import escape.coordinate.CoordinateImpl;
import escape.piece.EscapePieceImpl;
import escape.required.Coordinate;
import escape.required.LocationType;

/**
 * Class to handle validation of moves
 */
public class MoveValidator {
    private Map<Coordinate, EscapePieceImpl> board;
    private int rows;
    private int cols;
    private PieceTypeDescriptor[] pieceTypes;
    private boolean canFight;

    /*
     * Default constructor
     */
    public MoveValidator(Map<Coordinate, EscapePieceImpl> board, int rows, int cols, PieceTypeDescriptor[] pieceTypes, boolean canFight) {
        this.board = board;
        this.rows = rows;
        this.cols = cols;
        this.pieceTypes = pieceTypes;
        this.canFight = canFight;
    }

    /**
	 * Determines if the next player is stuck
	 * @param player player to check
	 * @param type board type
	 * @param board board to check on
	 * @return true if given player is stuck; otherwise false
	 */
    public boolean nextCantMove (String player, Coordinate.CoordinateType type, Map<Coordinate, EscapePieceImpl> board) {
        for (Coordinate location: board.keySet()) {
            CoordinateImpl locationImpl = new CoordinateImpl(type, location.getRow(), location.getColumn());

            // If there is any piece on the board that belongs to the player and has
            // any valid path, they are not stuck
            if (
                board.get(location).getType() == null &&
                player.equals(board.get(location).getPlayer())
            ) {
                PathFinder pathFinder = new PathFinder(board, rows, cols, findDescriptor(board.get(location)), canFight);
                if (pathFinder.hasAnyPath(locationImpl)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
	 * Determines if a move described by the given coordinates is valid for the
     * given player
	 * @param player the player whose turn it currently is
	 * @param from coordinate the requested move starts from
	 * @param to coordinate the requested move ends
	 * @return true if the move is valid, false otherwise
	 */
    public boolean validate(String player, CoordinateImpl from, Coordinate to) {
        return (
            hasPiece(player, from) &&
            hasPath(from, to)
        );
    }

    /**
	 * Determines if a given coordinate is the exit
	 * @param to coordinate to check
	 * @return true if the given coordinate is the exitl otherwise false
	 */
    public boolean isExiting(Coordinate to) {
        return (
            board.containsKey(to) &&
            board.get(to).getType() == LocationType.EXIT
        );
    }
    
    /**
	 * Determines if the specified player has a piece at the specified location
	 * @param player the player whose turn it currently is
	 * @param from coordinate the requested move starts from
	 * @return true if there is a piece owner by the player at the starting 
     *         location, false otherwise
	 */
    private boolean hasPiece(String player, Coordinate from) {
        return (
            board.containsKey(from) &&
            board.get(from).getType() == null &&
            board.get(from).getPlayer().equals(player)
        );
    }
    
    /**
	 * Finds the PieceTypeDescriptor associated with a given piece
	 * @param piece the piece that needs a description
	 * @return the PiceTypeDescriptor object described in the input
     *         file that corresponds with the piece
	 */
    private PieceTypeDescriptor findDescriptor(EscapePieceImpl piece) {
        for (PieceTypeDescriptor descriptor: pieceTypes) {
            if (descriptor.getPieceName().equals(piece.getName())) {
                return descriptor;
            }
        }
        return null;
    }

    /**
	 * Determines if there is a valid path between two specified coordinates
	 * @param from coordinate the requested move starts from
	 * @param to coordinate the requested move ends
	 * @return true if the move is valid, false otherwise
	 */
    private boolean hasPath(CoordinateImpl from, Coordinate to) {
        EscapePieceImpl piece = board.get(from);
        PathFinder pathFinder = new PathFinder(board, rows, cols, findDescriptor(piece), canFight);
        return pathFinder.hasPath(from, to);
    }
}

package escape.validator;

import java.util.Map;

import escape.builder.PieceTypeDescriptor;
import escape.coordinate.CoordinateImpl;
import escape.piece.EscapePieceImpl;
import escape.required.Coordinate;

/**
 * Class to handle validation of moves
 */
public class MoveValidator {
    private Map<Coordinate, EscapePieceImpl> board;
    private int rows;
    private int cols;
    private PieceTypeDescriptor[] pieceTypes;

    /*
     * Default constructor
     */
    public MoveValidator(Map<Coordinate, EscapePieceImpl> board, int rows, int cols, PieceTypeDescriptor[] pieceTypes) {
        this.board = board;
        this.rows = rows;
        this.cols = cols;
        this.pieceTypes = pieceTypes;
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
	 * Determines if the specified player has a piece at the specified location
	 * @param player the player whose turn it currently is
	 * @param from coordinate the requested move starts from
	 * @return true if there is a piece owner by the player at the starting 
     *         location, false otherwise
	 */
    private boolean hasPiece(String player, Coordinate from) {
        return (
            board.containsKey(from) &&
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
        PathFinder pathFinder = new PathFinder(board, rows, cols, findDescriptor(piece));
        return pathFinder.hasPath(from, to);
    }
}

package escape;

import escape.required.*;
import escape.status.GameStatusImpl;
import escape.validator.MoveValidator;

import java.util.HashMap;
import java.util.Map;

import escape.builder.*;
import escape.coordinate.*;
import escape.piece.*;

public class EscapeGameManagerImpl implements EscapeGameManager<Coordinate>{
    private Coordinate.CoordinateType type;
    private int rows;
    private int cols;
    private String[] players;
    private int turn = 0;
    private PieceTypeDescriptor[] pieceTypes;
    // Board is represented as a HashMap
    private Map<Coordinate, EscapePieceImpl> board = new HashMap<Coordinate, EscapePieceImpl>();

    public void setCoordinateType(Coordinate.CoordinateType type) {
        this.type = type;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int cols) {
        this.cols = cols;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public void setLocations(LocationInitializer[] locations) {
        if (locations != null) {
            // Create a coordiante and piece for each location specified in the input,
            // add them to the board as they're created
            for (LocationInitializer location: locations) {
                CoordinateImpl nextCoord = new CoordinateImpl(type, location.x, location.y);
                EscapePieceImpl nextPiece = new EscapePieceImpl(location.pieceName, location.player);
    
                board.put(nextCoord, nextPiece);
            }
        }
    }

    public void setPieceTypes(PieceTypeDescriptor[] pieceTypes) {
        this.pieceTypes = pieceTypes;
    }

    @Override
    public CoordinateImpl makeCoordinate(int x, int y) {
        return new CoordinateImpl(type, x, y);
    }

    @Override
    public EscapePieceImpl getPieceAt(Coordinate coordinate)
	{
		if (board.containsKey(coordinate)) {
            return board.get(coordinate);
        }
        return null;
	}

    @Override
    public GameStatus move(Coordinate from, Coordinate to)
	{
        // Initialize a validator and convert 'from' to a CoordinateImpl for neighbor extraction
        MoveValidator validator = new MoveValidator(board, rows, cols, pieceTypes);
        CoordinateImpl fromImpl = new CoordinateImpl(type, from.getRow(), from.getColumn());

        // Initialize a GameStatus object, populate based on move
        GameStatusImpl status = new GameStatusImpl();
        status.setFinalLocation(from);
        status.setValid(validator.validate(players[turn], fromImpl, to));
        // If move is valid, update the final location, pass the turn, update the board
        if (status.isValidMove()) {
            status.setFinalLocation(to);
            turn = (turn + 1) % players.length;
            updateBoard(from, to);
        }

        return status;
	}
    
    /**
	 * Updates the board after a successful move
	 * @param from the starting coordinate of the (pre-validated) move
	 * @param to the ending coordinate of the move
	 */
    private void updateBoard(Coordinate from, Coordinate to) {
        EscapePieceImpl movedPiece = board.get(from);
        board.remove(from);
        board.put(to, movedPiece);
    }
}

package escape;

import escape.required.*;
import escape.required.EscapePiece.PieceAttributeID;
import escape.required.EscapePiece.PieceName;
import escape.required.GameStatus.MoveResult;
import escape.required.Rule.RuleID;
import escape.rule.RuleChecker;
import escape.rule.RuleImpl;
import escape.status.GameStatusImpl;
import escape.validator.MoveValidator;

import java.util.ArrayList;
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
    private int[] scores;
    private int turn = 0;
    private PieceTypeDescriptor[] pieceTypes;
    private Rule[] rules;
    // Board is represented as a HashMap
    private Map<Coordinate, EscapePieceImpl> board = new HashMap<Coordinate, EscapePieceImpl>();
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();

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
        this.scores = new int[players.length];
    }

    public void setPieceTypes(PieceTypeDescriptor[] pieceTypes) {
        this.pieceTypes = pieceTypes;
    }

    public void setLocations(LocationInitializer[] locations) {
        if (locations != null) {
            // Create a coordiante and piece for each location specified in the input,
            // add them to the board as they're created
            for (LocationInitializer location: locations) {
                CoordinateImpl nextCoord = new CoordinateImpl(type, location.x, location.y);
                if (location.locationType == LocationType.BLOCK || location.locationType == LocationType.EXIT) {
                    board.put(nextCoord, new EscapePieceImpl(location.locationType));
                }
                else if (location.locationType == null) {
                    EscapePieceImpl newPiece = new EscapePieceImpl(location.pieceName, location.player);
                    newPiece.setValue(this.findValue(newPiece.getName()));
                    board.put(nextCoord, newPiece);
                }
            }
        }
    }

    /**
	 * Gets the value of a certain piece type
	 * @param pieceName the name of the piece to check
	 * @return the value of the piece as defined by its descriptor
	 */
    private int findValue(PieceName pieceName) {
        int value = 1;

        for (PieceTypeDescriptor descriptor: pieceTypes) {
            if (
                descriptor.getPieceName().equals(pieceName) && 
                descriptor.getAttribute(PieceAttributeID.VALUE) != null
            ) {
                value = descriptor.getAttribute(PieceAttributeID.VALUE).getValue();
            }
        }

        return value;
    }

    public void setRules(RuleDescriptor[] rules) {
        if (rules == null) {
            this.rules = new Rule[0];
            return;
        }

        this.rules = new Rule[rules.length];
        for (int i = 0; i < rules.length; i++) {
            this.rules[i] = new RuleImpl(rules[i].ruleId, rules[i].ruleValue);
        }
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
    public GameObserver addObserver(GameObserver observer) {
        observers.add(observer);
        return observer;
    }

    @Override
    public GameObserver removeObserver(GameObserver observer) {
        observers.remove(observer);
        return observer;
    }


    /**
	 * Notifies observers of an event
	 * @param message the message to send to the observers
	 */
    public void notifyObservers(String message) {
        for(GameObserver observer: observers) {
            observer.notify(message);
        }
    }

    /**
	 * Notifies observers of an event
	 * @param message the message to send to the observers
	 * @param cause the cause of the error
	 */
    public void notifyObservers(String message, Throwable cause) {
        for(GameObserver observer: observers) {
            observer.notify(message, cause);
        }
    }

    @Override
    public GameStatus move(Coordinate from, Coordinate to)
	{
        // Initialize a validator and convert 'from' to a CoordinateImpl for neighbor extraction
        MoveValidator validator = new MoveValidator(board, rows, cols, pieceTypes, this.canFight());
        CoordinateImpl fromImpl = new CoordinateImpl(type, from.getRow(), from.getColumn());

        // Initialize a GameStatus object, populate based on move validity
        GameStatusImpl status = new GameStatusImpl();
        status.setValid(validator.validate(players[turn % players.length], fromImpl, to));

        // If move is valid, update the board, pass the turn, validate based on the rules,
        // and set the move result accordingly; otherwise lose
        if (status.isValidMove()) {
            status = updateBoard(turn, from, to, validator.isExiting(to), status);
            turn = turn + 1;
            
            RuleChecker ruleChecker = new RuleChecker(board, rules, players, pieceTypes, scores);
            status.setMoveResult(ruleChecker.checkResult(turn));
            
            if (
                status.getMoveResult() != MoveResult.LOSE &&
                validator.nextCantMove(players[turn % players.length], type, board)
            ) {
                status.setMoveResult(MoveResult.WIN);
            }
        }
        else {
            // If invalid, set the move result to lose and notify the observers
            status.setMoveResult(MoveResult.LOSE);
            
            this.notifyObservers(players[turn % players.length] + " lost due to", new EscapeException("Invalid move"));
            status.setIsMoreInformation(true);
            return status;
        }

        return this.notifyResult(status);
	}
    
    /**
	 * Componses the notification after a game is terminated
	 * @param status the GameStatusImpl object to be updated
	 * @return updated status
	 */
    private GameStatusImpl notifyResult(GameStatusImpl status) {
        switch (status.getMoveResult()) {
            case LOSE:
                this.notifyObservers(players[(turn + 1) % players.length] + " lost on turn " + (turn / 2));
                status.setIsMoreInformation(true);
                break;
            case DRAW:
                this.notifyObservers(players[(turn + 1) % players.length] + " and " + players[turn % players.length] + " tied on turn " + (turn / 2));
                status.setIsMoreInformation(true);
                break;
            case WIN:
                this.notifyObservers(players[(turn + 1) % players.length] + " won on turn " + (turn / 2));
                status.setIsMoreInformation(true);
                break;
            default:
        }
        return status;
    }

    /**
	 * Determines if POINT_CONFLICT is in play
	 * @return true if POINT_CONFLICT is in play; otherwise false
	 */
    private boolean canFight() {
        for (Rule rule: rules) {
            if (rule.getId() == RuleID.POINT_CONFLICT) {
                return true;
            }
        }
        return false;
    }
    
    /**
	 * Updates the board after a successful move
	 * @param from the starting coordinate of the (pre-validated) move
	 * @param to the ending coordinate of the move
	 * @param isExiting whether or not the moving piece is exiting the board
	 * @param status the GameStatusImpl to be updated
	 * @return updated status
	 */
    private GameStatusImpl updateBoard(int turn, Coordinate from, Coordinate to, boolean isExiting, GameStatusImpl status) {
        EscapePieceImpl movedPiece = board.get(from);
        board.remove(from);

        if (!isExiting) {
            if (canFight() && board.containsKey(to) && !board.get(to).getPlayer().equals(players[turn % players.length])) {
                status = resolveConflict(movedPiece, turn, to, status);
            }
            else {
                board.put(to, movedPiece);
            }
        }
        else {
            scores[turn % players.length] = scores[turn % players.length] + movedPiece.getValue();
        }
        return status;
    }

    /**
	 * Resolves a conflict when necessary
	 * @param movedPiece the piece initiating conflict
	 * @param turn the current turn number
	 * @param to the location of the conflict
	 * @param status the GameStatusImpl to be updated
	 * @return updated status
	 */
    private GameStatusImpl resolveConflict(EscapePieceImpl movedPiece, int turn, Coordinate to, GameStatusImpl status) {
        EscapePieceImpl enemyPiece = board.get(to);
        board.remove(to);

        if (enemyPiece.getValue() > movedPiece.getValue()) {
            enemyPiece.setValue(enemyPiece.getValue() - movedPiece.getValue());
            board.put(to, enemyPiece);

            this.notifyObservers(players[turn % players.length] + " defeated " + players[(turn + 1) % players.length] + " and has value " + enemyPiece.getValue());
            status.setIsMoreInformation(true);
        }
        else if (enemyPiece.getValue() < movedPiece.getValue()) {
            movedPiece.setValue(movedPiece.getValue() - enemyPiece.getValue());
            board.put(to, movedPiece);
            
            this.notifyObservers(players[(turn + 1) % players.length] + " defeated " + players[turn % players.length] + " and has value " + enemyPiece.getValue());
            status.setIsMoreInformation(true);
        }
        return status;
    }
}

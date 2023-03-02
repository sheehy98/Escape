package escape.rule;

import java.util.Map;

import escape.builder.PieceTypeDescriptor;
import escape.piece.EscapePieceImpl;
import escape.required.Coordinate;
import escape.required.Rule;
import escape.required.GameStatus.MoveResult;
import escape.required.Rule.RuleID;

/**
 * Class to validate a board according to the rules
 */
public class RuleChecker {
    private Map<Coordinate, EscapePieceImpl> board;
    Rule[] rules;
    String[] players;
    PieceTypeDescriptor[] pieceTypes;
    int[] scores;

    /**
     * Default contstructor
     * @param board board to check with
     * @param rules rules to validate with
     * @param players players in the game
     * @param pieceTypes types of pieces in play
     * @param scores current scores of the players
     */
    public RuleChecker(Map<Coordinate, EscapePieceImpl> board, Rule[] rules, String[] players, PieceTypeDescriptor[] pieceTypes, int[] scores) {
        this.board = board;
        this.rules = rules;
        this.players = players;
        this.pieceTypes = pieceTypes;
        this.scores = scores;
    }
    
    /**
     * Determine the result of a turn according to the rules
     * @param turn the current turn number
     * @return the MoveResult of the current state of the board after validation
     */
    public MoveResult checkResult(int turn) {
        int limit = this.hasScoreLimit();
        // Check if the player should win
        if (
            (limit == 0 && this.isFinished(turn)) ||
            (limit > 0 && this.atScoreLimit(turn, scores, limit))
        ) {
            return MoveResult.WIN;
        }
        // Check if the player should lose
        else if (limit == 0 && this.isFinished(turn + 1)) {
            return MoveResult.LOSE;
        }
        // Calculate the winner if the turn limit is reached
        if (this.atTurnLimit(turn)) {
            return this.decideWinner(turn, scores);
        }
        return MoveResult.NONE;
        
    }

    /**
     * Determine if a player has removed all of the pieces from the board
     * @param turn the current turn number
     * @return true if a player has removed all of the pieces from the board; otherwise false
     */
    private boolean isFinished(int turn) {
        for (Coordinate location: board.keySet()) {
            if (
                board.get(location).getType() == null &&
                players[(turn - 1) % players.length].equals(board.get(location).getPlayer())
            ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine if the SCORE rule is in play
     * @return true if the SCORE rule is in play; otherwise false
     */
    private int hasScoreLimit() {
        for (Rule rule: rules) {
            if (rule.getId() == RuleID.SCORE) {
                return rule.getIntValue();
            }
        }
        return 0;
    }

    /**
     * Determine if the current player is at the score limit
     * @param turn current turn, used to determine player
     * @param scores current scores
     * @param limit score limit
     * @return true if the current player is at the score limit; otherwise false
     */
    private boolean atScoreLimit(int turn, int[] scores, int limit) {
        return scores[(turn + 1) % players.length] >=  limit;
    }

    /**
     * Determine if the game has reached the turn limit
     * @param turn current turn
     * @return true if the game has reached the turn limit; otherwise false
     */
    private boolean atTurnLimit(int turn) {
        for (Rule rule: rules) {
            if (rule.getId() == RuleID.TURN_LIMIT) {
                return turn == 2 * rule.getIntValue();
            }
        }
        return false;
    }

    /**
     * Calculate the winner if the turn limit is reached
     * @param turn the current turn
     * @param scores the current scores
     * @return the MoveResult that follows from the difference in scores
     */
    private MoveResult decideWinner(int turn, int[] scores) {
        if (scores[turn % players.length] == scores[(turn + 1) % players.length]) {
            return MoveResult.DRAW;
        }
        if (scores[turn % players.length] < scores[(turn + 1) % players.length]) {
            return MoveResult.WIN;
        }
        return MoveResult.LOSE;
    }
}

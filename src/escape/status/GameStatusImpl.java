package escape.status;

import escape.required.*;

public class GameStatusImpl implements GameStatus {
    boolean valid;
    MoveResult moveResult = MoveResult.NONE;
    boolean isMoreInformation;

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValidMove() {
        return valid;
    }

    public void setIsMoreInformation(boolean isMoreInformation) {
		this.isMoreInformation = isMoreInformation;
    }

    public boolean isMoreInformation() {
		return isMoreInformation;
    }

    public void setMoveResult(MoveResult moveResult) {
		this.moveResult = moveResult;
    }

    public MoveResult getMoveResult() {
		return moveResult;
    }

    public Coordinate finalLocation() {
	    return null;
    }

    public CombatResult getCombatResult() {
	    return null;
    }
}

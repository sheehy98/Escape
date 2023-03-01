package escape.status;

import escape.required.*;

public class GameStatusImpl implements GameStatus {
    boolean valid;
    Coordinate finalLocation;

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValidMove() {
        return valid;
    }

    public boolean isMoreInformation() {
		throw new EscapeException("Not implemented");
    }

    public MoveResult getMoveResult() {
		return MoveResult.NONE;
    }

    public void setFinalLocation(Coordinate finalLocation) {
		this.finalLocation = finalLocation;
    }

    public Coordinate finalLocation() {
	    return finalLocation;
    }

    public CombatResult getCombatResult() {
	    return null;
    }
}

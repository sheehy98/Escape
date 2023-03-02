package escape.piece;

import escape.required.EscapePiece;
import escape.required.LocationType;

public class EscapePieceImpl implements EscapePiece {
    LocationType type = null;
    PieceName name;
    String owner;
    int value;

    /**
     * Default contstructor
     * @param name
     * @param owner
     */
    public EscapePieceImpl(PieceName name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    /**
     * Contstructor for block and exit
     * @param type
     */
    public EscapePieceImpl(LocationType type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public PieceName getName() {
        return name;
    }

    @Override
    public String getPlayer() {
        return owner;
    }
    
    public LocationType getType() {
        return type;
    }
}

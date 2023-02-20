package escape.piece;

import escape.required.EscapePiece;

public class EscapePieceImpl implements EscapePiece {
    PieceName name;
    String owner;

    /**
     * Default contstructor
     * @param name
     * @param owner
     */
    public EscapePieceImpl(PieceName name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    @Override
    public PieceName getName() {
        return name;
    }

    @Override
    public String getPlayer() {
        return owner;
    }
}

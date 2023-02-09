package escape;

import escape.required.*;
import escape.coordinate.*;

public class EscapeGameManagerImpl implements EscapeGameManager<Coordinate>{
    private Coordinate.CoordinateType type;

    @Override
    public CoordinateImpl makeCoordinate(int x, int y) {
        return new CoordinateImpl(type, x, y);
    }

    public void setCoordinateType(Coordinate.CoordinateType type) {
        this.type = type;
    }
}

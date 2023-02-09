package escape;

import escape.required.*;
import escape.builder.LocationInitializer;
import escape.coordinate.*;

public class EscapeGameManagerImpl implements EscapeGameManager<Coordinate>{
    private Coordinate.CoordinateType type;
    private int rows;
    private int cols;
    private String[] players;
    private LocationInitializer[] locations;

    @Override
    public CoordinateImpl makeCoordinate(int x, int y) {
        return new CoordinateImpl(type, x, y);
    }

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
        this.locations = locations;
    }
}

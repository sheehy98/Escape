package escape.builder;

import escape.*;
import escape.coordinate.CoordinateImpl;
import escape.required.Coordinate;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeGameBuilderTest {
    @Test
    void makeGameObject() {
        EscapeGameManager egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test1.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        assertNotNull(egm);
    }
    
    @Test
    void makeCoordinate() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test1.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord = egm.makeCoordinate(1, 2);

        assertEquals(1, coord.getRow());
        assertEquals(2, coord.getColumn());
    }
}

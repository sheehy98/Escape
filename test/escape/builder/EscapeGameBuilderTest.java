package escape.builder;

import escape.*;
import escape.coordinate.CoordinateImpl;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeGameBuilderTest {
    @Test
    void makeGameObject() {
        EscapeGameManager egm = null;
        try {
            egm = new EscapeGameBuilder("configurations/test1.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        assertNotNull(egm);
    }
    
    @Test
    void makeCoordinate() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("configurations/test1.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord = egm.makeCoordinate(1, 2);

        assertEquals(1, coord.getRow());
        assertEquals(2, coord.getColumn());
    }
    
    @Test
    void compareCoordinates() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("configurations/test1.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 2);

        assertTrue(coord1.equals(coord2));
    }
}

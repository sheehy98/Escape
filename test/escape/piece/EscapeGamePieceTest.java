package escape.piece;

import escape.*;
import escape.builder.EscapeGameBuilder;
import escape.coordinate.CoordinateImpl;
import escape.required.EscapePiece;
import escape.required.EscapePiece.PieceName;
import escape.utility.BaseTest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeGamePieceTest extends BaseTest{
    @Test
    void initializePiece() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test2.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        EscapePiece piece1 = egm.getPieceAt(coord1);

        assertNull(piece1);


        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        EscapePiece piece2 = egm.getPieceAt(coord2);

        assertNotNull(piece2);
        assertEquals(PieceName.SNAIL, piece2.getName());
        assertEquals("Chris", piece2.getPlayer());
    }
    
    @Test
    void initializePieces() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test3.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        EscapePiece piece1 = egm.getPieceAt(coord1);

        assertNotNull(piece1);
        assertEquals(PieceName.SNAIL, piece1.getName());
        assertEquals("Chris", piece1.getPlayer());

        
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        EscapePiece piece2 = egm.getPieceAt(coord2);

        assertNotNull(piece2);
        assertEquals(PieceName.SNAIL, piece2.getName());
        assertEquals("Pat", piece2.getPlayer());
    }
}

package escape.builder;

import escape.*;
import escape.coordinate.CoordinateImpl;
import escape.required.EscapePiece;
import escape.required.GameStatus;
import escape.required.EscapePiece.PieceName;

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
    
    @Test
    void compareCoordinates() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test1.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 2);

        assertTrue(coord1.equals(coord2));
    }
    
    @Test
    void initializePiece() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test2.ecg").makeGameManager();
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
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test3.ecg").makeGameManager();
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
    
    @Test
    void firstBadMove() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(0, 4);
        GameStatus move1 = egm.move(coord1, coord2);
        
        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void firstGoodMove() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));
    }
    
    @Test
    void firstTurn() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test5.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(1, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertTrue(coord4.equals(move2.finalLocation()));
    }
    
    @Test
    void wrongPiece() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void firstTwoTurns() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test5.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(1, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertTrue(coord4.equals(move2.finalLocation()));

        CoordinateImpl coord5 = egm.makeCoordinate(5, 5);
        GameStatus move3 = egm.move(coord2, coord5);

        assertTrue(move3.isValidMove());
        assertTrue(coord5.equals(move3.finalLocation()));

        CoordinateImpl coord6 = egm.makeCoordinate(2, 2);
        GameStatus move4 = egm.move(coord4, coord6);

        assertTrue(move4.isValidMove());
        assertTrue(coord6.equals(move4.finalLocation()));
    }
    
    @Test
    void tryAgain() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test5.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(0, 0);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));

        CoordinateImpl coord3 = egm.makeCoordinate(4, 5);
        GameStatus move2 = egm.move(coord1, coord3);

        assertTrue(move2.isValidMove());
        assertTrue(coord3.equals(move2.finalLocation()));
    }
    
    @Test
    void wrongType() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 7);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void longMove() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test6.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(6, 7);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));
    }
    
    @Test
    void noDistance() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 4);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void spaceOccupied() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test6.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void blockingPiece() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test7.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void aboveBlockingPiece() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test8.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));
    }
    
    @Test
    void blockingFlight() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test8.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void invalidOrtho() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test9.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(5, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void validOrtho() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test10.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(5, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));
    }
    
    @Test
    void invalidDiag() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test11.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void validDiag() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test12.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));
    }
    
    @Test
    void invalidLinear() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test13.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(5, 6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void blockedLinear() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test14.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));
    }
    
    @Test
    void validLinearFly() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test15.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));
    }
    
    @Test
    void validLinear() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test13.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));
    }
    
    @Test
    void validNegative() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test16.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(-4, -6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertTrue(coord2.equals(move1.finalLocation()));
    }
    
    @Test
    void twoTypes() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder("C:/Users/Mago Sheehy/Documents/git/WPI/CS4233/Escape/configurations/test17.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(5, 6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertTrue(coord1.equals(move1.finalLocation()));

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        GameStatus move2 = egm.move(coord1, coord3);

        assertTrue(move2.isValidMove());
        assertTrue(coord3.equals(move2.finalLocation()));

        CoordinateImpl coord4 = egm.makeCoordinate(3, 3);
        CoordinateImpl coord5 = egm.makeCoordinate(1, 1);
        GameStatus move3 = egm.move(coord4, coord5);

        assertFalse(move3.isValidMove());
        assertTrue(coord4.equals(move3.finalLocation()));
        
        GameStatus move4 = egm.move(coord4, coord1);

        assertTrue(move4.isValidMove());
        assertTrue(coord1.equals(move4.finalLocation()));
    }
}

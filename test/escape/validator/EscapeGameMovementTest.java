package escape.validator;

import escape.*;
import escape.builder.EscapeGameBuilder;
import escape.coordinate.CoordinateImpl;
import escape.required.GameStatus;
import escape.required.GameStatus.MoveResult;
import escape.utility.BaseTest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeGameMovementTest extends BaseTest{    
    @Test
    void firstBadMove() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(0, 4);
        GameStatus move1 = egm.move(coord1, coord2);
        
        assertFalse(move1.isValidMove());
    }
    
    @Test
    void firstGoodMove() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void firstTurn() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test5.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(1, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
    }
    
    @Test
    void wrongPiece() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void firstTwoTurns() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test5.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(1, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());

        CoordinateImpl coord5 = egm.makeCoordinate(5, 5);
        GameStatus move3 = egm.move(coord2, coord5);

        assertTrue(move3.isValidMove());

        CoordinateImpl coord6 = egm.makeCoordinate(2, 2);
        GameStatus move4 = egm.move(coord4, coord6);

        assertTrue(move4.isValidMove());
    }
    
    @Test
    void tryAgain() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test5.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(0, 0);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(4, 5);
        GameStatus move2 = egm.move(coord1, coord3);

        assertTrue(move2.isValidMove());
    }
    
    @Test
    void wrongType() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 7);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void longMove() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test6.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(6, 7);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void noDistance() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test4.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 4);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void spaceOccupied() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test6.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void blockingPiece() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test7.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void aboveBlockingPiece() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test8.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void blockingFlight() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test8.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void invalidOrtho() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test9.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(5, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void validOrtho() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test10.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(5, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void invalidDiag() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test11.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void validDiag() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test12.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void invalidLinear() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test13.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(5, 6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void blockedLinear() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test14.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void validLinearFly() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test15.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void validLinear() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test13.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void validNegative() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test16.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(-4, -6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void twoTypes() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test17.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(5, 6);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        GameStatus move2 = egm.move(coord1, coord3);

        assertTrue(move2.isValidMove());

        CoordinateImpl coord4 = egm.makeCoordinate(3, 3);
        CoordinateImpl coord5 = egm.makeCoordinate(1, 1);
        GameStatus move3 = egm.move(coord4, coord5);

        assertFalse(move3.isValidMove());
        
        GameStatus move4 = egm.move(coord4, coord1);

        assertTrue(move4.isValidMove());
    }
    
    @Test
    void mixedBounds() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test18.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(2, -2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(-1, 1);
        GameStatus move2 = egm.move(coord3, coord4);

        assertFalse(move2.isValidMove());
    }
    
    @Test
    void canExit() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test19.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 3);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(-1, 1);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());

        CoordinateImpl coord5 = egm.makeCoordinate(4, 3);
        GameStatus move3 = egm.move(coord2, coord5);
        assertFalse(move3.isValidMove());
    }
    
    @Test
    void canNotLose() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test14.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(5, 5);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);
    }
    
    @Test
    void canLose() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test14.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 4);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.LOSE);
    }
    
    @Test
    void unobtrusiveClear() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test22.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(2, 3);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 3);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void blockBlocks() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test23.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(2, 3);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 3);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
    }
    
    @Test
    void canUnblock() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test25.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 4);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 3);
        CoordinateImpl coord4 = egm.makeCoordinate(4, 3);
        GameStatus move2 = egm.move(coord3, coord4);

        assertFalse(move2.isValidMove());
    }
    
    @Test
    void flyOverBlock() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test23.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(2, 3);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 3);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
    }
    
    @Test
    void firstHexOmni() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test26.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(0, 0);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord4 = egm.makeCoordinate(4, 4);
        GameStatus move2 = egm.move(coord3, coord4);

        assertFalse(move2.isValidMove());
    }
    
    @Test
    void firstHexLinear() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test27.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(0, 0);
        CoordinateImpl coord2 = egm.makeCoordinate(2, -2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord4 = egm.makeCoordinate(1, 4);
        GameStatus move2 = egm.move(coord3, coord4);

        assertFalse(move2.isValidMove());
    }
    
    @Test
    void hexBounded() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test28.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord4 = egm.makeCoordinate(0, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertFalse(move2.isValidMove());
    }
    
    @Test
    void hexSemiBounded() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test29.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(1, -1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord4 = egm.makeCoordinate(0, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertFalse(move2.isValidMove());
    }

    @Test
    void endIfStuck() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test30.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 3);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.WIN);
    }

    @Test
    void canUnstick() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test31.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 3);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);
    }

    @Test
    void linearJumpSuccess() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test37.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(2, 4);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);
    }

    @Test
    void blockedJump() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test39.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 4);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.LOSE);
    }

    @Test
    void blockedLanding() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test39.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(4, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.LOSE);
    }
}

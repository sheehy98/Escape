package escape.rule;

import escape.*;
import escape.builder.EscapeGameBuilder;
import escape.coordinate.CoordinateImpl;
import escape.required.GameStatus;
import escape.required.GameStatus.MoveResult;
import escape.utility.BaseTest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeGameRuleTest extends BaseTest{
    @Test
    void canWin() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test20.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(0, 0);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord4 = egm.makeCoordinate(3, 3);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.WIN);
    }

    @Test
    void canTimeout() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test20.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord4 = egm.makeCoordinate(4, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.LOSE);
    }

    @Test
    void canTie() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test20.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(0, 0);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord4 = egm.makeCoordinate(4, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.DRAW);
    }

    @Test
    void hasValue() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test21.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord4 = egm.makeCoordinate(3, 3);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.WIN);
    }

    @Test
    void winByClear() {
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
        assertEquals(move1.getMoveResult(), MoveResult.WIN);
    }

    @Test
    void scoreWin() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test24.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(2, 3);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 3);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.WIN);
    }

    @Test
    void scoreWinBeyond() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test24.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(4, 3);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 3);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.WIN);
    }

    @Test
    void conflictWin() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test32.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.LOSE);
    }

    @Test
    void conflictCasualty() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test33.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(2, 1);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.NONE);
    }

    @Test
    void conflictPenalty() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test33.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(2, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.NONE);
    }

    @Test
    void allRules() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test34.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(2, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.NONE);

        CoordinateImpl coord5 = egm.makeCoordinate(3, 3);
        CoordinateImpl coord6 = egm.makeCoordinate(2, 3);
        GameStatus move3 = egm.move(coord5, coord6);

        assertTrue(move3.isValidMove());
        assertEquals(move3.getMoveResult(), MoveResult.NONE);

        CoordinateImpl coord7 = egm.makeCoordinate(3, 2);
        CoordinateImpl coord8 = egm.makeCoordinate(3, 3);
        GameStatus move4 = egm.move(coord7, coord8);

        assertTrue(move4.isValidMove());
        assertEquals(move4.getMoveResult(), MoveResult.WIN);
    }

    @Test
    void conflictTie() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test35.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(2, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertFalse(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.LOSE);
    }

    @Test
    void sacrificeWin() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test36.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.WIN);
    }

    @Test
    void comebackWin() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test34.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);

        CoordinateImpl coord3 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord4 = egm.makeCoordinate(2, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.NONE);

        CoordinateImpl coord5 = egm.makeCoordinate(3, 3);
        CoordinateImpl coord6 = egm.makeCoordinate(2, 2);
        GameStatus move3 = egm.move(coord5, coord6);

        assertTrue(move3.isValidMove());
        assertEquals(move3.getMoveResult(), MoveResult.WIN);
    }

    @Test
    void combatLinear() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test38.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.LOSE);
    }
}

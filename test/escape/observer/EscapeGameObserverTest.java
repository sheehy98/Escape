package escape.observer;

import escape.*;
import escape.builder.EscapeGameBuilder;
import escape.coordinate.CoordinateImpl;
import escape.required.GameStatus;
import escape.required.GameStatus.MoveResult;
import escape.utility.BaseTest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeGameObserverTest extends BaseTest{
    @Test
    void observerInvalid() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test31.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        GameObserverImpl observer = new GameObserverImpl();
        egm.addObserver(observer);

        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(0, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertFalse(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.LOSE);
        assertTrue(move1.isMoreInformation());
        assertTrue(observer.getMessage().contains("Chris lost"));
    }

    @Test
    void observerValid() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test31.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        GameObserverImpl observer = new GameObserverImpl();
        egm.addObserver(observer);

        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(3, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);
        assertFalse(move1.isMoreInformation());
    }

    @Test
    void observerWin() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test30.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        GameObserverImpl observer = new GameObserverImpl();
        egm.addObserver(observer);

        CoordinateImpl coord1 = egm.makeCoordinate(1, 3);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 2);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.WIN);
        assertTrue(move1.isMoreInformation());
        assertTrue(observer.getMessage().contains("Chris won"));
    }

    @Test
    void observerDraw() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test20.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        GameObserverImpl observer = new GameObserverImpl();
        egm.addObserver(observer);

        CoordinateImpl coord1 = egm.makeCoordinate(1, 1);
        CoordinateImpl coord2 = egm.makeCoordinate(0, 0);
        GameStatus move1 = egm.move(coord1, coord2);

        assertTrue(move1.isValidMove());
        assertEquals(move1.getMoveResult(), MoveResult.NONE);
        assertFalse(move1.isMoreInformation());

        CoordinateImpl coord3 = egm.makeCoordinate(2, 2);
        CoordinateImpl coord4 = egm.makeCoordinate(4, 2);
        GameStatus move2 = egm.move(coord3, coord4);

        assertTrue(move2.isValidMove());
        assertEquals(move2.getMoveResult(), MoveResult.DRAW);
        assertTrue(move2.isMoreInformation());
        assertTrue(observer.getMessage().contains(" tied on turn "));
    }

    @Test
    void conflictObserver() {
        EscapeGameManager<CoordinateImpl> egm = null;
        try {
            egm = new EscapeGameBuilder(configPath + "test33.ecg").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        GameObserverImpl observer = new GameObserverImpl();
        egm.addObserver(observer);
        
        CoordinateImpl coord1 = egm.makeCoordinate(1, 2);
        CoordinateImpl coord2 = egm.makeCoordinate(1, 1);
        GameStatus move1 = egm.move(coord1, coord2);
        assertTrue(move1.isMoreInformation());
        assertTrue(observer.getMessage().contains(" defeated "));
    }
}

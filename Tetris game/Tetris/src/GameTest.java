/**
 * Created By: Laura Guo
 * 30 October 2020
 * Description: This class contains tests for the Game class constructor and its getter and setters.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import java.time.*;

public class GameTest {
    Game g;
    
    @BeforeClass
    public static void setup() {
        g = new Game();
    } // end setup
    
    @Test
    void testDefaultConstructor1() {
        assertEquals(1, g.getLevel());
    }
    
    @Test
    void testDefaultConstructor2() {
        assertTrue(g.getTimePlayed().getClass() == LocalTime.class);
    }
    
    @Test
    void testDefaultConstructor3() {
        assertFalse(g.getExitGame());
    }
    
    @Test
    void testDefaultConstructor4() {
        assertEquals(1, g.getSpeedMultiplier());
    }
    
    @Test
    void testDefaultConstructor5() {
        assertEquals(1, g.getTimeMultiplier());
    }
    
    @Test
    void testDefaultConstructor6() {
        assertTrue(g.getBoard().getClass() == Board.class);
    }
    
    @Test
    void testSetLevel() {
        g.setLevel(2);
        
        assertEquals(2, g.getLevel());
    }
    
    @Test
    void testSetSpeedMultiplier() {
        g.setSpeedMultiplier(1.25);
        
        assertEquals(1.25, g.getSpeedMultiplier());
    }
    
    @Test
    void testSetScoreMultiplier() {
        g.setScoreMultiplier(2);
        
        assertEquals(2, g.getScoreMultiplier());
    }
    
    @Test
    void testSetTimePlayed() {
        LocalTime time = LocalTime.now();
        g.setTimePlayed(time);
        
        assertEquals(time, g.getTimePlayed());
    }
    
    @Test
    void testSetExitGame() {
        g.setExitGame(true);
        assertTrue(g.getExitGame());
    }
    
    @Test
    void testSetBoard() {
        Board b = new Board(1, 2, 30);
        g.setBoard(b);
        
        assertEquals(b, g.getBoard());
    }
}

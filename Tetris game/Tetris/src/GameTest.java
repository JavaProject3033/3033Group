/**
 * Created By: Laura Guo
 * 30 October 2020
 * Description: This class contains tests for the Game class constructor and its getter and setters.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import java.time.*;
import java.util.Arrays;

public class GameTest {
    static Game g;
    
    @BeforeClass
    public static void setup() {
        g = new Game();
    } // end setup
    
    @Test
    void testDefaultConstructor3() {
        assertFalse(g.getExitGame());
    }
    
    @Test
    void testDefaultConstructor4() {
        assertEquals(1, g.getSpeedMultiplier());
    }
    
    @Test
    void testDefaultConstructor6() {
        assertTrue(g.getBoard().getClass() == Board.class);
    }
    
    @Test
    void testSetSpeedMultiplier() {
        g.setSpeedMultiplier(1.25);
        
        assertEquals(1.25, g.getSpeedMultiplier());
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
    
    @Test
    void testMoveDownOne1() { // no space to move down; should be no change
        Board b = new Board(1, 2, 30); // b4: no clearable rows, no clearable colors, no floating blocks
        Block z = new Block(new BlockColor(0), new Shape('z', 0), 18, 0, 0);
        Block j1 = new Block(new BlockColor(1), new Shape('j', 3), 16, 3, 3);
        Block t1 = new Block(new BlockColor(0), new Shape('t', 0), 18, 4, 0);
        Block t2 = new Block(new BlockColor(2), new Shape('t', 0), 17, 6, 0);
        Block i = new Block(new BlockColor(0), new Shape('i', 0), 19, 6, 0);
        Block j2 = new Block(new BlockColor(2), new Shape('j', 3), 16, 9, 3);
        Block[] blocks = {z, j1, t1, t2, i, j2};
        
        for(int k = 0; k < blocks.length; k ++) {
            b.addBlock(blocks[k]);
        } // end for
        
        boolean[][] expectedBoard = new boolean[Board.ROWS][Board.COLS];
        for(int r = 0; r < Board.ROWS; r ++) {
            expectedBoard[r] = Arrays.copyOf(b.getBoard()[r], Board.ROWS);
        } // end for
        
        Block[] expectedBlocks = Arrays.copyOf(b.getBlocks(), b.getBlocks().length);
        
        g.setBoard(b);
        
        g.moveDownOne(j1);
        
        // checking to see if the board array is the same
        for(int r = 0; r < Board.ROWS; r ++) {
            assertArrayEquals(expectedBoard[r], b.getBoard()[r]);
        } // end for
        
        // checking to see if the blocks are the same
        assertArrayEquals(expectedBlocks, b.getBlocks());
    }
    
    @Test
    void testMoveDownOne2() { // should be space to move down one
        Board b = new Board(1, 2, 30); // b4: no clearable rows, no clearable colors, no floating blocks
        Block j1 = new Block(new BlockColor(1), new Shape('j', 3), 16, 3, 3);
        Block t2 = new Block(new BlockColor(2), new Shape('t', 0), 17, 6, 0);
        Block i = new Block(new BlockColor(0), new Shape('i', 0), 19, 6, 0);
        Block j2 = new Block(new BlockColor(2), new Shape('j', 3), 16, 9, 3);
        Block[] blocks = {j1, t2, i, j2};
        
        Board expectedBoard = new Board(1, 2, 30);
        Block j1expected = new Block(new BlockColor(1), new Shape('j', 3), 17, 3, 3);
        Block t2expected = new Block(new BlockColor(2), new Shape('t', 0), 17, 6, 0);
        Block iexpected = new Block(new BlockColor(0), new Shape('i', 0), 19, 6, 0);
        Block j2expected = new Block(new BlockColor(2), new Shape('j', 3), 16, 9, 3);
        Block[] expectedBlocks = {j1expected, t2expected, iexpected, j2expected};
        
        for(int k = 0; k < blocks.length; k ++) { // adding blocks to their corresponding boards
            b.addBlock(blocks[k]);
            expectedBoard.addBlock(expectedBlocks[k]);
        } // end for
        
        g.setBoard(b);
        g.moveDownOne(j1);
        
        // checking to see if the board array is the same
        for(int r = 0; r < Board.ROWS; r ++) {
            assertArrayEquals(expectedBoard.getBoard()[r], b.getBoard()[r]);
        } // end for
        
        // checking to see if the actual board has a j block at the location of j1expected
        Block[] actualBlocks = b.getBlocks();
        boolean containsCorrectBlock = false;
        
        for(int j = 0; j < actualBlocks.length; j ++) {
            if(actualBlocks[j].equals(j1expected)) {
                containsCorrectBlock = true;
            } // end if
        } // end for
        
        assertTrue(containsCorrectBlock);
    }
    
    @Test
    void testSetBoardArray() {
        Board b = new Board();
        Block j1 = new Block(new BlockColor(1), new Shape('j', 3), 16, 3, 3);
        b.addBlock(j1);
        g.setBoard(b);
        g.setBoardArray(j1, false);
        
        Board expected = new Board();
        
        // checking to see if the board array is the same
        for(int r = 0; r < Board.ROWS; r ++) {
            assertArrayEquals(expected.getBoard()[r], b.getBoard()[r]);
        } // end for   
    }
    
    @Test
    void testIsValidMove1() {
        Board b = BoardTest.generateb3();
        Block z = b.getBlocks()[6];
        g.setBoard(b);
        
        assertTrue(g.isValidMove(z, Game.RIGHT));
    }
    
    @Test
    void testIsValidMove2() {
        Board b = BoardTest.generateb3();
        Block z = b.getBlocks()[6];
        g.setBoard(b);
        
        assertFalse(g.isValidMove(z, Game.LEFT));
    }
    
    @Test
    void testIsValidMove3() {
        Board b = BoardTest.generateb3();
        Block z = b.getBlocks()[6];
        g.setBoard(b);
        
        assertFalse(g.isValidMove(z, Game.ROTATE_RIGHT));
    }
    
    @Test
    void testIsValidMove4() {
        Board b = BoardTest.generateb3();
        Block z = b.getBlocks()[6];
        g.setBoard(b);
        
        assertFalse(g.isValidMove(z, Game.ROTATE_LEFT));
    }
    
    @Test
    void testIsValidMove5() {
        Board b = BoardTest.generateb3();
        Block s = b.getBlocks()[2];
        g.setBoard(b);
        
        assertTrue(g.isValidMove(s, Game.RIGHT));
    }
    
    @Test
    void testIsValidMove6() {
        Board b = BoardTest.generateb3();
        Block s = b.getBlocks()[2];
        g.setBoard(b);
        
        assertFalse(g.isValidMove(s, Game.LEFT));
    }
    
    @Test
    void testIsValidMove7() {
        Board b = BoardTest.generateb3();
        Block s = b.getBlocks()[2];
        g.setBoard(b);
        
        assertTrue(g.isValidMove(s, Game.ROTATE_LEFT));
    }
    
    @Test
    void testIsValidMove8() {
        Board b = BoardTest.generateb3();
        Block s = b.getBlocks()[2];
        g.setBoard(b);
        
        assertFalse(g.isValidMove(s, Game.ROTATE_RIGHT));
    }
    
    @Test
    void testIsAtBottom1() {
        Board b = BoardTest.generateb3();
        b.removeBlock(5);
        g.setBoard(b);
        
        assertFalse(g.isAtBottom(b.getBlocks()[6]) /*z*/);
    }
    
    @Test
    void testIsAtBottom2() {
        Board b = BoardTest.generateb3();
        b.removeBlock(5);
        g.setBoard(b);
        
        assertFalse(g.isAtBottom(b.getBlocks()[4]) /*t*/);
    }
}

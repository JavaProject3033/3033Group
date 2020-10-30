/**
 * Created By: Laura Guo
 * 29 October 2020
 * Description: This class contains tests for the Board class, including for the constructors,
 *      clearRow, clearColor, nextBlock, IsValidLoaction, generateNewFutureBlocks, moveBlocksDown,
 *      removeBlankBlocks, and applicable getter and setter functions.
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

public class BoardTest {
    Board b1, b2;
    
    boolean[][][][] allShapes = {Shape.O, Shape.I, Shape.S, Shape.Z, Shape.L, Shape.J, Shape.T};
    
    @BeforeClass
    public static void setup() {
        b1 = new Board();
        b2 = new Board(1, 2, 30);
    } // end setup
    
    // checking that board is initialized to all zeros
    @Test
    void testDefaultConstructor1() {
        boolean allFalse = true;
        
        for(int r = 0; r < Board.ROWS; r++) {
            for(int c = 0; c < Board.COLS; c++) {
                if(!b1.getBoard()[r][c]) {
                    allFalse = false;
                } // end if
            } // end for
        } // end for
        
        assertTrue(allFalse);
    }
    
    // checking to see that blocks is not null
    @Test
    void testDefaultConstructor2() {
        assertNotNull(b1.getBlocks());
    } 
    
    // checking to see that futureBlocks is not null
    @Test
    void testDefaultConstructor3() {
        assertNotNull(b1.getFutureBlocks());
    } 
    
    // checking to see that normalBlockSpeed is initialized to 0
    @Test
    void testDefaultConstructor4() {
        assertEquals(0, b1.getNormalBlockSpeed());
    } 
    
    // checking to see that dropBlockSpeed is initialized to zero
    @Test
    void testDefaultConstructor5() {
        assertEquals(0, b1.getDropBlockSpeed());
    } 
    
    // checking to see that squareSideLength is initialized to zero
    @Test
    void testDefaultConstructor6() {
        assertEquals(0, b1.getSquareSideLength());
    } 
    
    // checking to see that the current block is the same as the first block in futureBlocks
    @Test
    void testDefaultConstructor7() {
        assertEquals(b1.getFutureBlocks()[0], b1.getCurrent());
    } 
    
    // checking to see that currentBlockIndex is 0
    @Test
    void testDefaultConstructor8() {
        assertEquals(0, b1.getCurrentBlockIndex());
    } 
    
    // checking that board is initialized to all zeros
    @Test
    void testConstructor1() {
        boolean allFalse = true;
        
        for(int r = 0; r < Board.ROWS; r++) {
            for(int c = 0; c < Board.COLS; c++) {
                if(!b2.getBoard()[r][c]) {
                    allFalse = false;
                } // end if
            } // end for
        } // end for
        
        assertTrue(allFalse);
    } 
    
    @Test
    void testConstructor2() {
        assertNotNull(b2.getBlocks());
    }
    
    // should have generated the futureBlocks array (no repeat shapes, every color used at least once)
    @Test
    void testConstructor3() {
        // uses helper function; see bottom of the page
        assertTrue(isValidFutureBlocks(b2));
    }
    
    @Test
    void testConstructor4() {
        assertEquals(1, b2.getNormalSpeed());
    } 
    
    @Test
    void testConstructor5() {
        assertEquals(2, b2.getDropBlockSpeed());
    }
    
    @Test
    void testConstructor6() {
        assertEquals(30, b2.getSquareSideLength());
    }
    
    @Test
    void testConstructor7() {
        assertEquals(0, b2.getCurrentBlockIndex());
    }
    
    @Test
    void testConstructor8() {
        assertEquals(b2.getFutureBlocks()[0], b2.getCurrent());
    }
  
     // no rows to clear; should be no change
    @Test
    void testClearRow1() {
        Board b3 = generateb3(); 
        b3.clearRow();
        
        assertArrayEquals(generateb3().getBoard(), b3.getBoard());
    }
    
    // should clear a row on the board
    @Test
    void testClearRow2() {
        Board after = new Board(1, 2, 30); // this one is hard to test . . .
        after.getBoard()[17][3] = true; // so I'm trying to test certain aspects to see if it matches up
        after.getBoard()[17][9] = true; // setting the expected board values up
        after.getBoard()[18][3] = true;
        after.getBoard()[18][6] = true;
        after.getBoard()[18][9] = true;
        
        for(int i = 1; i < Board.ROWS; i++) {
            after.getBoard()[19][i] = true;
        } // end for
        
        // checking to see if the expected color values are there
        int[] colorCount = {0, 0, 0}; // doing this by counting the number of occurrences of each color to see if it matches
        BlockColor blockColor;
        
        for(int i = 0; i < blocks.length; i++) {
            blockColor = blocks[i].getColor();
            
            if(blockColor.equals(BlockColor.COLORS[0])) {
                colorCount[0] ++;
            } // end if
            
            blockColor = blocks[i].getColor();
            
            if(blockColor.equals(BlockColor.COLORS[1])) {
                colorCount[1] ++;
            } // end if
            
            blockColor = blocks[i].getColor();
            
            if(blockColor.equals(BlockColor.COLORS[2])) {
                colorCount[2] ++;
            } // end if
        } // end for
        
        // if the colors are wrong, then the test fails
        if(colorCount[0] != 3 || colorCount[1] != 1 || colorCount[2] != 2) {
            fail();
            return;
        } // end if
        
        Board b4 = generateb4();
        b4.clearRow();
        
        assertArrayEquals(after.getBoard(), b4);
    }
    
    // no blocks with touching colors; should be no change
    @Test
    void testClearColor1() {
        Board b3 = generateb3(); 
        b3.clearColor();
        
        assertArrayEquals(generateb3().getBoard(), b3.getBoard());
    }
    
    // should clear all the touching colors (3+ touching blocks) on the board
    @Test
    void testClearColor2() {
        Board after = new Board(1, 2, 30);
        after.addBlock(new Block(new BlockColor(1), new Shape('j', 3), 17, 3, 3));
        after.addBlock(new Block(new BlockColor(2), new Shape('t', 0), 18, 6, 0));
        after.addBlock(new Block(new BlockColor(2), new Shape('j', 3), 17, 9, 3));
        
        Block b4 = generateb4();
        b4.clearColor();
        
        assertArrayEquals(after.getBoard(), b4);
    }
    
    @Test
    void testNextBlock1() {
        Board b = new Board(1, 2, 30);
        Block next = b.getFutureBlocks()[1];
        
        b.nextBlock();
        
        assertEquals(next, b.getCurrent());
    }
    
    @Test
    void testNextBlock2()) {
        Board b = new Board(1, 2, 30);
        int next = 2;
        
        b.nextBlock();
        
        assertEquals(next, b.getCurrentBlockIndex());
    }
    
    // should have called on the generateNewFutureBlocks function to get the next current block 
    @Test
    void testNextBlock3() {
        Board b = new Board(1, 2, 30);
        b.setCurrentBlockIndex(Shape.NUM_SHAPES - 1);
        int next = 0;
        
        b.nextBlock();
        
        assertEquals(next, b.getCurrentBlockIndex());
    }
    
    @Test
    void testNextBlock4() {
        Board b = new Board(1, 2, 30);
        b.setCurrentBlockIndex(Shape.NUM_SHAPES - 1);
        
        b.nextBlock();
        
        int next = b.getFutureBlocks[0];
        
        assertEquals(next, b.getCurrent());
    }
    
    // invalid location past the top of the board
    @Test
    void testIsValidLoaction1() {
        assertFalse(b1.isValidLocation(5, -1));
    }
    
    // valid location near the top of the board
    @Test
    void testIsValidLoaction2() {
        assertTrue(b1.isValidLocation(9, 0));
    }
    
    // invalid location past the right of the board
    @Test
    void testIsValidLoaction3() {
        assertFalse(b1.isValidLocation(10, 10));
    }
    
    // valid location near the right of the board
    @Test
    void testIsValidLoaction4() {
        assertFalse(b1.isValidLocation(9, 10));
    }
    
    // invalid location past the left of the board
    @Test
    void testIsValidLoaction5() {
        assertFalse(b1.isValidLocation(-1, 10));
    }
    
    // valid location near the left of the board
    @Test
    void testIsValidLoaction6() {
        assertFalse(b1.isValidLocation(0, 10));
    }
    
    // invalid location below the bottom of the board
    @Test
    void testIsValidLoaction7() {
        assertFalse(b1.isValidLocation(5, 20));
    }
    
    // valid location near the bottom of the board
    @Test
    void testIsValidLoaction8() {
        assertFalse(b1.isValidLocation(5, 19));
    }
    
    // valid location in the middle of the board
    @Test
    void testIsValidLoaction9() {
        assertFalse(b1.isValidLocation(5, 10));
    }
    
    // making sure it is not the same as the previous futureBlocks array
    @Test
    void testGenerateNewFutureBlocks1() {
        Board b = new Board(1, 2, 30);
        Block currentBlocks = b.getFutureBlockks();
        
        b.generateNewFutureBlocks();
        
        assertArrayNotEquals(currentBlocks, b.getFutureBlocks());
    }
    
    // checking that all blocks and colors are used at least once
    @Test
    void testGenerateNewFutureBlocks2() {
        Board b = new Board(1, 2, 30);
        b.generateNewFutureBlocks()
        
        assertTrue(checkFutureBlocks(b));
    }
    
    // all blocks touching bottom--no change
    @Test
    void testMoveBlocksDown1() {
        Board b3 = generateb3(); 
        b3.moveBlocksDown();
        
        assertArrayEquals(generateb3().getBoard(), b3.getBoard());
    }
    
    // block touching the same of another block--should fall down
    @Test
    void testMoveBlocksDown2() {
        boolean pass = false;
        Block floating = new Block(new BlockColor(2), new Shape('z', 0), 12, 7, 0); // this is the block that is currently floating
        Board b = new Board(1, 2, 30); // same as b3 but without the J block colored in 3
        b.addBlock(new Block(new BlockColor(0), new Shape('o', 0), 18, 0, 0));
        b.addBlock(new Block(new BlockColor(1), new Shape('l', 3), 17, 1, 3));
        b.addBlock(new Block(new BlockColor(0), new Shape('s', 3), 15, 2, 3));
        b.addBlock(new Block(new BlockColor(1), new Shape('i', 0), 19, 3, 0));
        b.addBlock(new Block(new BlockColor(2), new Shape('t', 0), 17, 7, 0));
        b.addBlock(floating); 
        
        b.moveBlocksDown();
        
        // checking if there is a block at 15,7; if so, the block should have the characteristics (other than location) as the specified block above
        if(b.getBoard[15][7]) {
            for(int i = 0; i < b.getBlocks().length; i++) { // finding the block that is at 15,7 (should be where the floating one fell to)
                Block current = b.getBlocks()[i];
                
                // seeing if the block is the same block that was floating
                if(current.getX() == 15 && current.getY() == 7) {
                    pass = current.isSameColor(floating) && Arrays.equals(current, floating);
                } 
            } // end for
        } // end if
        
        assertTrue(pass);  
    }
    
    // no blocks are blank--no change
    @Test
    void testRemoveBlankBlocks1() {
        Board b = generateb3();
        Block oldBlocksArr = Arrays.copyOf(b.getBlocks());
        
        b.removeBlankBlocks();
        
        assertEquals(oldBlocksArr, b.getBlocks());
    }
    
    // some blocks are blank--remove those
    @Test
    void testRemoveBlankBlocks2() {
        boolean notContainBlank = true;
        boolean containPartialBlank = false;
        Board b = new Board(1, 2, 30); // no clearable rows, no clearable colors, no floating blocks
        Block blank = new Block(new BlockColor(0), new Shape('o', 0), 18, 0, 0); // these two are the same as the first two blocks in generateb3()
        Block partialBlank = new Block(new BlockColor(1), new Shape('l', 3), 17, 1, 3);
        b.addBlock(blank);
        b.addBlock(partialBlank);
        
        // making the blank block full of blanks
        boolean[][] shapeArr = b.getShape();
        for(int i = 0; i < shapeArr.length; i++) { // for each row in the blank block
            Arrays.fill(shapeArr[i], false);
        } // end for
        
        // adjusting board to match
        b.getBoard()[17][1] = false;
        b.getBoard()[17][2] = false;
        b.getBoard()[18][2] = false;
        b.getBoard()[19][2] = false;
        
        // making the other block partially blank
        partialBlank.getShape()[0][0] = false;
        
        b.getBoard()[18][0] = false;
        
        // checking the blocks array to see if it got rid of the blank block but kept the partialBlank block
        boolean[][] blocksArr = b.getBlocks();
        for(int i = 0; i < blocksArr.length; i++) {
            Block current = blocksArr[i];
            
            if(current == blank) {
                notContainBlock = false;
            } // end if
            
            if(current == partialBlank) {
                containPartialBlank = true;
            } // end if
        } // end for
        
        assertTrue(notContainBlank && containPartialBlank);
    }
    
    @Test
    void testAddBlock1() {
        boolean successfulAdd = true;
        Block b = generateb4();
        Block added = new Block(new BlockColor(0), new Shape('o', 0), 0, 0, 0);
        
        b.addBlock(added);
        
        // checking to see if the block was added to the board
        successfulAdd = b.getBoard[0][0] && b.getBoard[0][1] && b.getBoard[1][0] && b.getBoard[1][1];
        
        // checking to see if the block was added to the block list
        Blocks[] blocksArr = b.getBlocks();
        for(int i = 0; i < blocksArr.length; i ++) {
            Block current = blocksArr[i];
            
            if(current.getX() == 0 && current.getY() == 0) { // if the block at 0,0 matches the added block in the other characteristics
                successfulAdd = current.isSameColor(added) && Arrays.equals(current, added);
            } // end if
        } // end for
        
        assertTrue(successfulAdd);
    }
    
    @Test
    void testRemoveBlock1() {
        boolean successfullyRemoved = true;
        Board b = generateb3();
        Block removed = new Block(new BlockColor(3), new Shape('j', 3), 14, 8, 3);
        
        b.removeBlock(removed);
        
        // checking to see if the block was actually removed
        succeessfullyRemoved = !b.getBoard()[14][8] || !b.getBoard()[15][8] || !b.getBoard()[16][8] || !b.getBoard()[16][7];
        
        Block blocksArr = b.getBlocks();
        for(int i = 0; i < blocksArr.length; i ++) {
            Block current = blocksArr[i];
            
            if(current.getX() == 14 && current.getY() == 8) { // if the block at 0,0 matches the added block in the other characteristics
                successfullyRemoved = false;
            } // end if
        } // end for
        
        assertTrue(successfullyRemoved);
    }
    
    // trying to remove a block that isn't there--do nothing; blocks[] is empty
    @Test
    void testRemoveBlock2() {
        Board b = new Board();
        Blocks[] expected = b.getBlocks();
        Block randomBlock = new Block();
        
        b.removeBlock(randomBlock);
        
        assertArrayEquals(expected, b.getBlocks());
    }
    
    // trying to remove a block that isn't there--do nothing
    @Test
    void testRemoveBlock3() {
        Board b = generateb3();
        Blocks[] expected = b.getBlocks();
        Block randomBlock = new Block();
        
        b.removeBlock(randomBlock);
        
        assertArrayEquals(expected, b.getBlocks());
    }
    
    @Test
    void testRemoveBlock4() {
        boolean successfullyRemoved = true;
        Board b = generateb3();
        Block removed = new Block(new BlockColor(3), new Shape('j', 3), 14, 8, 3);
        
        b.removeBlock(14, 8);
        
        // checking to see if the block was actually removed
        succeessfullyRemoved = !b.getBoard()[14][8] || !b.getBoard()[15][8] || !b.getBoard()[16][8] || !b.getBoard()[16][7];
        
        Block blocksArr = b.getBlocks();
        for(int i = 0; i < blocksArr.length; i ++) {
            Block current = blocksArr[i];
            
            if(current.getX() == 14 && current.getY() == 8) { // if the block at 0,0 matches the added block in the other characteristics
                successfullyRemoved = false;
            } // end if
        } // end for
        
        assertTrue(successfullyRemoved);
    }
    
    // trying to remove a block that isn't there--do nothing; blocks[] is empty
    @Test
    void testRemoveBlock5() {
        Board b = new Board();
        Blocks[] expected = b.getBlocks();
        
        b.removeBlock(5, 10);
        
        assertArrayEquals(expected, b.getBlocks());
    }
    
    // trying to remove a block that isn't there--do nothing
    @Test
    void testRemoveBlock6() {
        Board b = generateb3();
        Blocks[] expected = b.getBlocks();
        
        b.removeBlock(5, 5);
        
        assertArrayEquals(expected, b.getBlocks());
    }
    
    // helper functions
    /**
     * Checks if the given Board object has a valid futureBlocks array
     * @return true if the Board's futureBlocks array is valid, false otherwise
     */
    boolean checkFutureBlocks(Board b) {
        boolean hasEveryShape = true;
        boolean[] isShapeInArray = new boolean[Shape.NUM_SHAPES];
        boolean hasEveryColor = true;
        boolean[] isColorInArray = new boolean[BlockColor.NUM_COLORS];
        
        for(int i = 0; i < b.getFutureBlocks().length; i++) { // for every block in futureBlocks
            for(int j = 0; j < allShapes.length; j++) { // for every shape
                for(int k = 0; k < allShapes[j].length; k++) { // for every orientation
                    if(Arrays.equals(allShapes[j][k], b.getFutureBlocks()[i].getShape())) { //if the shapes/orientations are equal
                        isShapeInArray[j] = true;
                    } // end else
                } // end for
            } // end for
        } // end for
        
        // checking to see if all shapes are in the array
        for(int i = 0; i < isShapeInArray.length; i++) {
            if(!isShapeInArray[i]) {
                hasEveryShape = false;
                break;
            } // end if
        } // end for
        
        for(int i = 0; i < b.getFutureBlocks().length; i++) { // for every block in futureBlocks
            for(int j = 0; j < BlockColor.COLORS.length; j++) { // for every color
                if(b.getFutureBlocks()[i].getColor().equals(BlockCOlor.COLORS[j])) { // if the shape's color is equal to the current color
                    isColorInArray[j] = true;
                } // end if
            } // end for
        } // end for
        
        // checking to see if all colors are in the array
        for(int i = 0; i < isColorInArray.length; i++) {
            if(!isColorInArray[i]) {
                hasEveryColor = false;
                break;
            } // end if
        } // end for
        
        return hasEveryShape && hasEveryColor;
    } // end checkFutureBlocks
    
    /**
     * Creates a copy of the b3 object used in the above tests.
     * @return a copy of b3
     */
    static Board generateb3() {
        Board b = new Board(1, 2, 30); // no clearable rows, no clearable colors, no floating blocks
        b.addBlock(new Block(new BlockColor(0), new Shape('o', 0), 18, 0, 0));
        b.addBlock(new Block(new BlockColor(1), new Shape('l', 3), 17, 1, 3));
        b.addBlock(new Block(new BlockColor(0), new Shape('s', 3), 15, 2, 3));
        b.addBlock(new Block(new BlockColor(1), new Shape('i', 0), 19, 3, 0));
        b.addBlock(new Block(new BlockColor(2), new Shape('t', 0), 17, 7, 0));
        b.addBlock(new Block(new BlockColor(3), new Shape('j', 3), 14, 8, 3));
        b.addBlock(new Block(new BlockColor(2), new Shape('z', 0), 12, 7, 0));
        
        return b;
    } // end generate b3
    
    /**
     * Creates a copy of the b4 object used in the above tests.
     * @return a copy of b4
     */
    static Board generateb4() {
        Board b = new Board(1, 2, 30); // no clearable rows, no clearable colors, no floating blocks
        b.addBlock(new Block(new BlockColor(0), new Shape('z', 0), 18, 0, 0));
        b.addBlock(new Block(new BlockColor(1), new Shape('j', 3), 16, 3, 3));
        b.addBlock(new Block(new BlockColor(0), new Shape('t', 0), 18, 4, 0));
        b.addBlock(new Block(new BlockColor(2), new Shape('t', 0), 17, 6, 0));
        b.addBlock(new Block(new BlockColor(0), new Shape('i', 0), 19, 6, 0));
        b.addBlock(new Block(new BlockColor(2), new Shape('j', 3), 16, 9, 3));
        
        return b;
    } // end generate b3
}

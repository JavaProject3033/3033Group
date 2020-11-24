/**
 * Created By: Laura Guo
 * 28 October 2020
 * Description: This class contains tests for the constructors, getters, setters, rotateRight, rotateLeft,
 *      moveRight, moveLeft, and isSameColor methods in the Block class. There is no test for the updatePoints
 *      class since it should be private and is used for other methods that are tested.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class BlockTest {
    static Block b1, i, j, z;
    
    @BeforeEach
    public void setup() {
        b1 = new Block();
        
        i = new Block(new BlockColor(5), new Shape('i', 1), 0, 0, 1);  
        j = new Block(new BlockColor(5), new Shape('j', 0), 4, 3, 0); 
        z = new Block(new BlockColor(2), new Shape('Z', 3), 17, 9, 3); 
    } // end setup
    
    // color should be initialized to Color.WHITE
    @Test
    void testDefaultConstructor1() {
        assertEquals(BlockColor.COLORS[0], b1.getColor());
    } 
    
    // initialize to any shape--cannot be null
    @Test
    void testDefaultConstructor2() {
        assertNotNull(b1.getShape());
    } 
    
    // initializes the points correctly with the first point being as close to 0, 0 as possible. The test only checks the first point,
    //      the other points are checked in other tests.
    @Test
    void testDefaultConstructor3() {
        assertTrue(b1.getPoints()[0] == 0 && b1.getPoints()[1] < 2);
    } 
    
    // initializes the orientation to 0
    @Test
    void testDefaultConstructor4() {
        assertEquals(0, b1.getOrientation());
    } 
    
    @Test
    void testConstructor1() {
        assertEquals(BlockColor.COLORS[5], i.getColor());
    }
    
    @Test
    void testConstructor2() {
        assertArrayEquals(Shape.I[1], i.getShape());
    } 
    
    @Test
    void testConstructor3() {
        int[] correctPoints = {
                0, 0,
                1, 0,
                2, 0, 
                3, 0
                };
        
        assertArrayEquals(correctPoints, i.getPoints());
    } 
    
    @Test
    void testConstructor4() {
        assertEquals(1, i.getOrientation());
    } 
    
    @Test
    void testGetColor() {
        assertEquals(BlockColor.COLORS[2], z.getColor());
    } 
    
    @Test
    void testGetShape() {
        assertArrayEquals(Shape.Z[3], z.getShape());
    }
    
    @Test
    void testGetPoints1() {
        int[] correctPoints = {
                4, 3, 
                5, 3, 
                5, 4,
                5, 5
                };
        
        assertArrayEquals(correctPoints, j.getPoints());
    }
    
    @Test
    void testGetPoints2() {
        int[] correctPoints = {
                17, 9,
                18, 8,
                18, 9,
                19, 8
                };
        
        assertArrayEquals(correctPoints, z.getPoints());
    }
    
    @Test
    void testGetOrientation() {
        assertEquals(3, z.getOrientation());
    }
    
    @Test
    void testSetColor1() {
        b1.setColor(new BlockColor());
        
        assertEquals(BlockColor.COLORS[0], b1.getColor());
    }
    
    @Test
    void testSetColor2() {
        b1.setColor(new BlockColor(3));
        
        assertEquals(BlockColor.COLORS[3], b1.getColor());
    }
    
    @Test
    void testSetShape() {
        b1.setShape(new Shape('o', 3));
        
        assertArrayEquals(Shape.O[3], b1.getShape());
    }
    
    @Test
    void testUpdatePoints1() {
        b1.setShape(new Shape('o', 3));
        b1.updatePoints(5, 5);
        int[] correctPoints = {
                5, 5, 
                5, 6,
                6, 5, 
                6, 6 
                };
        
        assertArrayEquals(correctPoints, b1.getPoints());
    }
    
    @Test
    void testUpdatePoints2() {
        b1.setShape(new Shape('i', 1));
        b1.updatePoints(0, 0);
        int[] correctPoints = {
                0, 0,
                1, 0,
                2, 0, 
                3, 0
                };
        
        assertArrayEquals(correctPoints, b1.getPoints());
    }
    
    @Test
    void testSetOrientation() {
        b1.setOrientation(1);
        
        assertEquals(1, b1.getOrientation());
    }
    
    // rotating right changes the orientation value, the shape object, and points to match
    @Test
    void testRotateRight1a() { // ONLY CHECKING THE ORIENTATION
        Block b = new Block(new BlockColor(5), new Shape('j', 0), 4, 3, 0);
        b.rotateRight();
        
        assertEquals(1, b.getOrientation());
    }
    
    @Test
    void testRotateRight1b() { // ONLY CHECKING THE SHAPE
        Block b = new Block(new BlockColor(5), new Shape('j', 0), 4, 3, 0);
        b.rotateRight();
        
        assertArrayEquals(Shape.J[1], b.getShape());
    }
    
    @Test
    void testRotateRight1c() { // ONLY CHECKING THE POINTS
        Block b = new Block(new BlockColor(5), new Shape('j', 0), 4, 3, 0);
        b.rotateRight();
        
        int[] correctPoints = {
                4, 4,
                4, 5,
                5, 4,
                6, 4
                };
        
        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test // ONLY CHECKING THE ORIENTATION
    void testRotateRight2a() {
        Block b = new Block(new BlockColor(2), new Shape('Z', 3), 17, 8, 3);
        b.rotateRight();
        
        assertEquals(0, b.getOrientation());
    }
    
    @Test // ONLY CHECKING THE SHAPE
    void testRotateRight2b() {
        Block b = new Block(new BlockColor(2), new Shape('Z', 3), 17, 8, 3);
        b.rotateRight();
        
        assertArrayEquals(Shape.Z[0], b.getShape());
    }
    
    @Test 
    void testRotateRight2c() { // ONLY CHECKING THE POINTS
        Block b = new Block(new BlockColor(2), new Shape('Z', 3), 17, 8, 3);
        b.rotateRight();
        
        int[] correctPoints = {
                17, 7,
                17, 8,
                18, 8,
                18, 9
                };
        
        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test // ONLY CHECKING THE ORIENTATION
    void testRotateRight3a() {
        Block b = new Block(new BlockColor(2), new Shape('Z', 3), 17, 9, 3); // will go off the board if rotated. Thus, do nothing.
        b.rotateRight();
        
        assertEquals(0, b.getOrientation());
    }
    
    @Test // ONLY CHECKING THE SHAPE
    void testRotateRight3b() {
        Block b = new Block(new BlockColor(2), new Shape('Z', 3), 17, 9, 3); // will go off the board if rotated. Thus, do nothing.
        b.rotateRight();
        
        assertArrayEquals(Shape.Z[0], b.getShape());
    }
    
    @Test 
    void testRotateRight3c() { // ONLY CHECKING THE POINTS
        Block b = new Block(new BlockColor(2), new Shape('Z', 3), 17, 9, 3); // will go off the board if rotated but still rotate it
        b.rotateRight();
        
        int[] correctPoints = {
                17, 8,
                17, 9, 
                18, 9, 
                18, 10
                };
        
        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test
    void testRotateLeft1a() { // ONLY CHECKING THE ORIENTATION
        Block b = new Block(new BlockColor(5), new Shape('i', 1), 0, 0, 1); // will go off the board if rotated. Thus, do nothing.
        b.rotateLeft();
        
        assertEquals(0, b.getOrientation());
    }
    
    @Test
    void testRotateLeft1b() { // ONLY CHECKING THE SHAPE
        Block b = new Block(new BlockColor(5), new Shape('i', 1), 0, 0, 1); // will go off the board if rotated. Thus, do nothing.
        b.rotateLeft();
        
        assertArrayEquals(Shape.I[0], b.getShape());
    }
  
    @Test
    void testRotateLeft1c() { // ONLY CHECKING THE POINTS 
        Block b = new Block(new BlockColor(5), new Shape('i', 1), 0, 0, 1); // will go off the board if rotated but still do it
        b.rotateLeft();
        
        int[] correctPoints = {
                1, -2,
                1, -1,
                1, 0,
                1, 1
                };
        
        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test
    void testRotateLeft2a() { // ONLY CHECKING THE ORIENTATION
        Block b = new Block(new BlockColor(5), new Shape('j', 0), 4, 3, 0); 
        b.rotateLeft();
        
        assertEquals(3, b.getOrientation());
    }
    
    @Test
    void testRotateLeft2b() { // ONLY CHECKING THE SHAPE
        Block b = new Block(new BlockColor(5), new Shape('j', 0), 4, 3, 0); 
        b.rotateLeft();
        
        assertArrayEquals(Shape.J[3], b.getShape());
    }
    
    @Test
    void testRotateLeft2c() { // ONLY CHECKING THE POINTS
        Block b = new Block(new BlockColor(5), new Shape('j', 0), 4, 3, 0); 
        b.rotateLeft();
        
        int[] correctPoints = {
                4, 4,
                5, 4,
                6, 3, 
                6, 4
                };
        
        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test
    void testMoveRight1() {
        Block b = new Block(new BlockColor(5), new Shape('j', 0), 4, 1, 0); 
        b.moveRight();
        
        int[] correctPoints = {
                4, 2, 
                5, 2, 
                5, 3, 
                5, 4
                };
        
        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test
    void testMoveRight2() {
        Block b = new Block(new BlockColor(2), new Shape('Z', 3), 17, 9, 3); // will go off the board if moved. Thus, do nothing.
        b.moveRight();
        
        int[] correctPoints = {
                17, 9,
                18, 8,
                18, 9,
                19, 8
                };
        
        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test
    void testMoveLeft1() {
        Block b = new Block(new BlockColor(5), new Shape('j', 0), 4, 3, 0); 
        b.moveLeft();
        
        int[] correctPoints = {
                4, 2,
                5, 2,
                5, 3, 
                5, 4
                };
        
        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test
    void testMoveLeft2() {
        Block b = new Block(new BlockColor(2), new Shape('Z', 3), 17, 9, 3);
        b.moveLeft();
        
        int[] correctPoints = {
                17, 8,
                18, 7,
                18, 8,
                19, 7
                };

        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test
    void testMoveLeft3() {
        Block b = new Block(new BlockColor(5), new Shape('i', 1), 0, 0, 1); // will go off the board if moved. Thus, do nothing.
        b.moveLeft();
        
        int[] correctPoints = {
                0, 0,
                1, 0,
                2, 0,
                3, 0
                };
        
        assertArrayEquals(correctPoints, b.getPoints());
    }
    
    @Test
    void testIsSameColor1() {
        assertEquals(i.getColor(), j.getColor());
    }
    
    @Test
    void testIsSameColor2() {
        assertNotEquals(i.getColor(), z.getColor());
    }
    
} // end BlockColorTest

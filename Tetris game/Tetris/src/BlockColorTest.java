/**
 * Created By: Laura Guo
 * 27 October 2020
 * Description: This class contains test cases for the constructors and the methods isEqual, toggleColorblindMode,
 *       and the getters and setters in the BlockColor class.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class BlockColorTest {
    BlockColor bc1, bc2;
    
    @BeforeEach
    public void setup() {
        bc1 = new BlockColor();
        bc2 = new BlockColor(3);
    } // end setup
    
    @Test
    void testDefaultConstructor1() {
        assertEquals(0, bc1.getColor());
    } 
   
    @Test
    void testDefaultConstructor2() {
        assertEquals(BlockColor.COLORS, BlockColor.getCurrentColors());
    } 
    
    @Test
    void testConstructor() {
        assertEquals(3, bc2.getColor());
    } 
    
    @Test
    void testIsEqual1() {
        bc1.setColor(3);
        bc2.setColor(3);
        
        assertTrue(bc1.isEqual(bc2));
    } 
    
    @Test
    void testIsEqual2() {
        bc1.setColor(0);
        bc2.setColor(3);
        
        assertFalse(bc1.isEqual(bc2));
    } 
    
    /** Removed color blind mode due to time constraints
    @Test
    void testToggleColorblindMode1() {
        assertEquals(BlockColor.COLORBLIND_COLORS, BlockColor.getCurrentColors());
    } 
    
    @Test
    void testToggleColorblindMode2() {
        assertEquals(BlockColor.COLORS, BlockColor.getCurrentColors());
    } **/
    
    @Test
    void testGetColor() {
        bc1.setColor(0);
        
        assertEquals(0, bc1.getColor());
    } 
    
    @Test
    void testSetColor() {
        bc1.setColor(5);
        
        assertEquals(5, bc1.getColor());
    } 
} // end BlockColorTest

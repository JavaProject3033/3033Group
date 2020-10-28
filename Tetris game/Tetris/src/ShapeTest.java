/**
 * Created By: Laura Guo
 * 27 October 2020
 * Description: This class contains test cases for the constructors and all the methods (including
 *       the getters and setters) in the Shape class.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;


public class ShapeTest {
    Shape s1, s2, s3;
    Shape o, i, s, z, l, j, t;
    
    @BeforeClass
    public static void setup() {
        s1 = new Shape();
        s2 = new Shape('J');
        s3 = new Shape('j');
        
        o = new Shape('o');
        i = new Shape('i');
        s = new Shape('s');
        z = new Shape('z');
        l = new Shape('l');
        j = new Shape('j');
        t = new Shape('t');
    } // end setup
    
    @Test
    void testDefaultConstructor() {
        assertNotEquals(null, s1.getShape());
    } // end testSoundConstructor
    
    @Test
    void testConstructor1() {
        assertEquals(Shape.J, s2.getShape());
    } // end testSoundConstructor
    
    @Test
    void testConstructor2() {
        assertEquals(Shape.J, s2.getShape());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightO() {
        assertEquals(2, o.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightI() {
        assertEquals(4, i.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightS() {
        assertEquals(2, s.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightZ() {
        assertEquals(2, z.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightL() {
        assertEquals(3, l.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightJ() {
        assertEquals(3, j.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightT() {
        assertEquals(2, t.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthO() {
        assertEquals(2, o.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthI() {
        assertEquals(1, i.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthS() {
        assertEquals(3, s.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthZ() {
        assertEquals(3, z.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthL() {
        assertEquals(2, l.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthJ() {
        assertEquals(2, j.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthT() {
        assertEquals(3, t.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testSetShape1() {
        s1.setShape('z');
        
        assertEquals(Shape.Z, s1.getShape());
    } // end testSoundConstructor
    
    @Test
    void testSetShape2() {
        s1.setShape('Z');
        
        assertEquals(Shape.Z, s1.getShape());
    } // end testSoundConstructor
    
    @Test
    void testGetShape() {
        assertEquals(Shape.Z, z.getShape());
    } // end testSoundConstructor
} // end ShapeTest

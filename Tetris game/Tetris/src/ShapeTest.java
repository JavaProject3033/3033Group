/**
 * Created By: Laura Guo
 * 27 October 2020
 * Description: This class contains test cases for the constructors and all the methods (including
 *       the getters and setters) in the Shape class.
 * NOTE: there may be several issues with this class. I changed the GameStructure document, wrote this
 *       class, then changed stuff back. I tried to also change this class to match, but I may have missed
 *       some stuff. Let me know if you see any issues.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class ShapeTest {
    Shape shape1, shape2, shape3;
    Shape o1, i1, s1, z1, l1, j1, t1;
    Shape o2, i2, s2, z2, l2, j2, t2;
    
    @BeforeClass
    public static void setup() {
        shape1 = new Shape();
        shape2 = new Shape('J', 0);
        shape3 = new Shape('j', 2);
        
        o1 = new Shape('o', 0);
        i1 = new Shape('i', 0);
        s1 = new Shape('s', 0);
        z1 = new Shape('z', 0);
        l1 = new Shape('l', 0);
        j1 = new Shape('j', 0);
        t1 = new Shape('t', 0);
        
        o2 = new Shape('o', 3);
        i2 = new Shape('i', 3);
        s2 = new Shape('s', 3);
        z2 = new Shape('z', 3);
        l2 = new Shape('l', 3);
        j2 = new Shape('j', 3);
        t2 = new Shape('t', 3);
    } // end setup
    
    @Test
    void testDefaultConstructor() {
        assertNotEquals(null, shape1.getShape());
    } // end testSoundConstructor
    
    @Test
    void testConstructor1() {
        assertEquals(Shape.J[0], shape2.getShape());
    } // end testSoundConstructor
    
    @Test
    void testConstructor2() {
        assertEquals(Shape.J[2], shape2.getShape());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightO1() {
        assertEquals(2, o1.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightI1() {
        assertEquals(4, i1.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightS1() {
        assertEquals(2, s1.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightZ1() {
        assertEquals(2, z1.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightL1() {
        assertEquals(3, l1.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightJ1() {
        assertEquals(3, j1.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightT1() {
        assertEquals(2, t1.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthO1() {
        assertEquals(2, o1.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthI1() {
        assertEquals(1, i1.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthS1() {
        assertEquals(3, s1.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthZ1() {
        assertEquals(3, z1.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthL1() {
        assertEquals(2, l1.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthJ1() {
        assertEquals(2, j1.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthT1() {
        assertEquals(3, t1.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightO2() {
        assertEquals(2, o2.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightI2() {
        assertEquals(1, i2.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightS2() {
        assertEquals(3, s2.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightZ2() {
        assertEquals(3, z2.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightL2() {
        assertEquals(2, l2.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightJ2() {
        assertEquals(2, j2.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetHeightT2() {
        assertEquals(3, t2.getHeight());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthO2() {
        assertEquals(2, o2.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthI2() {
        assertEquals(4, i2.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthS2() {
        assertEquals(3, s2.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthZ2() {
        assertEquals(2, z2.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthL2() {
        assertEquals(3, l2.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthJ2() {
        assertEquals(2, j2.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testGetWidthT2() {
        assertEquals(2, t2.getWidth());
    } // end testSoundConstructor
    
    @Test
    void testSetShape1() {
        s1.setShape('z', 2);
        
        assertEquals(Shape.Z[2], s1.getShape());
    } // end testSoundConstructor
    
    @Test
    void testSetShape2() {
        s1.setShape('Z', 1);
        
        assertEquals(Shape.Z[1], s1.getShape());
    } // end testSoundConstructor
    
    @Test
    void testSetShape3() {
        s1.setShape(3);
        
        assertEquals(Shape.Z[3], s1.getShape());
    } // end testSoundConstructor
    
    @Test
    void testGetShape() {
        z.setShape(3);
        
        assertEquals(Shape.Z[3], z.getShape());
    } // end testSoundConstructor
} // end ShapeTest

/**
 * Created By: Laura Guo
 * 26 October 2020
 * Description: This class contains test cases for the methods increaseVolume and decreaseVolume
 *       in the Score class. The other methods in the Score class were not testable in this form, 
 *       thus no tests were written for them.
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class SoundTest {
    Sound s1, s2;
    double tolerance;
    
    @BeforeClass
    public static void setup() {
        // initialize variables
        s1 = new Sound(); // TODO currently expects the constructor to initialize the sound to 0.5
        s2 = new Sound(0.99);
        
        tolerance = 0.01; // margin of error expected for the volume since we are adding/multiplying doubles
    } // end setup
    
    @Test
    void testDefaultConstructor() {
        assertEquals(0.5, s1.getVolume());
    } // end testSoundConstructor
    
    @Test
    void testConstructor() {
        assertEquals(0.99, s2.getVolume());
    } // end testSoundConstructor
    
    @Test
    void testIncreaseVolume() {
        s1.increaseVolume();
        
        assertEquals(s1.VOLUME_CHANGE_AMOUNT, s1.getVolume(), tolerance);
    } // end testIncreaseVolume
    
    @Test
    void testDecreaseVolume() {
        s1.decreaseVolume();
        
        assertEquals(0, s1.getVolume(), tolerance);
    } // end testDecreaseVolume

}

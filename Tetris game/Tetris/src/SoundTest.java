/**
 * Created By: Laura Guo
 * 26 October 2020
 * Description: This class contains test cases for the methods increaseVolume and decreaseVolume
 *       in the Score class. The other methods in the Score class were not testable in this form, 
 *       thus no tests were written for them.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import javafx.embed.swing.JFXPanel;

class SoundTest {
    static Sound s1, s2;
    static double tolerance;
    
    @BeforeEach
    public void setup() {
        new JFXPanel(); // had to use this to get the tests to work (otherwise JavaFX toollkit was not initialized)
        
        // initialize variables
        s1 = new Sound(); // currently expects the constructor to initialize the sound to 0.25
        s2 = new Sound(0.99);
        
        tolerance = 0.01; // margin of error expected for the volume since we are adding/multiplying doubles
    } // end setup
    
    @Test
    void testDefaultConstructor() {
        assertEquals(0.25, s1.getVolume());
    } // end testSoundConstructor
    
    @Test
    void testConstructor() {
        assertEquals(0.99, s2.getVolume());
    } // end testSoundConstructor
    
    @Test
    void testIncreaseVolume() {
        s1.increaseVolume();
        
        assertEquals(s1.VOLUME_CHANGE_AMOUNT + 0.25, s1.getVolume(), tolerance);
    } // end testIncreaseVolume
    
    @Test
    void testDecreaseVolume() {
        Sound s = new Sound();
        s.decreaseVolume();
        
        assertEquals(0.15, s.getVolume(), tolerance);
    } // end testDecreaseVolume

}

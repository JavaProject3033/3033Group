/**
 * Created By: Laura Guo
 * 26 October 2020
 * Description: This class contains test cases for the method increaseScore in the Score class. 
 *      The other methods in the Score class were not testable in this form, thus no tests were
 *      written for them.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class ScoreTest {
    // variables to be used with testing
    Score s1, s2;
    
    @BeforeClass
    public static void setup() {
        // initialize variables
        s1 = new Score(); // TODO currently expects the constructor to initialize the score to zero
        s2 = new Score();
    } // end setup
    
    @Test
    void testIncreaseScore1() {
        // doing score increases
        s1.increaseScore(10);
        
        // seeing if the function got the right number
        assertEquals(10, s1.getScore());
    } // end testIncreaseScore
    
    @Test
    void testIncreaseScore2() {
        // doing score increases
        s2.increaseScore(0);
        
        // seeing if the function got the right number
        assertEquals(0, s1.getScore());
    } // end testIncreaseScore

} // end ScoreTest

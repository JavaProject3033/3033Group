/**
 * @author Laura Guo
 * 15 November 2020
 * Description: This class contains the board and controls the main game mechanics (user input, moving blocks around, etc.)
 */

import javafx.scene.input.*;
import javafx.scene.*;

public class Game {
    final KeyCode LEFT = KeyCode.LEFT;
    final KeyCode RIGHT = KeyCode.RIGHT;
    final KeyCode DROP = KeyCode.DOWN;
    final KeyCode ROTATE_RIGHT = KeyCode.Z;
    final KeyCode ROTATE_LEFT = KeyCode.X;
    double speedMultiplier;
    boolean exitGame;
    
    Score score;
    Board board;
    
    public Game() {
        
    } // end Game default constructor
    
    public Scene playGame() {
        // score 
        // preview of blocks
        // instructions
        // game board
    } // end playGame
    
    // other code here ....
} // end game

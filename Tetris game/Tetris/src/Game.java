/**
 * @author Laura Guo
 * 15 November 2020
 * Description: This class contains the board and controls the main game mechanics (user input, moving blocks around, etc.)
 */

import javafx.scene.input.*;
import javafx.scene.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class Game {
    private final KeyCode LEFT = KeyCode.LEFT;
    private final KeyCode RIGHT = KeyCode.RIGHT;
    private final KeyCode DROP = KeyCode.DOWN;
    private final KeyCode ROTATE_RIGHT = KeyCode.Z;
    private final KeyCode ROTATE_LEFT = KeyCode.X;
    
    private final int FALL_SPEED = 500; // default fall speed for the blocks in ms
    private int dropSpeed;
    private final double DROP_SPEED_MULTIPLIER = 0.5; // drop speed in relation to the fall speed
    private double speedMultiplier;
    private double SIDE_PANE_WIDTH = 0.15 * GameLauncher.WINDOW_WIDTH;
    
    private boolean exitGame;
    private int squareSideLength; // used for drawing the blocks
    
    Score score;
    Board board;
    
    public Game() {
        speedMultiplier = 1;
        exitGame = false;
        score = new Score();
        dropSpeed = (int) (FALL_SPEED * DROP_SPEED_MULTIPLIER);
        squareSideLength = 20;
        board = new Board(FALL_SPEED, dropSpeed, squareSideLength);
    } // end Game default constructor
    
    public void playGame(Stage stage, Scene nextScene) {
        // SCORE
        Label scoreLabel = new Label(String.format("SCORE\n%d", score.getScore()));
        
        // INSTRUCTIONS
        Label instructionsLabel = new Label(
                "INSTRUCTIONS:\n" + 
                "Game ends when the blocks get past the top row.\n" + 
                "Clear blocks by filling up rows or touching similar colors.\n" +
                "Get higher scores by surviving longer.\n" + 
                "\n" + 
                "CONTROLS:\n" +
                "←, →\tmove blocks left and right\n" + 
                "↓\tdrop blocks\n" + 
                "z\trotate left" + 
                "x\trotate right"
                );
        
        // SCORE AND INSTRUCTIONS ARE ON LEFT SIDE OF THE WINDOW
        VBox leftPane = new VBox(GameLauncher.ITEM_SPACING, scoreLabel, instructionsLabel);
        leftPane.setAlignment(Pos.TOP_LEFT);
        leftPane.setPrefWidth(SIDE_PANE_WIDTH);
        
        // GAME BOARD
        double boardPadding = GameLauncher.WINDOW_HEIGHT * 0.10;
        double boardWidth = SIDE_PANE_WIDTH * 2 + GameLauncher.ITEM_SPACING * 2;
        GridPane boardPane = new GridPane();
        boardPane.setAlignment(Pos.CENTER);
        boardPane.setPadding(new Insets(boardPadding, 0, boardPadding, 0));
        setBoardPane(boardPane);
        
        // PREVIEW OF BLOCKS
        // user can preview the next two blocks
        Label previewLabel = new Label("NEXT BLOCKS: ");
        
        // the previews are made of GridPanes
        GridPane preview1Pane = new GridPane();
        preview1Pane.setAlignment(Pos.CENTER);
        GridPane preview2Pane = new GridPane();
        preview1Pane.setAlignment(Pos.CENTER);
        
        VBox rightPane = new VBox(GameLauncher.ITEM_SPACING, previewLabel, preview1Pane, preview2Pane);
        rightPane.setAlignment(Pos.TOP_RIGHT);
        rightPane.setPrefWidth(SIDE_PANE_WIDTH);
        
        // EVENT HANDLERS
        // moving block left
        
        // moving block right
        
        // dropping block
        
        // rotating block left
        
        // rotating block right
        
        // pane, scene stuff
        HBox mainPane = new HBox(GameLauncher.ITEM_SPACING, leftPane, boardPane, rightPane);
        Scene scene = new Scene(mainPane, GameLauncher.WINDOW_WIDTH, GameLauncher.WINDOW_HEIGHT);
        
        stage.setScene(scene);
    } // end playGame
    
    private void updatePreviewPane() {
        // TODO?
    } // end updatePreview
    
    private void setPreview(GridPane pane, Block block) {
        
    } // end setPreview
    
    private void setBoardPane(GridPane pane) {
        // TODO
    } // end setBoardPane
    
    public void setSpeedMultiplier(double multiplier) {
        speedMultiplier = multiplier;
    } // end setSpeedMultiplier
    
    public double getSpeedMultiplier() {
        return speedMultiplier;
    } // end getSpeedMultiplier
    
    public void setExitGame(boolean exit) {
        exitGame = exit;
    } // end setExitGame
    
    public boolean getExitGame() {
        return exitGame;
    } // end getExitGame
    
    public void setBoard(Board b) {
        board = b;
    } // end setBoard
    
    public Board getBoard() { 
        return board;
    } // end getBoard
    
    public int getScore() {
        return score.getScore();
    } // end getScore
} // end game

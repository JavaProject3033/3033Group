/**
 * @author Laura Guo
 * 15 November 2020
 * Description: This class contains the board and controls the main game mechanics (user input, moving blocks around, etc.)
 */

import javafx.scene.input.*;
import javafx.scene.*;

import java.util.Arrays;

import javafx.application.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.animation.*;
import javafx.event.*;

public class Game {
    public final static KeyCode ROTATE_RIGHT = KeyCode.Z;
    public final static KeyCode ROTATE_LEFT = KeyCode.X;
    public final static KeyCode LEFT = KeyCode.LEFT;
    public final static KeyCode RIGHT = KeyCode.RIGHT;
    public final static KeyCode DROP = KeyCode.DOWN;
    
    private final int FALL_SPEED = 500; // default fall speed for the blocks in ms
    private int dropSpeed;
    private final double DROP_SPEED_MULTIPLIER = 0.5; // drop speed in relation to the fall speed
    private double speedMultiplier;
    private double SIDE_PANE_WIDTH = 0.15 * GameLauncher.WINDOW_WIDTH;
    private static final int POINTS_ARR_LEN = 8;
    
    private boolean exitGame;
    private int squareSideLength; // used for drawing the blocks
    private final Rectangle[] squares; // GUI representation of the possible squares that make up the board.
    
    Score score;
    Board board;
    
    public Game() {
        speedMultiplier = 1;
        exitGame = false;
        score = new Score();
        dropSpeed = (int) (FALL_SPEED * DROP_SPEED_MULTIPLIER);
        squareSideLength = 20;
        board = new Board(FALL_SPEED, dropSpeed, squareSideLength);
        
        squares = new Rectangle[BlockColor.NUM_COLORS + 1];
        
        for(int i = 0; i < squares.length - 1; i++) {
            squares[i] = new Rectangle(squareSideLength, squareSideLength, BlockColor.COLORS[i]);
        } // end for
        
        squares[squares.length - 1] = new Rectangle(squareSideLength, squareSideLength, Color.WHITE);
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
        
        // BLOCKS WILL FALL AUTOMATICALLY ...
        // ... until they reach the bottom of the board.
        // automatic falling blocks handler
        Timeline blockFallingTimeline = new Timeline(new KeyFrame(Duration.millis(FALL_SPEED * speedMultiplier), e -> {
            moveDownOne(board.getCurrent());
            
            // increase speed of blocks once certain score thresholds have been met
            if(score.getScore() == 30) 
                speedMultiplier = 1.25;
            
            if(score.getScore() == 50)
                speedMultiplier = 1.5;
            
            if(score.getScore() == 70)
                speedMultiplier = 2;
            
            if(score.getScore() == 90)
                speedMultiplier = 2.5;
        }));
        
        blockFallingTimeline.play();
        
        // check for rows and colors to clear
        board.clearRow();
        board.clearColors();
        
        // remove any blank blocks from the board
        board.removeBlankBlocks();
        
        // move floating blocks down
        // TODO may may not need this if the clear colors and stuff does this...
        
        // put the next block on the board
        board.nextBlock(); 
        score.increaseScore(1); // increase score by 1 for every block that the user gets on the board
        
        // update the preview panes
        // set the first preview to the block after the current block in the futureBlocks array
        setPreview(preview1Pane, board.getFutureBlocks()[board.getCurrentBlockIndex() + 1]);
        
        // set the second preview to the block after the first preview
        setPreview(preview2Pane, board.getFutureBlocks()[board.getCurrentBlockIndex() + 2]);
        
        // MAIN PANE
        HBox mainPane = new HBox(GameLauncher.ITEM_SPACING, leftPane, boardPane, rightPane);
        
        // EVENT HANDLERS
        mainPane.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            Block current = board.getCurrent();
            
            if(key == LEFT || key == RIGHT || key == Game.DROP || key == Game.ROTATE_LEFT || key == Game.ROTATE_RIGHT) {
                setBoardArray(current, false); // specifying that where the block currently is on the board is empty
                
                switch (key) {
                    case LEFT: // moving block left
                        if(isValidMove(current, key)) 
                            current.moveLeft();
                        break;
                        
                    case RIGHT: // moving block right
                        if(isValidMove(current, key)) 
                            current.moveRight();
                        break;
                        
                    case DOWN: // dropping block
                        moveDownOne(board.getCurrent());
                        break;
                        
                    case Z: // rotating block left
                        if(isValidMove(current, key))
                            current.moveLeft();
                        break;
                        
                    case X: // rotating block right
                        if(isValidMove(current, key))
                            current.moveRight();
                        break;
                        
                    default:
                        break;
                } // end switch
                
                setBoardArray(current, true); // changing the place that the block occupies on the board array
            } // end if
        });
        
        // SCENE SETUP
        Scene scene = new Scene(mainPane, GameLauncher.WINDOW_WIDTH, GameLauncher.WINDOW_HEIGHT);
        stage.setScene(scene);
    } // end playGame
    
    public void moveDownOne(Block b) {
        if(!isAtBottom(b)) {
            int currentX = b.getPoints[0];
            int newY = b.getPoints[1];
            b.updatePoints(currentX, newY);
        } // end if
    } // end moveDownOne
    
    public void setBoardArray(Block b, boolean occupied) {
        int[] points = b.getPoints();
        
        for(int i = 0; i < points.length; i += 2) {
            board.getBoard()[i][i + 1] = occupied;
        } // end for
    } // end updateBoard
    
    public boolean isValidMove(Block b, KeyCode move) {
        BlockColor bColor = new BlockColor(b.getColorNum());
        Shape bShape = b.getShapeObj();
        int[] bPoints = b.getPoints();
        Block testBlock = new Block(bColor, bShape, bPoints[0], bPoints[1], b.getOrientation()); // create a copy of the block to test rotate
        int[] testPoints = testBlock.getPoints();
        
        // performing the move on the test block
        switch (move) {
            case LEFT: // move left
                testBlock.moveLeft();
                break;
                
            case RIGHT: // move right
                testBlock.moveRight();
                break;
                
            case Z: // rotate left
                testBlock.rotateLeft();
                break;
                
            case X: // rotate right
                testBlock.rotateRight();
                break;
                
            default:
                return false;
        } // end switch
        
        // checking to see if the move resulted in valid points
        for(int i = 0; i < testPoints.length; i += 2) {
            int x = testPoints[i];
            int y = testPoints[i + 1];
            
            if(!(x >= 0 && x < Board.ROWS && y >= 0 && y < Board.COLS)) // not within bounds of the board
                return false;
            
            if(!(board.getBoard()[x][y])) // overlapping with any other blocks
                return false;
        } // end for
        
        return true;
    } // end isValidBlock
    
    private void setPreview(GridPane pane, Block block) {
        int numRows = block.getShapeObj().getWidth();
        int numCols = block.getShapeObj().getHeight();
        Rectangle square;
        boolean[][] shape = block.getShape();
        
        for(int r = 0; r < numRows; r ++) {
            for(int c = 0; c < numCols; c ++) {
                if(shape[r][c]) // if there is a square in this index
                    square = squares[block.getColorNum()];
                    
                else // otherwise there is no square
                    square = squares[squares.length - 1];
                
                pane.add(square, r, c);
            } // end for
        } // end for
    } // end setPreview
    
    private void setBoardPane(GridPane pane) {
        // filling pane with white squares first
        for(int r = 0; r < Board.ROWS; r ++) {
            for(int c = 0; c < Board.COLS; c ++) {
                pane.add(squares[squares.length - 1], r, c);
            } // end for
        } // end for
        
        // adding the colored squares onto the pane
        Block[] blocks = board.getBlocks();
        
        for(int i = 0; i < blocks.length; i ++) { // for each block
            if(blocks[i] == null)
                break;
            
            int points = blocks[i].getPoints();
            // TODO currently assuming points that aren't there anymore are set to -1 ...
            for(int j = 0; j < POINTS_ARR_LEN; j += 2) { // for each point in the block
                if(points[j] == -1)
                    break;
                
                pane.add(squares[blocks[i].getColorNum()], points[j], points[j + 1]);
            } // end for
        } // end for
    } // end setBoardPane
    
    public boolean isAtBottom(Block b) {
        int maxY = -1;
        
        // finding the largest y value among the points in block b. (This means they are on the "bottom" of the block.)
        for(int i = 1; i < POINTS_ARR_LEN; i += 2) {
            int currentY = b.getPoints()[i];
            
            if(currentY > maxY)
                maxY = currentY;
        } // end for
        
        // finding the other points with a y value the same as the maximum y value and checking to see if any of the bottom points have a square below them
        for(int i = 0; i < POINTS_ARR_LEN; i += 2) {
            int currentX = b.getPoints()[i];
            int currentY = b.getPoints()[i + 1];
            
         // this is a bottom point and there is a square below it on the board (or the board bottom)
            if(currentY == maxY && (board[currentX][currentY + 1] || currentY == Board.ROWS - 1)) 
                return true;
        } // end for
        
        return false;
    } // end isAtBottom
    
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

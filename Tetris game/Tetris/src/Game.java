/**
 * @author Laura Guo
 * 15 November 2020
 * Description: This class contains the board and controls the main game mechanics (user input, moving blocks around, etc.)
 */

import javafx.scene.input.*;
import javafx.scene.*;
import java.util.Arrays;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.animation.*;

public class Game {
    public final static KeyCode ROTATE_RIGHT = KeyCode.X;
    public final static KeyCode ROTATE_LEFT = KeyCode.Z;
    public final static KeyCode LEFT = KeyCode.LEFT;
    public final static KeyCode RIGHT = KeyCode.RIGHT;
    public final static KeyCode DROP = KeyCode.DOWN;
    
    private final int FALL_SPEED = 1000; // default fall speed for the blocks in ms
    private int dropSpeed;
    private final double DROP_SPEED_MULTIPLIER = 0.5; // drop speed in relation to the fall speed
    private double speedMultiplier;
    private double SIDE_PANE_WIDTH = 0.30 * GameLauncher.WINDOW_WIDTH;
    private static final int POINTS_ARR_LEN = 8;
    
    private boolean exitGame;
    private int squareSideLength; // used for drawing the blocks
//    private final Rectangle[] squares; // GUI representation of the possible squares that make up the board.
    
    private Score score;
    private Board board;
    
    public Timeline blockFallingTimeline;
    
    public Game() {
        speedMultiplier = 1;
        exitGame = false;
        score = GameLauncher.getScore();
        dropSpeed = (int) (FALL_SPEED * DROP_SPEED_MULTIPLIER);
        squareSideLength = (GameLauncher.WINDOW_HEIGHT - 20) / Board.ROWS;
        board = new Board(FALL_SPEED, dropSpeed, squareSideLength);
        System.out.println(board.getCurrent().getPoints()[0] + " " + board.getCurrent().getPoints()[1]);
        
//        squares = new Rectangle[BlockColor.NUM_COLORS + 1];
        
//        for(int i = 0; i < squares.length - 1; i++) {
//            squares[i] = new Rectangle(squareSideLength, squareSideLength, BlockColor.COLORS[i]);
//        } // end for
        
//        squares[squares.length - 1] = new Rectangle(squareSideLength, squareSideLength, Color.WHITE);
    } // end Game default constructor
    
    public void playGame(Stage stage, Scene nextScene) {
        int smallFontSize = 10;
        int largeFontSize = 25;
        
        // SCORE
        Label scoreTitleLabel = new Label("SCORE: ");
        scoreTitleLabel.setFont(new Font(smallFontSize));
        
        Label scoreLabel = new Label("" + score.getScore());
        scoreLabel.setFont(new Font(largeFontSize));
        
        // INSTRUCTIONS
        Label instructionsTitleLabel1 = new Label("INSTRUCTIONS:");
        Label instructionsTextLabel1 = new Label(
                "• Game ends when the blocks get past the top row.\n" + 
                "• Clear blocks by filling up rows or touching similar colors.\n" +
                "• Get higher scores by surviving longer.\n" + 
                "\n\n"        
                );
        Label instructionsTitleLabel2 = new Label("CONTROLS:");
        Label instructionsTextLabel2 = new Label(
                "←, →\tmove blocks left and right\n" + 
                "↓\t\tdrop blocks\n" + 
                "z\t\trotate left\n" + 
                "x\t\trotate right"
                );
        
        instructionsTextLabel1.setWrapText(true);
        instructionsTextLabel2.setWrapText(true);
        instructionsTitleLabel1.setFont(new Font(largeFontSize));
        instructionsTitleLabel2.setFont(new Font(largeFontSize));
        
        // SCORE AND INSTRUCTIONS ARE ON LEFT SIDE OF THE WINDOW
        VBox leftPane = new VBox(GameLauncher.ITEM_SPACING, scoreTitleLabel, scoreLabel, instructionsTitleLabel1, instructionsTextLabel1, instructionsTitleLabel2, instructionsTextLabel2);
        leftPane.setAlignment(Pos.TOP_LEFT);
        leftPane.setPrefWidth(SIDE_PANE_WIDTH);
        leftPane.setPadding(new Insets(GameLauncher.ITEM_SPACING * 2));
        
        // GAME BOARD
        double boardPadding = GameLauncher.WINDOW_HEIGHT * 0.10;
        double boardWidth = SIDE_PANE_WIDTH * 2 + GameLauncher.ITEM_SPACING * 2;
        GridPane boardPane = new GridPane();
        boardPane.setAlignment(Pos.CENTER);
        boardPane.setPadding(new Insets(boardPadding, 0, boardPadding, 0));
        setBoardPane(boardPane);
        
        // PREVIEW OF BLOCKS
        // user can preview the next two blocks
        Label previewLabel = new Label("NEXT BLOCK: ");
        previewLabel.setFont(new Font(largeFontSize));
        
        // the previews are made of GridPanes
        GridPane preview1Pane = new GridPane();
        preview1Pane.setAlignment(Pos.CENTER);
        setPreview(preview1Pane, board.getFutureBlocks()[board.getCurrentBlockIndex() + 1]);
//        GridPane preview2Pane = new GridPane();
//        preview1Pane.setAlignment(Pos.CENTER);
        
        VBox rightPane = new VBox(GameLauncher.ITEM_SPACING, previewLabel, preview1Pane);
        rightPane.setAlignment(Pos.TOP_LEFT);
        rightPane.setPrefWidth(SIDE_PANE_WIDTH);
        rightPane.setPadding(new Insets(GameLauncher.ITEM_SPACING * 2));
        
        // BLOCKS WILL FALL AUTOMATICALLY ...
        // ... until they reach the bottom of the board.
        // automatic falling blocks handler
        blockFallingTimeline = new Timeline(new KeyFrame(Duration.millis(FALL_SPEED * speedMultiplier), e -> {
            if(!exitGame) {
                moveDownOne(board.getCurrent());
                setBoardPane(boardPane); 
                
                scoreLabel.setText("" + score.getScore());
                
                // increase speed of blocks once certain score thresholds have been met
                if(score.getScore() == 30) 
                    speedMultiplier = 1.25;
                
                if(score.getScore() == 50)
                    speedMultiplier = 1.5;
                
                if(score.getScore() == 70)
                    speedMultiplier = 2;
                
                if(score.getScore() == 90)
                    speedMultiplier = 2.5;
                
                if(board.isAtBottom(board.current)) {
                 // check for rows and colors to clear
                    board.clearRow();
                    board.clearColors();
                    
                    // remove any blank blocks from the board
                    board.removeBlankBlocks();
                    
                    // move floating blocks down
                    board.moveBlocksDown();
                    
                    // put the next block on the board
                    board.nextBlock(); 
                    score.increaseScore(1); // increase score by 1 for every block that the user gets on the board
                    
                    // update the preview panes
                    // set the first preview to the block after the current block in the futureBlocks array
                    if(board.getCurrentBlockIndex() >= board.getFutureBlocks().length - 1) {
                        setPreview(preview1Pane, board.getFutureBlocks()[0]);
                    } // end if
                    
                    else {
                        setPreview(preview1Pane, board.getFutureBlocks()[board.getCurrentBlockIndex() + 1]);
                    } // end else
                    
                    // set the second preview to the block after the first preview
    //                setPreview(preview2Pane, board.getFutureBlocks()[board.getCurrentBlockIndex() + 2]);
                    
                    // checking for game over condition
                    // game over occurs when a block can't move down AND one of its points is above the top of the board
                    if(board.isAtBottom(board.getCurrent()) && !isValidBlock(board.getCurrent())) { 
                        exitGame = true;
                        
                        GameLauncher.getScoreLabel().setText((String.format("Your Score\t%d", getScore())));
                        
                        if(score.getScore() > score.getHighScore()) {
                            GameLauncher.getHighScoreLabel().setText("CONGRATULATIONS! New high score!");
                        } // end if
                        
                        GameLauncher.getScore().saveHighScore();
                        
                        stage.setScene(nextScene);
                    } // end if
                } // end if
            } // end if
        }));
        
        blockFallingTimeline.setCycleCount(Animation.INDEFINITE);
        blockFallingTimeline.play();
        
        // MAIN PANE
        HBox mainPane = new HBox(GameLauncher.ITEM_SPACING, leftPane, boardPane, rightPane);
        mainPane.setAlignment(Pos.CENTER);
        
        // MAIN SCENE
        Scene scene = new Scene(mainPane, GameLauncher.WINDOW_WIDTH, GameLauncher.WINDOW_HEIGHT);
        
        // EVENT HANDLERS
        scene.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            Block current = board.getCurrent();
            
            System.out.println("detected " + key);
            
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
                            current.rotateLeft();
                        break;
                        
                    case X: // rotating block right
                        if(isValidMove(current, key))
                            current.rotateRight();
                        break;
                        
                    default:
                        break;
                } // end switch
                
                setBoardArray(current, true); // changing the place that the block occupies on the board array
            } // end if
        });
        
        // DISPLAY SCENE
        stage.setScene(scene);
    } // end playGame
    
    private Rectangle newRectangle(int color) {
        if(color < BlockColor.NUM_COLORS)
            return new Rectangle(squareSideLength, squareSideLength, BlockColor.COLORS[color]);
        
        else
            return new Rectangle(squareSideLength, squareSideLength, Color.WHITE);
    } // end newRectangle
    
    public void moveDownOne(Block b) {
        if(!board.isAtBottom(b)) {
            System.out.println("not at bottom; can move down one");
            int newR = b.getPoints()[0] + 1;
            int currentC = b.getPoints()[1];
            
            setBoardArray(b, false);
            b.updatePoints(newR, currentC);
            setBoardArray(b, true);
        } // end if
    } // end moveDownOne
    
    public void setBoardArray(Block b, boolean occupied) {
        int[] points = b.getPoints();
        
        for(int i = 0; i < points.length; i += 2) {
            board.getBoard()[points[i]][points[i + 1]] = occupied;
        } // end for
    } // end updateBoard
    
    public boolean isValidMove(Block b, KeyCode move) {
        BlockColor bColor = new BlockColor(b.getColorNum());
        Shape bShape = new Shape(b.getShapeObj().getLetter(), b.getOrientation());
        int[] bPoints = b.getPoints();
        Block testBlock = new Block(bColor, bShape, bPoints[0], bPoints[1], b.getOrientation()); // create a copy of the block to test rotate
        
        System.out.println("orig points: " + Arrays.toString(bPoints));
        System.out.println("test points (before): " + Arrays.toString(testBlock.getPoints()));
        
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
        
        System.out.println("test points (after): " + Arrays.toString(testBlock.getPoints()));
        
        // checking to see if the move resulted in valid points
        //return isValidBlock(testBlock);
        
        int[] testPoints = testBlock.getPoints();
        Block[][] locations = board.generateBlockLocations();
        
        for(int i = 0; i < testPoints.length; i += 2) {
            System.out.println("points: " + testPoints[i] + " " + testPoints[i + 1]);
            
            if(!board.isValidLocation(testPoints[i], testPoints[i + 1])) { // goes off the board
                System.out.println("INVALID LOC");
                return false;
            } // end if
            
            // if it is on the board
            Block current = locations[testPoints[i]][testPoints[i + 1]];
            System.out.println("current: " + current + "\t" + b + " ");
            System.out.println("null " + (current == null));
            if(current != null && current != b) { // if the rotation leads the block to occupy a non-null space or a space occupied by another block
                System.out.println("IN NON-NULL SPACE OR SPACE WITH ANOTHER BLOCK");
                return false;                
            } // end if
        } // end for
        
        return true;
        
/**        BlockColor bColor = new BlockColor(b.getColorNum());
        Shape bShape = b.getShapeObj();
        int[] bPoints = b.getPoints();
        Block testBlock = new Block(bColor, bShape, bPoints[0], bPoints[1], b.getOrientation()); // create a copy of the block to test rotate
        
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
        return isValidBlock(testBlock);**/
    } // end isValidBlock
    
    public boolean isValidBlock(Block b) {
        int[] points = b.getPoints();
        
        for(int i = 0; i < points.length; i += 2) {
            if(!board.isValidLocation(points[i], points[i + 1])) { // checking to see if it is a valid board location first; prevents an exception when generateBoardLocation is called
                return false;
            } // end if
        } // end for
        
        Block[][] locations = board.generateBlockLocations();
        
        for(int i = 0; i < points.length; i += 2) {
            int r = points[i];
            int c = points[i + 1];
            
            if(!b.isValidShapePoint(r, c) || locations[r][c] == b || locations[r][c] != null) // not within bounds of the board and not overlapping with other blocks
                return false;
        } // end for
        
        return true;
    } // end isValidBlock
    
    private void setPreview(GridPane pane, Block block) {
        pane.getChildren().clear();
        
        boolean[][] shape = block.getShape();
        int numRows = shape.length;
        int numCols = shape[0].length;
        Rectangle square;
        
        for(int r = 0; r < numRows; r ++) {
            for(int c = 0; c < numCols; c ++) {
                if(shape[r][c]) { // if there is a square in this index
                    square = newRectangle(block.getColorNum());
                    pane.add(square, c, r);
                } // end if
            } // end for
        } // end for
    } // end setPreview
    
    private void setBoardPane(GridPane pane) {
        // filling pane with white squares first
        for(int r = 0; r < Board.ROWS; r ++) {
            for(int c = 0; c < Board.COLS; c ++) {
                pane.add(newRectangle(BlockColor.NUM_COLORS), c, r);
            } // end for
        } // end for
        
        // adding the colored squares onto the pane
        Block[] blocks = board.getBlocks();
        
        for(int i = 0; i < blocks.length; i ++) { // for each block
            if(blocks[i] == null)
                break;
            
            int[] points = blocks[i].getPoints();
            for(int j = 0; j < POINTS_ARR_LEN; j += 2) { // for each point in the block
                if(!blocks[i].isValidShapePoint(points[j], points[j + 1]))
                    break;
                
                pane.add(newRectangle(blocks[i].getColorNum()), points[j + 1], points[j]);
            } // end for
        } // end for
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

package board;

import java.util.*;
import javafx.geometry.Point2D; 

public class Board 
{
    final int ROWS = 10;
    final int COLS = 10;
    final int MAX_BLOCKS = ROWS * COLS / 4;
    boolean board[][];
    Block blocks[];
    Block futureBlocks[];
    double normalBlockSpeed;
    double dropBlockSpeed;
    int squareSideLength;
    int currentBlockIndex;
    Block current;
    
    Board()
    {
       /*
        Initialize board[][] with zeros
	Initialize blocks[]
	Generate/initialize futureBlocks
	Initialize normalBlockSpeed to 0
	Initialize dropBlockSpeed to 0
	Initialize squareSideLength to 0
	Initialize current to futureBlocks[0]
        */ 
        board = new boolean[ROWS][COLS];
        blocks = new Block[MAX_BLOCKS];
        futureBlocks = new Block[Shape.NUM_SHAPES];
        normalBlockSpeed = 0;
        dropBlockSpeed = 0;
        squareSideLength = 0;
        current = futureBlocks[0];
        currentBlockIndex = 0;
    }
    
    Board(int normalSpeed, int dropSpeed, int sideLen)
    {
        /*
        Initialize board[][] with zeros
        Initialize blocks[]
        Generate/initialize futureBlocks
        Initialize normalBlockSpeed to the given argument
        Initialize dropBlockSpeed to the given argument
        Initialize squareSideLength to the given argument
        */
        board = new boolean[ROWS][COLS];
        blocks = new Block[MAX_BLOCKS];
        futureBlocks = new Block[Shape.NUM_SHAPES];
        normalBlockSpeed = normalSpeed;
        dropBlockSpeed = dropSpeed;
        squareSideLength = sideLen;
        current = futureBlocks[0];
        currentBlockIndex = 0;
    }
    
    void clearRow()
    {
        /*
        Checks each row and clears them if needed by setting the cleared portions in the shape to be false
	May be helpful to use moveBlocksDown()
	Use this method when block hits the other stationary blocks
        */
        for(int i=0; i<ROWS; i++) {
            //check for row with all true
            boolean allFilled = true;
            
            for(int j=0; j<COLS; j++){
                if(board[i][j] == false){
                    allFilled = false;
                    break;
                }
            }
            
            if(allFilled){
                for(int j=0; j<COLS; j++){
                    board[i][j] = false;
                }
                
                moveBlocksDown();
            }
        }
    }
    
    void clearColors()
    {
        // Board is array of boolean. how to check the color
        // 
        
        /*
        Checks for and clears color clusters
	May be helpful to use moveBlocksDown()
	Use this method when block hits the other stationary blocks
        */
        ArrayList<Point2D> colorCluster = new ArrayList<Point2D>();
        
        //Check same color in same row
        for(int i=0; i<ROWS-2; i++) {
            for(int j=0; j<COLS-2; j++){
                if(board[i][j] && board[i][j+1] && board[i][j+2]) {
                    
                }
            }
        }
        
        //Check same color in same column
        for(int i=0; i<ROWS; i++) {
            for(int j=0; j<COLS; j++){
                
            }
        }
    }
    
    void nextBlock()
    {
        /*
        Places the next block into the current variable
	Increments the currentBlockIndex variable
	Calls on generateNewFutureBlocks when needed
        */
        if (currentBlockIndex > futureBlocks.length -1) {
            currentBlockIndex = 0;
            generateNewFutureBlocks();
            
        }
        
        current = futureBlocks[currentBlockIndex];
        currentBlockIndex++;
    }
    
    boolean isValidLocation(Point2D loc)
    {      
        /*
        Checks to see if a point is on the board
	Use this to check if a user’s move would be valid or not
        */    
        if(loc.getX()<0 || loc.getX()>9 || loc.getY()<0 || loc.getY()>9)
            return false;
        else
            return true;
    }
    
    void generateNewFutureBlocks()
    {
        /*
        Generates new blocks for the futureBlocks array.
	To make the game play more balanced:
        	All shapes should be used at least once
        	All colors should be used at least once
        	The order and color-block assignment should be random
        */
        Random randShape = new Random();
        Random randPoint = new Random();
        Random randOrientiation = new Random();
        
        ArrayList<Shape> shapeList= new ArrayList<Shape>();
        shapeList.add(new Shape('o', randShape.nextInt(4)));
        shapeList.add(new Shape('i', randShape.nextInt(4)));
        shapeList.add(new Shape('s', randShape.nextInt(4)));
        shapeList.add(new Shape('z', randShape.nextInt(4)));
        shapeList.add(new Shape('l', randShape.nextInt(4)));
        shapeList.add(new Shape('j', randShape.nextInt(4)));
        shapeList.add(new Shape('t', randShape.nextInt(4)));
        
        ArrayList<BlockColor> blockColourList = new ArrayList<BlockColor>();
        blockColourList.add(new BlockColor(0));
        blockColourList.add(new BlockColor(1));
        blockColourList.add(new BlockColor(2));
        blockColourList.add(new BlockColor(3));
        blockColourList.add(new BlockColor(4));
        blockColourList.add(new BlockColor(5));
        blockColourList.add(new BlockColor(6));
        
        Collections.shuffle(shapeList);
        Collections.shuffle(blockColourList);
        
        for(int i=0; i<futureBlocks.length; i++)
        {
            futureBlocks[i] = new Block(blockColourList.get(i), shapeList.get(i),
                                    new Point2D(randPoint.nextInt(20), randPoint.nextInt(20)), 
                                    randOrientiation.nextInt(4));
        }
    }
    
    void moveBlocksDown()
    {
        /*
        Checks to make sure that there are no blocks that are randomly floating
        If a block is not touching the bottom or attached somewhere to another 
        block, it should “fall” down until it is resting on something
        */
        //Clear floating row
        boolean blankRow =  false;
        
        for(int i=ROWS-1; i>=0; i--) {
            blankRow = true;
            
            for(int j=0; j<COLS; j++){
                if(board[i][j] == true){ 
                    blankRow = false;
                    break;
                }
            }
            
            if(blankRow){
                for(int row=i; row>0; row--) {
                    for(int col=0; col<COLS; col++)
                        board[row][col] = board[row-1][col];
                }
                
                for(int j=0; j<COLS; j++)
                    board[0][j] = false;                                                
            }
        }              
    }    
    
    void removeBlankBlocks()
    {
        /*
            Checks the blocks array and removes any blocks whose shapes are 
            completely false 
        */   
        boolean validShape = false;  
        ArrayList<Block> validBlocks = new ArrayList<Block>();
        
        for(int i=0; i<blocks.length; i++) {
            validShape = false;
            Shape shape = blocks[i].getShape();
            boolean[][] shapeArray = shape.getShape();
            
            for(int x=0; x<shapeArray.length; x++) {
                for(int y=0; y<shapeArray[x].length; y++) {
                    if(shapeArray[x][y] == true) {
                        validShape = true;
                    }
                }
            }
            
            if(validShape)
                validBlocks.add(blocks[i]);
            }
        
        blocks = new Block[validBlocks.size()];
        blocks = validBlocks.toArray(blocks);
    }
        
    // getters/setters for non-final variables

    public boolean[][] getBoard() {
        return board;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public Block[] getFutureBlocks() {
        return futureBlocks;
    }

    public double getNormalBlockSpeed() {
        return normalBlockSpeed;
    }

    public double getDropBlockSpeed() {
        return dropBlockSpeed;
    }

    public int getSquareSideLength() {
        return squareSideLength;
    }

    public int getCurrentBlockIndex() {
        return currentBlockIndex;
    }

    public Block getCurrent() {
        return current;
    }

    public void setBoard(boolean[][] board) {
        this.board = board;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public void setFutureBlocks(Block[] futureBlocks) {
        this.futureBlocks = futureBlocks;
    }

    public void setNormalBlockSpeed(double normalBlockSpeed) {
        this.normalBlockSpeed = normalBlockSpeed;
    }

    public void setDropBlockSpeed(double dropBlockSpeed) {
        this.dropBlockSpeed = dropBlockSpeed;
    }

    public void setSquareSideLength(int squareSideLength) {
        this.squareSideLength = squareSideLength;
    }

    public void setCurrentBlockIndex(int currentBlockIndex) {
        this.currentBlockIndex = currentBlockIndex;
    }

    public void setCurrent(Block current) {
        this.current = current;
    }        
}

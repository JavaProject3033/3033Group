package board;

public class Board 
{
    final int ROWS = 20;
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
        futureBlocks = new futureBlocks[Shape.NUM_SHAPES];
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
        futureBlocks = new futureBlocks[Shape.NUM_SHAPES];
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
            //check for row with all true and same color
            boolean allFilled = true;
            
            for(int j=0; j<COLS; j++){
                if(board[i][j] == false){
                    allFilled = false;
                    break;
                }
            }
            
            if(allFilled){
                moveBlocksDown();
            }
        }
    }
    
    void clearColors()
    {
        /*
        Checks for and clears color clusters
	May be helpful to use moveBlocksDown()
	Use this method when block hits the other stationary blocks
        */
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
    
    boolean isValidLocation(int x, int y)
    {
        // need more information on Point2D class
        
        /*
        Checks to see if a point is on the board
	Use this to check if a user’s move would be valid or not
        */    
        if(x<0 || x>9 || y<0 || y>9)
            return false;
        else
            return true;
    }
    
    void generateNewFutureBlocks()
    {
        // Need more information about shape class, 
        // the shapes and color of the blocks
        /*
        Generates new blocks for the futureBlocks array.
	To make the game play more balanced:
        	All shapes should be used at least once
        	All colors should be used at least once
        	The order and color-block assignment should be random
        */
    }
    
    void moveBlocksDown()
    {
        /*
        Checks to make sure that there are no blocks that are randomly floating
        If a block is not touching the bottom or attached somewhere to another 
        block, it should “fall” down until it is resting on something
        */
        // TODO
    }
    
    
    void removeBlankBlocks()
    {
        // Need more information about the shape of the block from Block class
        /*
            Checks the blocks array and removes any blocks whose shapes are 
            completely false 
        */        
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
